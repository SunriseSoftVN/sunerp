package dtos.report

import scala.collection.JavaConverters._

/**
 * The Class PhongBanDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:54 PM
 *
 */
case class PhongBanDto(id: Long, name: String, khoiLuongs: List[KhoiLuongDto] = Nil) {

  //unique task list
  lazy val tasks = khoiLuongs.map(_.task).distinct

  /**
   * do sum "khoiluong" of the task.
   * @param taskId
   */
  def sum(taskId: Long) = khoiLuongs.filter(_.task.id == taskId).map(_.khoiLuong).sum


  def sumByDay(taskId: Long, dayOfMonth: Int) = khoiLuongs
    .filter(khoiLuong => khoiLuong.task.id == taskId && khoiLuong.ngayPhanCong.getDayOfMonth == dayOfMonth)
    .map(_.khoiLuong)
    .sum

  /**
   * Tranfrom data to report row.
   * @return
   */
  def reportRows() = tasks.map(PhongBanKhoiLuongRow(_, sum, sumByDay)).sortBy(_.taskCode)

  /**
   * for java api
   * @return
   */
  def javaReportRows() = reportRows().asJavaCollection
}
