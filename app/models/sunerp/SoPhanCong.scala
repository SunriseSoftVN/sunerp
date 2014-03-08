package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import org.joda.time.DateTime
import com.github.tototoshi.slick.MySQLJodaSupport._

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
                       lamDem: Boolean,
                       baoHoLaoDong: Boolean,
                       docHai: Boolean,
                       le: Boolean,
                       tet: Boolean,
                       thaiSan: Boolean,
                       dauOm: Boolean,
                       conOm: Boolean,
                       taiNanLd: Boolean,
                       hop: Boolean,
                       hocDaiHan: Boolean,
                       hocDotXuat: Boolean,
                       viecRieng: Boolean,
                       chuNhat: Boolean,
                       ghiChu: String,
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

  def lamDem = column[Boolean]("lamDem", O.NotNull, O.Default(false))

  def baoHoLaoDong = column[Boolean]("baoHoLaoDong", O.NotNull, O.Default(false))

  def docHai = column[Boolean]("docHai", O.NotNull, O.Default(false))

  def le = column[Boolean]("le", O.NotNull, O.Default(false))

  def tet = column[Boolean]("tet", O.NotNull, O.Default(false))

  def thaiSan = column[Boolean]("thaiSan", O.NotNull, O.Default(false))

  def dauOm = column[Boolean]("dauOm", O.NotNull, O.Default(false))

  def conOm = column[Boolean]("conOm", O.NotNull, O.Default(false))

  def taiNanLd = column[Boolean]("taiNanLd", O.NotNull, O.Default(false))

  def hop = column[Boolean]("hop", O.NotNull, O.Default(false))

  def hocDaiHan = column[Boolean]("hocDaiHan", O.NotNull, O.Default(false))

  def hocDotXuat = column[Boolean]("hocDotXuat", O.NotNull, O.Default(false))

  def viecRieng = column[Boolean]("viecRieng", O.NotNull, O.Default(false))

  def chuNhat = column[Boolean]("chuNhat", O.NotNull, O.Default(false))

  def ghiChu = column[String]("ghiChu")

  def ngayPhanCong = column[DateTime]("ngayPhanCong", O.NotNull)

  def * = (id.?, nhanVienId, taskId, phongBangId, khoiLuong, gio, lamDem, baoHoLaoDong, docHai, le, tet,
    thaiSan, dauOm, conOm, taiNanLd, hop, hocDaiHan, hocDotXuat, viecRieng, chuNhat, ghiChu, ngayPhanCong) <>(SoPhanCong.tupled, SoPhanCong.unapply)
}

object SoPhanCongs extends AbstractQuery[SoPhanCong, SoPhanCongs](new SoPhanCongs(_)) {

}
