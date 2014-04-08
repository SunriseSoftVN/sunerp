package dtos

import play.api.libs.json.{Writes, Json}
import models.sunerp._
import models.qlkh.{Tasks, Task}
import models.sunerp.NhanVien
import models.sunerp.SoPhanCongExt
import models.sunerp.SoPhanCong
import models.sunerp.PhongBan
import org.joda.time.{LocalDate, DateTime}

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
                          taskId: Long,
                          phongBanId: Long,
                          khoiLuong: Double,
                          gio: Double,
                          ghiChu: String,
                          soPhanCongExtId: Long,
                          ngayPhanCong: LocalDate,
                          soPhanCongExt: SoPhanCongExt,
                          nhanVien: NhanVien,
                          task: Task,
                          phongBan: PhongBan
                          )


object SoPhanCongDto {

  def apply(tuple: (SoPhanCong, SoPhanCongExt, NhanVien, PhongBan)) = {
    val (soPhanCong, soPhanCongExt, nhanVien, phongBan) = tuple
    new SoPhanCongDto(
      id = soPhanCong.id.get,
      nhanVienId = soPhanCong.nhanVienId,
      taskId = soPhanCong.taskId,
      phongBanId = soPhanCong.phongBanId,
      khoiLuong = soPhanCong.khoiLuong,
      gio = soPhanCong.gio,
      ghiChu = soPhanCong.ghiChu,
      soPhanCongExtId = soPhanCong.soPhanCongExtId,
      ngayPhanCong = soPhanCong.ngayPhanCong,
      soPhanCongExt = soPhanCongExt,
      nhanVien = nhanVien,
      task = soPhanCong.task,
      phongBan = phongBan
    )
  }

  implicit def jsonWrite = new Writes[SoPhanCongDto] {
    override def writes(o: SoPhanCongDto) = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "taskId" -> o.taskId,
      "phongBanId" -> o.phongBanId,
      "khoiLuong" -> o.khoiLuong,
      "gio" -> o.gio,
      "ghiChu" -> o.ghiChu,
      "soPhanCongExtId" -> o.soPhanCongExtId,
      "ngayPhanCong" -> o.ngayPhanCong.toString("MM-dd-yyyy"),
      "soPhanCongExt" -> SoPhanCongExts.soPhanCongExtJsonFormat.writes(o.soPhanCongExt),
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien),
      "task" -> Tasks.taskJsonFormat.writes(o.task),
      "phongBan" -> PhongBans.phongBanJsonFormat.writes(o.phongBan)
    )
  }
}