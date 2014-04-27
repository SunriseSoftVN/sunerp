package dtos.report.row

import scala.beans.BeanProperty
import java.util

/**
 * The Class BangChamCongRow.
 *
 * @author Nguyen Duc Dung
 * @since 4/23/14 4:27 PM
 *
 */
class BangChamCongRow {

  @BeanProperty
  var stt: java.lang.Integer = null

  @BeanProperty
  var tenNV: String = _

  @BeanProperty
  var hsl: java.lang.Double = null

  @BeanProperty
  var hop: java.lang.Double = null

  @BeanProperty
  var hocNH: java.lang.Double = null

  @BeanProperty
  var hocDH: java.lang.Integer = null

  @BeanProperty
  var gianTiep: java.lang.Integer = null

  @BeanProperty
  var phep: java.lang.Integer = null

  @BeanProperty
  var leTet: java.lang.Integer = null

  @BeanProperty
  var tongCacKhoanCong: java.lang.Double = null

  @BeanProperty
  var omDau: java.lang.Integer = null

  @BeanProperty
  var thaiSan: java.lang.Integer = null

  @BeanProperty
  var conOm: java.lang.Integer = null

  @BeanProperty
  var taiNanLaoDong: java.lang.Integer = null

  @BeanProperty
  var tongCacKhoanTru: java.lang.Integer = null

  @BeanProperty
  var phuCapLamDem: java.lang.Integer = null

  @BeanProperty
  var trucBHLD: java.lang.Integer = null

  @BeanProperty
  var phuCapDocHai: java.lang.Integer = null

  @BeanProperty
  var giuaCa: java.lang.Integer = null

  @BeanProperty
  var tongGioCong: java.lang.Double = null

  @BeanProperty
  var diemHeSo: java.lang.Double = null

  @BeanProperty
  var xepLoai: String = _

  @BeanProperty
  var ghiChu: String = _

  @BeanProperty
  var congSanPham: java.lang.Double = null

  @BeanProperty
  var khoiLuongCongViec = new util.HashMap[String, String]()

}
