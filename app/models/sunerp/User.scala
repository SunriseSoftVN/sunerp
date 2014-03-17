package models.sunerp

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import models.core.{WithId, AbstractTable, AbstractQuery, Hash}
import dtos.{ExtGirdDto, PagingDto, UserDto}
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
 * The Class User.
 *
 * @author Nguyen Duc Dung
 * @since 10/5/13 6:29 PM
 *
 */
case class User(
                 id: Option[Long] = None,
                 username: String,
                 password: String,
                 roleId: Long,
                 nhanVienId: Option[Long] = None
                 ) extends WithId[Long] {

  def authorities(implicit session: Session): List[Authority] = Authorities.findByRoleId(roleId)
}

class Users(tag: Tag) extends AbstractTable[User](tag, "user") {

  def username = column[String]("username", O.NotNull)

  def password = column[String]("password", O.NotNull)

  def roleId = column[Long]("roleId", O.NotNull)

  def nhanVienId = column[Long]("nhanVienId", O.Nullable)

  def role = foreignKey("role_user_fk", roleId, Roles)(_.id)

  def nhanVien = foreignKey("nhan_vien_user_fk", nhanVienId, NhanViens)(_.id)

  def * = (id.?, username, password, roleId, nhanVienId.?) <>(User.tupled, User.unapply)
}

object Users extends AbstractQuery[User, Users](new Users(_)) {

  val userRoleQuery = for (user <- Users; role <- user.role) yield (user, role)

  def login(username: String, password: String)(implicit session: Session) = {
    val user = findByUsername(username)
    user.isDefined && Hash.checkPassword(password, user.get.password)
  }

  def findByUsername(username: String)(implicit session: Session) = where(_.username === username).firstOption()

  def loadWithRole(pagingDto: PagingDto)(implicit session: Session) = {
    var query = userRoleQuery

    pagingDto.filters.foreach(filter => {
      query = query.where(tuple => {
        val (user, role) = tuple
        filter.property match {
          case "username" => user.username.toLowerCase like "%" + filter.value.toLowerCase + "%"
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(tuple => {
        val (user, role) = tuple
        sort.property match {
          case "username" => if (sort.direction == "asc") user.username.asc else user.username.desc
          case "role.name" => if (sort.direction == "asc") role.name.asc else role.name.desc
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val users = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(UserDto.apply)

    ExtGirdDto[UserDto](
      total = totalRow,
      data = users
    )
  }

  def countAll(implicit session: Session) = Query(length).first()

  def allWithRole(implicit session: Session) = userRoleQuery.list.map(UserDto.apply)


  override def beforeSave(entity: User)(implicit session: Session) = {
    entity.copy(password = Hash.createPassword(entity.password))
  }

  override def beforeUpdate(entity: User)(implicit session: Session) = {
    val oldUser = findById(entity.id.get)
    oldUser.map(user => {
      if (user.password == entity.password) {
        entity
      } else {
        entity.copy(password = Hash.createPassword(entity.password))
      }
    }).getOrElse(entity)
  }

  implicit val userJsonFomart = Json.format[User]

  def editForm = Form(
    mapping(
      "id" -> optional(of[Long]),
      "username" -> text(minLength = 4),
      "password" -> text(minLength = 4),
      "roleId" -> longNumber,
      "nhanVienId" -> optional(longNumber)
    )(User.apply)(User.unapply)
  )
}