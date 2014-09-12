package dtos.report.row

import scala.beans.BeanProperty

/**
 * Created by dungvn3000 on 9/11/2014.
 */
class ChungTuLuongRow {
  @BeanProperty var stt: String = null
  @BeanProperty var danhMuc: String = null
  @BeanProperty var donVi: String = null
  var khoiLuong: Double = _
  var donGia: Double = _
  var khongThuong: Double = _
  var coThuong: Double = _

  def getKhoiLuong = if (khoiLuong > 0) khoiLuong else null
  def getDonGia = if (donGia > 0) donGia else null
  def getKhongThuong = if (khongThuong > 0) khongThuong else null
  def getCoThuong = if (coThuong > 0) coThuong else null
}
