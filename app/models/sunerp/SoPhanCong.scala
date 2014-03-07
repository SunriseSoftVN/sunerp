package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._

/**
 * The Class SoPhanCong.
 *
 * @author Nguyen Duc Dung
 * @since 3/7/14 5:42 PM
 *
 */
case class SoPhanCong(
                       id: Option[Long] = None,
                       nhanVienId: Long,
                       taskId: Long,
                       khoiLuong: Double,
                       gio: Double
                       ) extends WithId[Long]

class SoPhanCongs(tag: Tag) extends AbstractTable[SoPhanCong](tag, "so_phan_cong") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def taskId = column[Long]("taskId", O.NotNull)

  def khoiLuong = column[Double]("khoiLuong", O.NotNull)

  def gio = column[Double]("gio", O.NotNull)

  def * = (id.?, nhanVienId, taskId, khoiLuong, gio) <>(SoPhanCong.tupled, SoPhanCong.unapply)
}

object SoPhanCongs extends AbstractQuery[SoPhanCong, SoPhanCongs](new SoPhanCongs(_)) {

}
