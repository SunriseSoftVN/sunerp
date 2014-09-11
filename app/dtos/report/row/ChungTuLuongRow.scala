package dtos.report.row

import scala.beans.BeanProperty

/**
 * Created by dungvn3000 on 9/11/2014.
 */
class ChungTuLuongRow {
  @BeanProperty
  var stt: java.lang.Integer = null

  @BeanProperty
  var danhMuc: String = null

  @BeanProperty
  var donVi: String = null

  @BeanProperty
  var khoiLuong: java.lang.Double = null

  @BeanProperty
  var donGia: java.lang.Double = null

  @BeanProperty
  var khongThuong: java.lang.Double = null

  @BeanProperty
  var coThuong: java.lang.Double = null
}
