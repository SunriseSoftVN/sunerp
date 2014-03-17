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
 * The Class SoLuong.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 3:37 PM
 *
 */
case class SoLuong(
                    id: Option[Long] = None,
                    nhanVienId: Long,
                    chucVu: String,
                    heSoLuong: Double,
                    luongNd: Long,
                    k2: Double,
                    luongSP: Long,
                    luongTgCong: Double,
                    luongTgTien: Long,
                    cacKhoangCongId: Long,
                    cacKhoangTruId: Long,
                    createdDate: DateTime
                    ) extends WithId[Long]

class SoLuongs(tag: Tag) extends AbstractTable[SoLuong](tag, "soLuong") {

  def nhanVienId = defColumn[Long]("nhanVienId", O.NotNull)

  def nhanvien = foreignKey("nhanvien_so_luong_fk", nhanVienId, NhanViens)(_.id)

  def chucVu = defColumn[String]("chucVu", O.NotNull)

  def heSoLuong = defColumn[Double]("heSoLuong", O.NotNull)

  def luongNd = defColumn[Long]("luongNd", O.NotNull)

  def k2 = defColumn[Double]("k2", O.NotNull)

  def luongSP = defColumn[Long]("luongSP", O.NotNull)

  def luongTgCong = defColumn[Double]("luongTgCong", O.NotNull)

  def luongTgTien = defColumn[Long]("luongTgTien", O.NotNull)

  def cacKhoangCongId = defColumn[Long]("cacKhoangCongId", O.NotNull)

  def cacKhoangCong = foreignKey("cac_khoang_cong_so_luong_fk", cacKhoangCongId, CacKhoanCongs)(_.id)

  def cacKhoangTruId = defColumn[Long]("cacKhoangTruId", O.NotNull)

  def cacKhoangTru = foreignKey("cac_khoang_tru_so_luong_fk", cacKhoangTruId, CacKhoangTrus)(_.id)

  def createdDate = defColumn[DateTime]("createdDate", O.NotNull)

  def * = (id.?, nhanVienId, chucVu, heSoLuong, luongNd, k2, luongSP, luongTgCong, luongTgTien, cacKhoangCongId, cacKhoangTruId, createdDate) <>(SoLuong.tupled, SoLuong.unapply)
}

object SoLuongs extends AbstractQuery[SoLuong, SoLuongs](new SoLuongs(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "chucVu" -> text(minLength = 4),
      "heSoLuong" -> of[Double],
      "luongNd" -> longNumber,
      "k2" -> of[Double],
      "luongSP" -> longNumber,
      "luongTgCong" -> of[Double],
      "luongTgTien" -> longNumber,
      "cacKhoangCongId" -> longNumber,
      "cacKhoangTruId" -> longNumber,
      "createdDate" -> jodaDate
    )(SoLuong.apply)(SoLuong.unapply)
  )

  implicit val soLuongJsonFormat = Json.format[SoLuong]

}