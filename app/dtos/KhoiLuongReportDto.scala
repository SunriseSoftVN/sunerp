package dtos

import scala.beans.BeanProperty
import java.util

/**
 * The Class KhoiLuongReportDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 12:53 PM
 *
 */
class KhoiLuongReportDto {

  @BeanProperty
  var taskName: String = _

  @BeanProperty
  var taskCode: String = _

  @BeanProperty
  var taskUnit: String = _

  @BeanProperty
  var total: Double = _

  @BeanProperty
  var khoiLuongCongViec = new util.HashMap[String, Double]()
}
