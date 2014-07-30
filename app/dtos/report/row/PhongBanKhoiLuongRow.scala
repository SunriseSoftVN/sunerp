package dtos.report.row

import scala.beans.BeanProperty
import java.util
import java.lang.Double
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
  var taskDinhMuc: Double = null

  @BeanProperty
  var taskSoLan: Double = null

  @BeanProperty
  var totalKhoiLuong: Double = null

  @BeanProperty
  var totalGio: Double = null

  @BeanProperty
  var quyKl: Double = null

  @BeanProperty
  var quyGio: Double = null

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

    if (task.dinhMuc > 0) {
      row.setTaskDinhMuc(task.dinhMuc)
    }
    task.soLan.map(soLan => if (soLan > 0) row.setTaskSoLan(soLan))

    row.setTotalKhoiLuong(sumKL(task.id))
    row.setTotalGio(sumGio(task.id))

    taskExternal.find(_.id == task.id).map(data => {
      data.khoiLuong.map(quyKl => row.quyKl = quyKl)
      row.quyGio = data.gio
    })

    val hideKlUnits = List("giờ", "hệ", "cv")
    if(hideKlUnits.contains(row.taskUnit)) {
      row.setTotalKhoiLuong(null)
    }

    for (i <- 1 to 31) {
      val daily = sumByDay(task.id, i)
      if (daily > 0) row.getKhoiLuongCongViec.put(i.toString, daily)
    }
    row
  }

}