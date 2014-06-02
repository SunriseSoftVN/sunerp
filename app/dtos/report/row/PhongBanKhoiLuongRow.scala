package dtos.report.row

import scala.beans.BeanProperty
import java.util
import dtos.report.TaskDto
import dtos.report.qlkh.TaskReportBean

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
  var taskDinhMuc: Double = _

  @BeanProperty
  var taskSoLan: Double = _

  @BeanProperty
  var totalKhoiLuong: Double = _

  @BeanProperty
  var totalGio: Double = _

  @BeanProperty
  var quyKl: Double = _

  @BeanProperty
  var quyGio: Double = _

  @BeanProperty
  var khoiLuongCongViec = new util.HashMap[String, Double]()

  var task: TaskDto = _
}

object PhongBanKhoiLuongRow {

  def apply(task: TaskDto,
            sumKL: Long => Double,
            sumGio: Long => Double,
            sumByDay: (Long, Int) => Double,
            taskExternal: List[TaskReportBean]) = {
    val row = new PhongBanKhoiLuongRow
    row.task = task
    row.setTaskId(task.id)
    row.setTaskName(task.name)
    row.setTaskUnit(task.donVi)
    row.setTaskCode(task.code)
    row.setTaskDinhMuc(task.dinhMuc)
    row.setTaskSoLan(task.soLan.getOrElse(0d))
    row.setTotalKhoiLuong(sumKL(task.id))
    row.setTotalGio(sumGio(task.id))

    taskExternal.find(_.id == task.id).map(data => {
      row.quyKl = data.khoiLuong.getOrElse(0)
      row.quyGio = data.gio
    })

    for (i <- 1 to 31) {
      val daily = sumByDay(task.id, i)
      if (daily > 0) row.getKhoiLuongCongViec.put(i.toString, daily)
    }
    row
  }

}