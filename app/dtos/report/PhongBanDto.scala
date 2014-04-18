package dtos.report

import dtos.report.row.PhongBanKhoiLuongRow

/**
 * The Class PhongBanDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:54 PM
 *
 */
case class PhongBanDto(id: Long, name: String, override val _khoiLuongs: List[KhoiLuongDto] = Nil) extends KhoiLuongContainer[PhongBanKhoiLuongRow] {

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def reportRows = tasks.map(PhongBanKhoiLuongRow(_, sumKL, sumKLByDay)).sortBy(_.taskCode)

}
