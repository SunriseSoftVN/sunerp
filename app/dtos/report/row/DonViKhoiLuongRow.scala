package dtos.report.row

import scala.beans.BeanProperty
import java.util
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
  var taskDinhMuc: Double = _

  @BeanProperty
  var taskSoLan: Double = _

  @BeanProperty
  var quyKl: Double = _

  @BeanProperty
  var quyGio: Double = _

  @BeanProperty
  lazy val conLaiKL: Double = if (quyKl > xnKL) {
    quyKl - xnKL
  } else 0d

  @BeanProperty
  lazy val conLaiGio: Double = if (quyGio > xnGio) {
    quyGio - xnGio
  } else 0d

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
    row.taskDinhMuc = task.dinhMuc
    row.taskSoLan = task.soLan.getOrElse(0d)
    row.xnKL = sumKL(task.id)
    row.xnGio = sumGio(task.id)

    taskExternal.find(_.id == task.id).map(data => {
      row.quyKl = data.khoiLuong.getOrElse(0)
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
