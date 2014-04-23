package dtos.report

import dtos.report.row.CongTyKhoiLuongRow

/**
 * The Class CongTyDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/17/14 9:10 AM
 *
 */
case class CongTyDto(override val children: List[DonViDto] = Nil) extends KhoiLuongContainer[CongTyKhoiLuongRow] {

  override val id: Long = -1 //fake id
  override val name: String = "Công ty"

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def khoiLuongRows: List[CongTyKhoiLuongRow] = ???

}
