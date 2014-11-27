package dtos

import models.sunerp._
import play.api.libs.json.{JsValue, Json, Writes}

/**
 * Created by dungvn3000 on 27/11/2014.
 */
case class TrangThaiNhanVienDto(
                                 id: Long,
                                 nhanVienId: Long,
                                 nghiViec: Boolean,
                                 month: Int,
                                 year: Int,
                                 nhanVien: NhanVien,
                                 phongBan: PhongBan
                                 )

object TrangThaiNhanVienDto {

  def apply(tuple: (TrangThaiNhanVien, NhanVien, PhongBan)) = {
    val (trangThaiNhanVien, nhanVien, phongBan) = tuple
    new TrangThaiNhanVienDto(
      id = trangThaiNhanVien.id.get,
      nhanVienId = trangThaiNhanVien.nhanVienId,
      nghiViec = trangThaiNhanVien.nghiViec,
      month = trangThaiNhanVien.month,
      year = trangThaiNhanVien.year,
      nhanVien = nhanVien,
      phongBan = phongBan
    )
  }

  implicit def jsonWrite: Writes[TrangThaiNhanVienDto] = new Writes[TrangThaiNhanVienDto] {
    override def writes(o: TrangThaiNhanVienDto): JsValue = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "nghiViec" -> o.nghiViec,
      "month" -> o.month,
      "year" -> o.year,
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien),
      "phongBan" -> PhongBans.phongBanJsonFormat.writes(o.phongBan)
    )
  }

}