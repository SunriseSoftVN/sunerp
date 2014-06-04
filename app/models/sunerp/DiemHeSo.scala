package models.sunerp

import models.core.{WithId, AbstractQuery, AbstractTable}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import dtos.{DiemHeSoDto, ExtGirdDto, PagingDto}
import utils.DateTimeUtils

/**
 * The Class DiemHeSo.
 *
 * @author Nguyen Duc Dung
 * @since 6/2/2014 9:22 AM
 *
 */
case class DiemHeSo(
                     id: Option[Long] = None,
                     nhanVienId: Long,
                     heSo: Double,
                     year: Int
                     ) extends WithId[Long]

class DiemHeSos(tag: Tag) extends AbstractTable[DiemHeSo](tag, "diemheso") {
  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanVien = foreignKey("xep_loai_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  def heSo = column[Double]("heSo", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def * = (id.?, nhanVienId, heSo, year) <>(DiemHeSo.tupled, DiemHeSo.unapply)
}

object DiemHeSos extends AbstractQuery[DiemHeSo, DiemHeSos](new DiemHeSos(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "heSo" -> of[Double],
      "year" -> default(number, DateTimeUtils.currentYear)
    )(DiemHeSo.apply)(DiemHeSo.unapply)
  )

  def findByNhanVienId(nhanVienId: Long, year: Int)(implicit session: Session) =
    where(diemHeSO => diemHeSO.nhanVienId === nhanVienId && diemHeSO.year === year).firstOption()

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[DiemHeSoDto] = {
    var query = for {
      diemHeSo <- this
      nhanVien <- diemHeSo.nhanVien
      phongBan <- nhanVien.phongBan
    } yield (diemHeSo, nhanVien, phongBan)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(tuple => {
        val (diemHeSo, nhanVien, phongBan) = tuple
        filter.property match {
          case "nhanVien.firstName" => nhanVien.firstName.toLowerCase like filter.asLikeValue
          case "year" => diemHeSo.year === filter.asInt
          case "donViId" => phongBan.donViId === filter.asLong
          case "phongBanId" => phongBan.id === filter.asLong
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(tuple => {
        val (diemHeSo, nhanVien, phongBan) = tuple
        sort.property match {
          case "nhanVien.fullName" => orderColumn(sort.direction, nhanVien.firstName)
          case "phongBan.name" => orderColumn(sort.direction, phongBan.name)
          case "year" => orderColumn(sort.direction, diemHeSo.year)
          case "heSo" => orderColumn(sort.direction, diemHeSo.heSo)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(DiemHeSoDto(_))

    ExtGirdDto[DiemHeSoDto](
      total = totalRow,
      data = rows
    )
  }

  implicit val diemHeSoJsonFormat = Json.format[DiemHeSo]

}