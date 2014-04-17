package dtos.report

import scala.beans.BeanProperty
import java.util

/**
 * The Class DonViKhoiLuongRow.
 *
 * @author Nguyen Duc Dung
 * @since 4/17/14 4:10 PM
 *
 */
class DonViKhoiLuongRow {

  @BeanProperty
  var taskId: Long = _

  @BeanProperty
  var taskName: String = _

  @BeanProperty
  var taskCode: String = _

  @BeanProperty
  var taskUnit: String = _

  @BeanProperty
  var taskDinhMuc: Double = _

  @BeanProperty
  var taskSoLan: Double = _

  @BeanProperty
  var quyKl: Double = _

  @BeanProperty
  var quyGio: Double = _

  @BeanProperty
  var conLaiKL: Double = _

  @BeanProperty
  var conLaiGio: Double = _

  @BeanProperty
  var xnKL: Double = _

  @BeanProperty
  var xnGio: Double = _

  @BeanProperty
  var khoiLuongCongViec = new util.HashMap[String, Double]()

  @BeanProperty
  var gioCongViec = new util.HashMap[String, Double]()
}
