package models.sunerp

import play.api.db.slick.Config.driver.simple._
import models.core.{WithId, AbstractQuery, AbstractTable}
import play.api.libs.json.Json
import play.api.data.format.Formats._
import play.api.data.Form
import play.api.data.Forms._
import dtos.{ExtGirdDto, PagingDto}

/**
 * The Class Role.
 *
 * @author Nguyen Duc Dung
 * @since 2/12/14 12:56 AM
 *
 */
case class Role(
                 id: Option[Long] = None,
                 name: String
                 ) extends WithId[Long]

class Roles(tag: Tag) extends AbstractTable[Role](tag, "role") {
  def name = column[String]("name")

  def * = (id.?, name) <>(Role.tupled, Role.unapply)
}

object Roles extends AbstractQuery[Role, Roles](new Roles(_)) {

  def findByName(name: String)(implicit session: Session) = where(_.name === name).firstOption

  def editForm = Form(
    mapping(
      "id" -> optional(of[Long]),
      "name" -> text(minLength = 4)
    )(Role.apply)(Role.unapply)
  )

  implicit val roleJsonFormat = Json.format[Role]

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[Role] = {
    var query = for (row <- this) yield row

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        filter.property match {
          case "name" => table.name.toLowerCase like filter.valueForLike
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        sort.property match {
          case "name" => orderColumn(sort.direction, table.name)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list

    ExtGirdDto[Role](
      total = totalRow,
      data = rows
    )
  }
}

