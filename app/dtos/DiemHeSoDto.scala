package dtos

import models.sunerp.{PhongBan, NhanViens, DiemHeSo, NhanVien}
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * The Class XepLoaiDto.
 *
 * @author Nguyen Duc Dung
 * @since 5/9/14 5:38 AM
 *
 */
case class DiemHeSoDto(
                        id: Long,
                        nhanVienId: Long,
                        year: Int,
                        heSo: Double,
                        nhanVien: NhanVien
                        )


object DiemHeSoDto {

  def apply(tuple: (DiemHeSo, NhanVien, PhongBan)) = {
    val (diemHeSo, nhanVien, phongBan) = tuple

    new DiemHeSoDto(
      id = diemHeSo.id.get,
      nhanVienId = diemHeSo.nhanVienId,
      year = diemHeSo.year,
      heSo = diemHeSo.heSo,
      nhanVien = nhanVien
    )
  }

  implicit val jsonWrite = new Writes[DiemHeSoDto] {
    override def writes(o: DiemHeSoDto): JsValue = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "year" -> o.year,
      "heSo" -> o.heSo,
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien)
    )
  }

}