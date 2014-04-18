package dtos.report

import dtos.report.row.DonViKhoiLuongRow

/**
 * The Class DonViDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/17/14 9:09 AM
 *
 */
case class DonViDto(id: Long, name: String, phongBans: List[PhongBanDto] = Nil) extends KhoiLuongSupport[DonViKhoiLuongRow] {

  override val khoiLuongs: List[KhoiLuongDto] = phongBans.flatMap(_.khoiLuongs)

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def reportRows: List[DonViKhoiLuongRow] = tasks.map(DonViKhoiLuongRow(_, sum)).sortBy(_.taskCode)
}
