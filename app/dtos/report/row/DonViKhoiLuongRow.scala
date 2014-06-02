package dtos.report.row

import scala.beans.BeanProperty
import java.util
import java.lang.Double
import dtos.report.TaskDto
import dtos.report.qlkh.TaskReportBean

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
  var taskDinhMuc: Double = null

  @BeanProperty
  var taskSoLan: Double = null

  @BeanProperty
  var quyKl: Double = null

  @BeanProperty
  var quyGio: Double = null

  def getConLaiKL: Double = if (quyKl != null && quyKl != null && quyKl > quyKl) {
    quyKl - xnKL
  } else null

  def getConLaiGio: Double = if (quyGio != null && xnGio != null && quyGio > xnGio) {
    quyGio - xnGio
  } else null

  @BeanProperty
  var xnKL: Double = _

  @BeanProperty
  var xnGio: Double = _

  @BeanProperty
  var khoiLuongCongViec = new util.HashMap[String, Double]()

  @BeanProperty
  var gioCongViec = new util.HashMap[String, Double]()

  var task: TaskDto = _
}

object DonViKhoiLuongRow {

  def apply(task: TaskDto, phongBanIds: List[Long],
            sumKL: Long => Double, sumKLByPhongBan: (Long, Long) => Double,
            sumGio: Long => Double, sumGioByPhongBang: (Long, Long) => Double,
            taskExternal: List[TaskReportBean]) = {
    val row = new DonViKhoiLuongRow
    row.task = task
    row.taskId = task.id
    row.taskName = task.name
    row.taskCode = task.code
    row.taskUnit = task.donVi
    if (task.dinhMuc > 0) {
      row.taskDinhMuc = task.dinhMuc
    }
    task.soLan.map(soLan => if (soLan > 0) row.taskSoLan = soLan)
    val xnKL = sumKL(task.id)
    val xnGio = sumGio(task.id)
    if (xnKL > 0) {
      row.xnKL = xnKL
    }
    if (xnGio > 0) {
      row.xnGio = xnGio
    }
    taskExternal.find(_.id == task.id).map(data => {
      data.khoiLuong.map(quyKl => row.quyKl = quyKl)
      row.quyGio = data.gio
    })

    for (phongBangId <- phongBanIds) {
      val kl = sumKLByPhongBan(task.id, phongBangId)
      val gio = sumGioByPhongBang(task.id, phongBangId)
      if (kl > 0) {
        row.khoiLuongCongViec.put(phongBangId.toString, kl)
      }
      if (gio > 0) {
        row.gioCongViec.put(phongBangId.toString, gio)
      }
    }
    row
  }

}
