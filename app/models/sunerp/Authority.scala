package models.sunerp

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import models.core.{WithId, AbstractTable, AbstractQuery}
import play.api.libs.json.Json
import dtos.{AuthorityDto, ExtGirdDto, PagingDto}
import play.api.data.Form
import play.api.data.Forms._

/**
 * The Class Authority.
 *
 * @author Nguyen Duc Dung
 * @since 2/13/14 2:49 AM
 *
 */
case class Authority(
                      id: Option[Long] = None,
                      domain: String,
                      roleId: Long
                      ) extends WithId[Long]

class Authorities(tag: Tag) extends AbstractTable[Authority](tag, "authority") {

  def domain = column[String]("domain", O.NotNull)

  def roleId = column[Long]("roleId", O.NotNull)

  def role = foreignKey("role_authority_fk", roleId, Roles)(_.id)

  def * = (id.?, domain, roleId) <>(Authority.tupled, Authority.unapply)
}

object Authorities extends AbstractQuery[Authority, Authorities](new Authorities(_)) {

  def findByUserId(userId: Long)(implicit session: Session) = Users.findById(userId).map(user => {
    where(_.roleId === user.roleId).list()
  }).getOrElse(Nil)

  def findByRoleId(roleId: Long)(implicit session: Session) = where(_.roleId === roleId).list()

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[AuthorityDto] = {
    var query = for (auth <- this; role <- auth.role) yield (auth, role)

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        val (authority, role) = table
        filter.property match {
          case "domain" => authority.domain.toLowerCase like filter.valueForLike
          case "role.name" => role.name.toLowerCase like filter.valueForLike
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (authority, role) = table
        sort.property match {
          case "domain" => orderColumn(sort.direction, authority.domain)
          case "role.name" => orderColumn(sort.direction, role.name)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(AuthorityDto.apply)

    ExtGirdDto[AuthorityDto](
      total = totalRow,
      data = rows
    )
  }

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "domain" -> nonEmptyText,
      "roleId" -> longNumber
    )(Authority.apply)(Authority.unapply)
  )

  implicit val jsonFormat = Json.format[Authority]
}
