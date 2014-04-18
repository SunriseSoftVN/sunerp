package dtos.report

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
  var khoiLuongCongViec = new util.HashMap[String, Double]()
}

object PhongBanKhoiLuongRow {

  def apply(task: TaskDto, sum: Long => Double, sumByDay: (Long, Int) => Double) = {
    val row = new PhongBanKhoiLuongRow
    row.setTaskId(task.id)
    row.setTaskName(task.name)
    row.setTaskUnit(task.unit)
    row.setTaskCode(task.code)
    row.setTotalKhoiLuong(sum(task.id))
    for (i <- 1 to 31) {
      val daily = sumByDay(task.id, i)
      if (daily > 0) row.getKhoiLuongCongViec.put(i.toString, daily)
    }
    row
  }

}