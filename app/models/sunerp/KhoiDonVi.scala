package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import dtos.{KhoiDonViDto, ExtGirdDto, PagingDto}

/**
 * The Class KhoiDonVi.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:11 AM
 *
 */
case class KhoiDonVi(
                      id: Option[Long] = None,
                      name: String,
                      companyId: Long
                      ) extends WithId[Long]

class KhoiDonVis(tag: Tag) extends AbstractTable[KhoiDonVi](tag, "khoiDonVi") {

  def name = column[String]("name", O.NotNull)

  def companyId = column[Long]("companyId", O.NotNull)

  def company = foreignKey("company_khoi_don_vi_fk", companyId, Companies)(_.id)

  def * = (id.?, name, companyId) <>(KhoiDonVi.tupled, KhoiDonVi.unapply)
}

object KhoiDonVis extends AbstractQuery[KhoiDonVi, KhoiDonVis](new KhoiDonVis(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(minLength = 4),
      "companyId" -> longNumber
    )(KhoiDonVi.apply)(KhoiDonVi.unapply)
  )

  implicit val khoiDonViJsonFormat = Json.format[KhoiDonVi]

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[KhoiDonViDto] = {
    var query = for (khoiDonVi <- this; company <- khoiDonVi.company) yield (khoiDonVi, company)

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        val (khoiDonVi, company) = table
        filter.property match {
          case "name" => khoiDonVi.name.toLowerCase like filter.valueForLike
          case "company.name" => company.name.toLowerCase like filter.valueForLike
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (khoiDonVi, company) = table
        sort.property match {
          case "name" => orderColumn(sort.direction, khoiDonVi.name)
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
      .map(KhoiDonViDto.apply)

    ExtGirdDto[KhoiDonViDto](
      total = totalRow,
      data = rows
    )
  }
}