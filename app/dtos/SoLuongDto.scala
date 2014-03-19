package dtos

import org.joda.time.DateTime
import models.sunerp._
import play.api.libs.json.{Json, JsValue, Writes}
import models.sunerp.NhanVien
import models.sunerp.CacKhoangCong
import models.sunerp.SoLuong
import models.sunerp.CacKhoangTru

/**
 * The Class SoLuongDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 11:44 AM
 *
 */
case class SoLuongDto(
                       id: Long,
                       nhanVienId: Long,
                       nhanVien: NhanVien,
                       chucVu: String,
                       heSoLuong: Double,
                       luongNd: Long,
                       k2: Double,
                       luongSP: Long,
                       luongTgCong: Double,
                       luongTgTien: Long,
                       cacKhoangCongId: Long,
                       cacKhoangCong: CacKhoangCong,
                       cacKhoangTruId: Long,
                       cacKhoangTru: CacKhoangTru,
                       createdDate: DateTime
                       )

object SoLuongDto {
  def apply(tuple: (SoLuong, NhanVien, CacKhoangCong, CacKhoangTru)) = {
    val (soLuong, nhanVien, cacKhoangCong, cacKhoangTru) = tuple
    new SoLuongDto(
      id = soLuong.id.get,
      nhanVienId = soLuong.nhanVienId,
      chucVu = soLuong.chucVu,
      heSoLuong = soLuong.heSoLuong,
      luongNd = soLuong.luongNd,
      k2 = soLuong.k2,
      luongSP = soLuong.luongSP,
      luongTgCong = soLuong.luongTgCong,
      luongTgTien = soLuong.luongTgTien,
      cacKhoangCongId = soLuong.cacKhoangCongId,
      cacKhoangTruId = soLuong.cacKhoangTruId,
      createdDate = soLuong.createdDate,
      nhanVien = nhanVien,
      cacKhoangCong = cacKhoangCong,
      cacKhoangTru = cacKhoangTru
    )
  }

  implicit val jsonWrite = new Writes[SoLuongDto] {
    override def writes(o: SoLuongDto): JsValue = Json.obj(
      "id" -> o.id,
      "nhanVienId" -> o.nhanVienId,
      "chucVu" -> o.chucVu,
      "heSoLuong" -> o.heSoLuong,
      "luongNd" -> o.luongNd,
      "k2" -> o.k2,
      "luongSP" -> o.luongSP,
      "luongTgCong" -> o.luongTgCong,
      "luongTgTien" -> o.luongTgTien,
      "cacKhoangCongId" -> o.cacKhoangCongId,
      "cacKhoangTruId" -> o.cacKhoangTruId,
      "createdDate" -> o.createdDate,
      "nhanVien" -> NhanViens.nhanVienJsonFormat.writes(o.nhanVien),
      "cacKhoangCong" -> CacKhoanCongs.cacKhoanCongJsonFormat.writes(o.cacKhoangCong),
      "cacKhoangTru" -> CacKhoangTrus.cacKhoanTruJsonFormat.writes(o.cacKhoangTru)
    )
  }
}