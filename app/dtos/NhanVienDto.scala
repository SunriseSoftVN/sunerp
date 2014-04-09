package dtos

import models.sunerp._
import play.api.libs.json.{Json, JsValue, Writes}
import models.sunerp.NhanVien
import models.sunerp.ChucVu
import models.sunerp.PhongBan
import models.sunerp.PhongBans

/**
 * The Class NhanVienDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/20/14 8:39 AM
 *
 */
case class NhanVienDto(
                        id: Long,
                        maNv: String,
                        password: String,
                        firstName: String,
                        lastName: String,
                        heSoLuong: Double,
                        chucVuId: Long,
                        chucVu: ChucVu,
                        phongBanId: Long,
                        phongBan: PhongBan
                        )

object NhanVienDto {

  def apply(tuple: (NhanVien, ChucVu, PhongBan)) = {
    val (nhanVien, chucVu, phongBan) = tuple
    new NhanVienDto(
      id = nhanVien.id.get,
      maNv = nhanVien.maNv,
      password = nhanVien.password,
      firstName = nhanVien.firstName,
      lastName = nhanVien.lastName,
      heSoLuong = nhanVien.heSoLuong,
      chucVuId = nhanVien.chucVuId,
      phongBanId = nhanVien.phongBanId,
      chucVu = chucVu,
      phongBan = phongBan
    )
  }

  implicit val jsonWrite = new Writes[NhanVienDto] {
    override def writes(o: NhanVienDto): JsValue = Json.obj(
      "id" -> o.id,
      "maNv" -> o.maNv,
      "password" -> o.password,
      "firstName" -> o.firstName,
      "lastName" -> o.lastName,
      "heSoLuong" -> o.heSoLuong,
      "chucVuId" -> o.chucVuId,
      "phongBanId" -> o.phongBanId,
      "chucVu" -> ChucVus.chucVuJsonFormat.writes(o.chucVu),
      "phongBan" -> PhongBans.phongBanJsonFormat.writes(o.phongBan)
    )
  }

}