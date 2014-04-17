package dtos.report

import scalaz._
import Scalaz._
import scala.collection.JavaConverters._

/**
 * The Class PhongBanDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:54 PM
 *
 */
case class PhongBanDto(id: Long, name: String, khoiLuongs: List[KhoiLuongDto] = Nil) {

  lazy val tasks = khoiLuongs.map(_.task)

  def reportRows() = {
    val groupByTaskId = khoiLuongs.groupBy(_.task.id)
    val rows = for (taskId <- groupByTaskId.keys) yield {
      val task = tasks.find(_.id == taskId) err "The task can't be not found."
      val khoiLuongs = groupByTaskId(taskId)
      val row = new PhongBanKhoiLuongRow()
      row.setTaskId(task.id)
      row.setTaskCode(task.code)
      row.setTaskName(task.name)
      row.setTaskUnit(task.unit)
      row.setTotalKhoiLuong(khoiLuongs.map(_.khoiLuong).sum)
      //nhom khoi luong theo ngay
      val khoiLuongGroupByDate = khoiLuongs.groupBy(_.ngayPhanCong)
      for (ngayPhanCong <- khoiLuongGroupByDate.keys) {
        //tong khoi luong cua mot cong viec, trong mot ngay
        val day = ngayPhanCong.getDayOfMonth
        val khoiLuongTrongNgay = khoiLuongGroupByDate(ngayPhanCong).map(_.khoiLuong).sum
        row.getKhoiLuongCongViec.put(day.toString, khoiLuongTrongNgay)
      }
      row
    }
    rows.toList.sortBy(_.taskCode)
  }

  /**
   * for java api
   * @return
   */
  def javaReportRows() = reportRows().asJavaCollection
}
