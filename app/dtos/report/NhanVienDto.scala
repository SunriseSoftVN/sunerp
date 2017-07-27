package dtos.report

import models.sunerp.NhanVien

/**
 * The Class NhanVienDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:57 PM
 *
 */
case class NhanVienDto(id: Long, maNv: String, name: String)

object NhanVienDto {

  def apply(nhanVien: NhanVien) = new NhanVienDto(
    id = nhanVien.id.get,
    maNv = nhanVien.maNv,
    name = nhanVien.lastName + " " + nhanVien.firstName
  )

}