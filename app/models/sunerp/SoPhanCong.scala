package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import org.joda.time.DateTime
import com.github.tototoshi.slick.MySQLJodaSupport._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json

/**
 * The Class SoPhanCong.
 *
 * @author Nguyen Duc Dung
 * @since 3/7/14 5:42 PM
 *
 */
case class SoPhanCong(
                       id: Option[Long] = None,
                       nhanVienId: Long,
                       taskId: Long,
                       phongBangId: Long,
                       khoiLuong: Double,
                       gio: Double,
                       ghiChu: String,
                       soPhanCongExtId: Long,
                       ngayPhanCong: DateTime
                       ) extends WithId[Long]

class SoPhanCongs(tag: Tag) extends AbstractTable[SoPhanCong](tag, "so_phan_cong") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanVien = foreignKey("nhan_vien_so_phan_cong_fk", nhanVienId, NhanViens)(_.id)

  def taskId = column[Long]("taskId", O.NotNull)

  def phongBangId = column[Long]("phongBangId", O.NotNull)

  def phongBang = foreignKey("phong_bang_so_phan_cong_fk", phongBangId, PhongBangs)(_.id)

  def khoiLuong = column[Double]("khoiLuong", O.NotNull)

  def gio = column[Double]("gio", O.NotNull)

  def ghiChu = column[String]("ghiChu")

  def soPhanCongExtId = column[Long]("soPhanCongExtId", O.NotNull)

  def soPhanCongExt = foreignKey("so_phan_cong_ext_so_phan_cong_fk", soPhanCongExtId, SoPhanCongExts)(_.id)

  def ngayPhanCong = column[DateTime]("ngayPhanCong", O.NotNull)

  def * = (id.?, nhanVienId, taskId, phongBangId, khoiLuong, gio, ghiChu, soPhanCongExtId, ngayPhanCong) <>(SoPhanCong.tupled, SoPhanCong.unapply)
}

object SoPhanCongs extends AbstractQuery[SoPhanCong, SoPhanCongs](new SoPhanCongs(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "taskId" -> longNumber,
      "phongBangId" -> longNumber,
      "khoiLuong" -> of[Double],
      "gio" -> of[Double],
      "ghiChu" -> text,
      "soPhanCongExtId" -> longNumber,
      "ngayPhanCong" -> jodaDate
    )(SoPhanCong.apply)(SoPhanCong.unapply)
  )

  implicit val soPhanCongJsonFormat = Json.format[SoPhanCong]

}
