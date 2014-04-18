package dtos.report

import scala.collection.JavaConverters._
import dtos.report.row.PhongBanKhoiLuongRow

/**
 * The Class PhongBanDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:54 PM
 *
 */
case class PhongBanDto(id: Long, name: String, khoiLuongs: List[KhoiLuongDto] = Nil) extends KhoiLuongSupport[PhongBanKhoiLuongRow] {

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def reportRows = tasks.map(PhongBanKhoiLuongRow(_, sum, sumByDay)).sortBy(_.taskCode)

}
