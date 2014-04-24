package dtos.report

import models.sunerp.NhanVien

/**
 * The Class NhanVienDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:57 PM
 *
 */
case class NhanVienDto(id: Long, name: String, heSoLuong: Double)

object NhanVienDto {

  def apply(nhanVien: NhanVien) = new NhanVienDto(
    id = nhanVien.id.get,
    name = nhanVien.lastName + " " + nhanVien.firstName,
    heSoLuong = nhanVien.heSoLuong
  )

}