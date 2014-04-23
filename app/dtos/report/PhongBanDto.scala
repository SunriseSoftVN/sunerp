package dtos.report

import dtos.report.row.PhongBanKhoiLuongRow
import dtos.report.qlkh.TaskReportBean

/**
 * The Class PhongBanDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:54 PM
 *
 */
case class PhongBanDto(
                        id: Long, name: String,
                        override val _khoiLuongs: List[KhoiLuongDto] = Nil,
                        taskExternal: List[TaskReportBean]
                        ) extends KhoiLuongContainer[PhongBanKhoiLuongRow] {

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def khoiLuongRows = tasks.map(PhongBanKhoiLuongRow(_, sumKL, sumGio, sumKLByDay, taskExternal)).sortBy(_.taskCode)
}
