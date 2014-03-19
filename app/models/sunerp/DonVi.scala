package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._
import dtos.{ExtGirdDto, PagingDto}

/**
 * The Class DonVi.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:20 AM
 *
 */
case class DonVi(
                  id: Option[Long] = None,
                  name: String,
                  khoiDonViId: Option[Long]
                  ) extends WithId[Long]

class DonVis(tag: Tag) extends AbstractTable[DonVi](tag, "donVi") {
  def name = column[String]("name", O.NotNull)
  def khoiDonViId = column[Long]("khoiDonViId", O.NotNull)
  def khoiDonVi = foreignKey("khoiDonVi_fk", khoiDonViId, KhoiDonVis)(_.id)
  def * = (id.?, name, khoiDonViId.?) <>(DonVi.tupled, DonVi.unapply)
}

object DonVis extends AbstractQuery[DonVi, DonVis](new DonVis(_)) {
  implicit val donViJsonFormat = Json.format[DonVi]

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(minLength = 4),
      "khoiDonViId" -> optional(longNumber)
    )(DonVi.apply)(DonVi.unapply)
  )

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[DonVi] = {
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

    ExtGirdDto[DonVi](
      total = totalRow,
      data = rows
    )
  }
}