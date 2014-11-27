package models.sunerp

import dtos.{TrangThaiNhanVienDto, ExtGirdDto, PagingDto}
import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.{Json, Writes}

import scala.slick.lifted.Tag

/**
 * Created by dungvn3000 on 07/11/2014.
 */
case class TrangThaiNhanVien(
                              id: Option[Long] = None,
                              nhanVienId: Long,
                              nghiViec: Boolean,
                              month: Int,
                              year: Int
                              ) extends WithId[Long]

class TrangThaiNhanViens(tag: Tag) extends AbstractTable[TrangThaiNhanVien](tag, "trangthainhanvien") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nghiViec = column[Boolean]("nghiViec", O.NotNull)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def nhanVien = foreignKey("trang_thai_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  override def * = (id.?, nhanVienId, nghiViec, month, year) <>(TrangThaiNhanVien.tupled, TrangThaiNhanVien.unapply)
}

object TrangThaiNhanViens extends AbstractQuery[TrangThaiNhanVien, TrangThaiNhanViens](new TrangThaiNhanViens(_)) {

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[TrangThaiNhanVienDto] = {
    var query = for {
      trangThai <- this
      nhanVien <- trangThai.nhanVien
      phongBan <- nhanVien.phongBan
    } yield (trangThai, nhanVien, phongBan)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(table => {
        val (trangThai, nhanVien, phongBan) = table
        filter.property match {
          case "nameOrMaNv" => nhanVien.firstName.toLowerCase.like(filter.asLikeValue) || nhanVien.maNv.toLowerCase.like(filter.asLikeValue)
          case "year" => trangThai.year === filter.asInt
          case "phongBanId" => phongBan.id === filter.asLong
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (trangThai, nhanVien, phongBan) = table
        sort.property match {
          case "nhanVien.fullName" => orderColumn(sort.direction, nhanVien.lastName)
          case "month" => orderColumn(sort.direction, trangThai.month)
          case "nghiViec" => orderColumn(sort.direction, trangThai.nghiViec)
          case "year" => orderColumn(sort.direction, trangThai.year)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(TrangThaiNhanVienDto.apply)

    ExtGirdDto[TrangThaiNhanVienDto](
      total = totalRow,
      data = rows
    )
  }

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "nghiViec" -> boolean,
      "month" -> number,
      "year" -> number
    )(TrangThaiNhanVien.apply)(TrangThaiNhanVien.unapply)
  )

  implicit val trangThaiJsonFormat = new Writes[TrangThaiNhanVien] {
    def writes(trangThai: TrangThaiNhanVien) = Json.obj(
      "id" -> trangThai.id,
      "nhanVienId" -> trangThai.nhanVienId,
      "nghiViec" -> trangThai.nghiViec,
      "month" -> trangThai.month,
      "year" -> trangThai.year
    )
  }

}
