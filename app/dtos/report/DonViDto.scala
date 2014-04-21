package dtos.report

import dtos.report.row.DonViKhoiLuongRow
import dtos.report.qlkh.TaskReportBean

/**
 * The Class DonViDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/17/14 9:09 AM
 *
 */
case class DonViDto(
                     id: Long,
                     name: String,
                     taskExternal: List[TaskReportBean],
                     override val children: List[PhongBanDto] = Nil
                     ) extends KhoiLuongContainer[DonViKhoiLuongRow] {

  val phongBanIds = children.map(_.id)

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def reportRows: List[DonViKhoiLuongRow] = tasks
    .map(
      DonViKhoiLuongRow(_, phongBanIds, sumKL, sumKLByChildId, sumGio, sumGioByChildId, taskExternal)
    ).sortBy(_.taskCode)
}
