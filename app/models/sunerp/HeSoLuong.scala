package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.{Json, Writes}
import play.api.data.format.Formats._

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

class HeSoLuongs(tag: Tag)  extends AbstractTable[HeSoLuong](tag, "hesoluong") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def value = column[Double]("value", O.NotNull)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def nhanVien = foreignKey("he_so_luong_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  override def * = (id.?, nhanVienId, value, month, year) <>(HeSoLuong.tupled, HeSoLuong.unapply)
}

object HeSoLuongs extends AbstractQuery[HeSoLuong, HeSoLuongs](new HeSoLuongs(_)) {

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
