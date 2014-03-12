package dtos

import play.api.libs.json.{Writes, Json}
import models.sunerp._
import models.qlkh.{Tasks, Task}
import models.sunerp.NhanVien
import models.sunerp.SoPhanCongExt
import models.sunerp.SoPhanCong
import models.sunerp.PhongBang
import org.joda.time.DateTime

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
                          phongBangId: Long,
                          khoiLuong: Double,
                          gio: Double,
                          ghiChu: String,
                          soPhanCongExtId: Long,
                          ngayPhanCong: DateTime,
                          soPhanCongExt: SoPhanCongExt,
                          nhanVien: NhanVien,
                          task: Task,
                          phongBang: PhongBang
                          )


object SoPhanCongDto {

  def apply(tuple: (SoPhanCong, SoPhanCongExt, NhanVien, PhongBang)) = {
    val (soPhanCong, soPhanCongExt, nhanVien, phongBang) = tuple
    new SoPhanCongDto(
      id = soPhanCong.id.get,
      nhanVienId = soPhanCong.nhanVienId,
      taskId = soPhanCong.taskId,
      phongBangId = soPhanCong.phongBangId,
      khoiLuong = soPhanCong.khoiLuong,
      gio = soPhanCong.gio,
      ghiChu = soPhanCong.ghiChu,
      soPhanCongExtId = soPhanCong.soPhanCongExtId,
      ngayPhanCong = soPhanCong.ngayPhanCong,
      soPhanCongExt = soPhanCongExt,
      nhanVien = nhanVien,
      task = soPhanCong.task,
      phongBang = phongBang
    )
  }

  implicit def jsonWrite = new Writes[SoPhanCongDto] {
    override def writes(o: SoPhanCongDto) = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "taskId" -> o.taskId,
      "phongBangId" -> o.phongBangId,
      "khoiLuong" -> o.khoiLuong,
      "gio" -> o.gio,
      "ghiChu" -> o.ghiChu,
      "soPhanCongExtId" -> o.soPhanCongExtId,
      "ngayPhanCong" -> o.ngayPhanCong,
      "soPhanCongExt" -> SoPhanCongExts.soPhanCongExtJsonFormat.writes(o.soPhanCongExt),
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien),
      "task" -> Tasks.taskJsonFormat.writes(o.task),
      "phongBang" -> PhongBangs.phongBangJsonFormat.writes(o.phongBang)
    )
  }
}