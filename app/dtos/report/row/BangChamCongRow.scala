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
  var stt: Int = _

  @BeanProperty
  var tenNV: String = _

  @BeanProperty
  var hsl: Double = _

  @BeanProperty
  var congSp: Double = _

  @BeanProperty
  var hop: Double = _

  @BeanProperty
  var hocNH: Double = _

  @BeanProperty
  var hocDH: Double = _

  @BeanProperty
  var gianTiep: Double = _

  @BeanProperty
  var phep: Int = _

  @BeanProperty
  var leTet: Int = _

  @BeanProperty
  var tongCacKhoanCong: Double = _

  @BeanProperty
  var omDau: Int = _

  @BeanProperty
  var thaiSan: Int = _

  @BeanProperty
  var conOm: Int = _

  @BeanProperty
  var taiNanLaoDong: Int = _

  @BeanProperty
  var tongCacKhoanTru: Double = _

  @BeanProperty
  var phuCapLamDem: Int = _

  @BeanProperty
  var trucBHLD: Int = _

  @BeanProperty
  var phuCapDocHai: Int = _

  @BeanProperty
  var giuaCa: Int = _

  @BeanProperty
  var tongGioCong: Double = _

  @BeanProperty
  var diemHeSo: Double = _

  @BeanProperty
  var xepLoai: String = _

  @BeanProperty
  var ghiChu: String = _

  @BeanProperty
  var congSanPham: Double = _

  @BeanProperty
  var khoiLuongCongViec = new util.HashMap[String, String]()

}
