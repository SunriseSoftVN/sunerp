package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._
import dtos.{DonViDto, ExtGirdDto, PagingDto}

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
                  companyId: Long
                  ) extends WithId[Long]

class DonVis(tag: Tag) extends AbstractTable[DonVi](tag, "donVi") {
  def name = column[String]("name", O.NotNull)
  def companyId = column[Long]("companyId", O.NotNull)
  def company = foreignKey("company_fk", companyId, Companies)(_.id)
  def * = (id.?, name, companyId) <>(DonVi.tupled, DonVi.unapply)
}

object DonVis extends AbstractQuery[DonVi, DonVis](new DonVis(_)) {
  implicit val donViJsonFormat = Json.format[DonVi]

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(minLength = 4),
      "companyId" -> longNumber
    )(DonVi.apply)(DonVi.unapply)
  )

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[DonViDto] = {
    var query = for (donVi <- this; company <- donVi.company) yield (donVi, company)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(table => {
        val (donVi, khoiDonVi) = table
        filter.property match {
          case "name" => donVi.name.toLowerCase like filter.asLikeValue
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (donVi, company) = table
        sort.property match {
          case "name" => orderColumn(sort.direction, donVi.name)
          case "company.name" => orderColumn(sort.direction, company.name)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(DonViDto.apply)

    ExtGirdDto[DonViDto](
      total = totalRow,
      data = rows
    )
  }
}