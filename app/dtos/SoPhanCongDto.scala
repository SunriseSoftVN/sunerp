package dtos

import play.api.libs.json.{Writes, Json}
import models.sunerp._
import models.qlkh.Tasks
import models.sunerp.NhanVien
import models.sunerp.SoPhanCongExt
import models.sunerp.SoPhanCong
import models.sunerp.PhongBan
import org.joda.time.LocalDate

/**
 * The Class SoPhanCongDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 7:01 PM
 *
 */
case class SoPhanCongDto(
                          id: Long,
                          nhanVienId: Long,
                          taskId: Option[Long],
                          taskName: String,
                          phongBanId: Long,
                          khoiLuong: Double,
                          gio: Double,
                          ghiChu: String,
                          soPhanCongExtId: Long,
                          ngayPhanCong: LocalDate,
                          soPhanCongExt: SoPhanCongExt,
                          nhanVien: NhanVien,
                          phongBan: PhongBan
                          )


object SoPhanCongDto {

  def apply(tuple: (SoPhanCong, SoPhanCongExt, NhanVien, PhongBan)) = {
    val (soPhanCong, soPhanCongExt, nhanVien, phongBan) = tuple
    new SoPhanCongDto(
      id = soPhanCong.id.get,
      nhanVienId = soPhanCong.nhanVienId,
      taskId = soPhanCong.taskId,
      taskName = soPhanCong.taskName,
      phongBanId = soPhanCong.phongBanId,
      khoiLuong = soPhanCong.khoiLuong,
      gio = soPhanCong.gio,
      ghiChu = soPhanCong.ghiChu,
      soPhanCongExtId = soPhanCong.soPhanCongExtId,
      ngayPhanCong = soPhanCong.ngayPhanCong,
      soPhanCongExt = soPhanCongExt,
      nhanVien = nhanVien,
      phongBan = phongBan
    )
  }

  implicit def jsonWrite = new Writes[SoPhanCongDto] {
    override def writes(o: SoPhanCongDto) = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "taskId" -> o.taskId,
      "taskName" -> o.taskName,
      "phongBanId" -> o.phongBanId,
      "khoiLuong" -> o.khoiLuong,
      "gio" -> o.gio,
      "ghiChu" -> o.ghiChu,
      "soPhanCongExtId" -> o.soPhanCongExtId,
      "ngayPhanCong" -> o.ngayPhanCong.toString("MM-dd-yyyy"),
      "soPhanCongExt" -> SoPhanCongExts.soPhanCongExtJsonFormat.writes(o.soPhanCongExt),
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien),
      "phongBan" -> PhongBans.phongBanJsonFormat.writes(o.phongBan)
    )
  }
}