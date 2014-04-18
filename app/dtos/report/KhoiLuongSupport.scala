package dtos.report

import scala.collection.JavaConverters._

/**
 * The Class KhoiLuongOp.
 *
 * @author Nguyen Duc Dung
 * @since 4/18/14 9:56 AM
 *
 */
trait KhoiLuongSupport[R] {

  val khoiLuongs: List[KhoiLuongDto]

  //unique task list
  lazy val tasks: List[TaskDto] = khoiLuongs.map(_.task).distinct

  /**
   * do sum "khoiluong" of the task.
   * @param taskId
   */
  def sumKL(taskId: Long) = khoiLuongs.par
    .filter(_.task.id == taskId).foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)


  def sumKLByDay(taskId: Long, dayOfMonth: Int) = khoiLuongs.par
    .filter(khoiLuong => khoiLuong.task.id == taskId && khoiLuong.ngayPhanCong.getDayOfMonth == dayOfMonth)
    .foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)

  def sumGio(taskId: Long) = khoiLuongs.par
    .filter(_.task.id == taskId).foldLeft(0d)((gio, dto) => dto.gio + gio)

  /**
   * Tranfrom data to report row.
   * @return
   */
  def reportRows: List[R]

  def javaReportRows() = reportRows.asJavaCollection
}
