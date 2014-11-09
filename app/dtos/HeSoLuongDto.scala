package dtos

import models.sunerp._
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * Created by dungvn3000 on 07/11/2014.
 */
case class HeSoLuongDto(
                         id: Long,
                         nhanVienId: Long,
                         value: Double,
                         month: Int,
                         year: Int,
                         nhanVien: NhanVien
                         )


object HeSoLuongDto {

  def apply(tuple: (HeSoLuong, NhanVien)) = {
    val (heSoLuong, nhanVien) = tuple
    new HeSoLuongDto(
      id = heSoLuong.id.get,
      nhanVienId = heSoLuong.nhanVienId,
      value = heSoLuong.value,
      month = heSoLuong.month,
      year = heSoLuong.year,
      nhanVien = nhanVien
    )
  }

  implicit def jsonWrite = new Writes[HeSoLuongDto] {
    override def writes(o: HeSoLuongDto): JsValue = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "value" -> o.value,
      "month" -> o.month,
      "year" -> o.year,
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien)
    )
  }
}