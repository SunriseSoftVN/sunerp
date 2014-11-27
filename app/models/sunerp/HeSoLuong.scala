package models.sunerp

import dtos.{HeSoLuongDto, ExtGirdDto, PagingDto}
import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.{Json, Writes}
import play.api.data.format.Formats._
import utils.DateTimeUtils

import scala.slick.lifted.Tag

/**
 * Created by dungvn3000 on 07/11/2014.
 */
case class HeSoLuong(
                      id: Option[Long] = None,
                      nhanVienId: Long,
                      value: Double,
                      month: Int,
                      year: Int
                      ) extends WithId[Long]

class HeSoLuongs(tag: Tag) extends AbstractTable[HeSoLuong](tag, "hesoluong") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def value = column[Double]("value", O.NotNull)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def nhanVien = foreignKey("he_so_luong_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  override def * = (id.?, nhanVienId, value, month, year) <>(HeSoLuong.tupled, HeSoLuong.unapply)
}

object HeSoLuongs extends AbstractQuery[HeSoLuong, HeSoLuongs](new HeSoLuongs(_)) {

  def copyFormOldTable(implicit session: Session): Unit = {
    if (countAll == 0) {
      val nhanViens = NhanViens.all
      val year = DateTimeUtils.currentYear
      val month = DateTimeUtils.currentMonth
      nhanViens.foreach(nhanVien => {
        val heSoLuong = HeSoLuong(
          nhanVienId = nhanVien.getId,
          value = nhanVien.heSoLuong,
          month = month,
          year = year
        )
        save(heSoLuong)
      })
    }
  }

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[HeSoLuongDto] = {
    var query = for {
      heSoLuong <- this
      nhanVien <- heSoLuong.nhanVien
      phongBan <- nhanVien.phongBan
    } yield (heSoLuong, nhanVien, phongBan)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(table => {
        val (heSoLuong, nhanVien, phongBan) = table
        filter.property match {
          case "nameOrMaNv" => nhanVien.firstName.toLowerCase.like(filter.asLikeValue) || nhanVien.maNv.toLowerCase.like(filter.asLikeValue)
          case "year" => heSoLuong.year === filter.asInt
          case "phongBanId" => phongBan.id === filter.asLong
          case "donViId" => phongBan.donViId === filter.asLong
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (heSoLuong, nhanVien, phongBan) = table
        sort.property match {
          case "nhanVien.maNv" => orderColumn(sort.direction, nhanVien.maNv)
          case "nhanVien.fullName" => orderColumn(sort.direction, nhanVien.lastName)
          case "phongBan.name" => orderColumn(sort.direction, phongBan.name)
          case "month" => orderColumn(sort.direction, heSoLuong.month)
          case "value" => orderColumn(sort.direction, heSoLuong.value)
          case "year" => orderColumn(sort.direction, heSoLuong.year)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(HeSoLuongDto.apply)

    ExtGirdDto[HeSoLuongDto](
      total = totalRow,
      data = rows
    )
  }

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "value" -> of[Double],
      "month" -> number,
      "year" -> number
    )(HeSoLuong.apply)(HeSoLuong.unapply)
  )

  implicit val heSoLuongJsonFormat = new Writes[HeSoLuong] {
    def writes(heSoLuong: HeSoLuong) = Json.obj(
      "id" -> heSoLuong.id,
      "nhanVienId" -> heSoLuong.nhanVienId,
      "value" -> heSoLuong.value,
      "month" -> heSoLuong.month,
      "year" -> heSoLuong.year
    )
  }

}
