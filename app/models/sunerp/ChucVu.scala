package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import dtos.{ExtGirdDto, PagingDto}

/**
 * The Class ChucVu.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 10:09 AM
 *
 */
case class ChucVu(
                   id: Option[Long] = None,
                   name: String,
                   phuCapTrachNhiem: Long
                   ) extends WithId[Long]

class ChucVus(tag: Tag) extends AbstractTable[ChucVu](tag, "chucVu") {
  def name = column[String]("name", O.NotNull)

  def phuCapTrachNhiem = column[Long]("phuCapTrachNhiem", O.NotNull)

  def * = (id.?, name, phuCapTrachNhiem) <>(ChucVu.tupled, ChucVu.unapply)
}

object ChucVus extends AbstractQuery[ChucVu, ChucVus](new ChucVus(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(minLength = 4),
      "phuCapTrachNhiem" -> longNumber
    )(ChucVu.apply)(ChucVu.unapply)
  )

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[ChucVu] = {
    var query = for (row <- this) yield row

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        filter.property match {
          case "name" => table.name.toLowerCase like filter.asLikeValue
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        sort.property match {
          case "name" => orderColumn(sort.direction, table.name)
          case "phuCapTrachNhiem" => orderColumn(sort.direction, table.phuCapTrachNhiem)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list

    ExtGirdDto[ChucVu](
      total = totalRow,
      data = rows
    )
  }

  implicit val chucVuJsonFormat = Json.format[ChucVu]

}
