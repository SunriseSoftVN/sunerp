package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import dtos.{PhongBanDto, ExtGirdDto, PagingDto}

/**
 * The Class PhongBan.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:20 AM
 *
 */
case class PhongBan(
                      id: Option[Long] = None,
                      donViId: Long,
                      name: String
                      ) extends WithId[Long]

class PhongBans(tag: Tag) extends AbstractTable[PhongBan](tag, "phongBan") {

  def donViId = column[Long]("donViId", O.NotNull)

  def donVi = foreignKey("doi_vi_phong_ban_fk", donViId, DonVis)(_.id)

  def name = column[String]("name", O.NotNull)

  def * = (id.?, donViId, name) <>(PhongBan.tupled, PhongBan.unapply)
}

object PhongBans extends AbstractQuery[PhongBan, PhongBans](new PhongBans(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "donViId" -> longNumber,
      "name" -> text
    )(PhongBan.apply)(PhongBan.unapply)
  )

  implicit val phongBanJsonFormat = Json.format[PhongBan]

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[PhongBanDto] = {
    var query = for (phongBan <- this; donVi <- phongBan.donVi) yield (donVi, phongBan)
    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        val (donVi, phongBan) = table
        filter.property match {
          case "name" => phongBan.name.toLowerCase like filter.asLikeValue
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (donVi, phongBan) = table
        sort.property match {
          case "name" => orderColumn(sort.direction, phongBan.name)
          case "donVi.name" => orderColumn(sort.direction, donVi.name)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(PhongBanDto.apply)

    ExtGirdDto[PhongBanDto](
      total = totalRow,
      data = rows
    )
  }
}