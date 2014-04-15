package dtos

import scala.beans.BeanProperty
import java.util

/**
 * The Class PhongBanKhoiLuongRow.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 12:53 PM
 *
 */
class PhongBanKhoiLuongRow {

  @BeanProperty
  var taskId: Long = _

  @BeanProperty
  var taskName: String = _

  @BeanProperty
  var taskCode: String = _

  @BeanProperty
  var taskUnit: String = _

  @BeanProperty
  var totalKhoiLuong: Double = _

  @BeanProperty
  var totalGio: Double = _

  @BeanProperty
  var khoiLuongCongViec = new util.HashMap[String, Double]()

  @BeanProperty
  var gioCongViec = new util.HashMap[String, Double]()
}
