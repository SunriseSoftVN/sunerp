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

  val phongBanIds = phongBans.map(_.id)

  def sumKLByPhongBanId(taskId: Long, phongBangId: Long): Double = phongBans
    .filter(_.id == phongBangId).foldLeft(0d)((kl, phongBan) => phongBan.sumKL(taskId) + kl)

  def sumGioByPhongBanId(taskId: Long, phongBangId: Long): Double = phongBans
    .filter(_.id == phongBangId).foldLeft(0d)((gio, phongBan) => phongBan.sumGio(taskId) + gio)

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def reportRows: List[DonViKhoiLuongRow] = tasks
    .map(DonViKhoiLuongRow(_, phongBanIds, sumKL, sumKLByPhongBanId, sumGio, sumGioByPhongBanId)).sortBy(_.taskCode)
}
