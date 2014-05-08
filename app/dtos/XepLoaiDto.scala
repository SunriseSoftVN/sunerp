package dtos

import models.sunerp.{NhanViens, XepLoai, NhanVien}
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * The Class XepLoaiDto.
 *
 * @author Nguyen Duc Dung
 * @since 5/9/14 5:38 AM
 *
 */
case class XepLoaiDto(
                       id: Long,
                       nhanVienId: Long,
                       month: Int,
                       year: Int,
                       xepLoai: String,
                       nhanVien: NhanVien
                       )


object XepLoaiDto {
  def apply(tuple: (XepLoai, NhanVien)) = {
    val (xepLoai, nhanVien) = tuple
    new XepLoaiDto(
      id = xepLoai.id.get,
      nhanVienId = xepLoai.nhanVienId,
      month = xepLoai.month,
      year = xepLoai.year,
      xepLoai = xepLoai.xepLoai,
      nhanVien = nhanVien
    )
  }

  implicit val jsonWrite = new Writes[XepLoaiDto] {
    override def writes(o: XepLoaiDto): JsValue = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "month" -> o.month,
      "year" -> o.year,
      "xepLoai" -> o.xepLoai,
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien)
    )
  }
}