package dtos.report

import scala.collection.JavaConverters._

/**
 * The Class KhoiLuongContainer.
 *
 * @author Nguyen Duc Dung
 * @since 4/18/14 9:56 AM
 *
 */
abstract class KhoiLuongContainer[R] {

  val id: Long
  val name: String
  val children: List[_ <: KhoiLuongContainer[_]] = List.empty
  protected val _khoiLuongs: List[KhoiLuongDto] = List.empty

  lazy final val khoiLuongs : List[KhoiLuongDto] = if(children.isEmpty) _khoiLuongs else children.flatMap(_.khoiLuongs)

  //unique task list
  lazy val tasks: List[TaskDto] = khoiLuongs.map(_.task).distinct

  /**
   * do sum "khoiluong" of the task.
   * @param taskId
   */
  def sumKL(taskId: Long) = khoiLuongs
    .par
    .filter(_.task.id == taskId).foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)

  def sumKLByDay(taskId: Long, dayOfMonth: Int) = khoiLuongs
    .par
    .filter(khoiLuong => khoiLuong.task.id == taskId && khoiLuong.ngayPhanCong.getDayOfMonth == dayOfMonth)
    .foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)

  def sumGio(taskId: Long) = khoiLuongs
    .par
    .filter(_.task.id == taskId).foldLeft(0d)((gio, dto) => dto.gio + gio)

  def sumKLByChildId(taskId: Long, childId: Long): Double = children
    .par
    .filter(_.id == childId).foldLeft(0d)((kl, child) => child.sumKL(taskId) + kl)

  def sumGioByChildId(taskId: Long, childId: Long): Double = children
    .par
    .filter(_.id == childId).foldLeft(0d)((gio, child) => child.sumGio(taskId) + gio)

  /**
   * Tranfrom data to report row.
   * @return
   */
  def reportRows: List[R]

  def javaReportRows() = reportRows.asJavaCollection
}
