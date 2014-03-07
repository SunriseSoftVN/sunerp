package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._

/**
 * The Class PhongBang.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:20 AM
 *
 */
case class PhongBang(
                      id: Option[Long] = None,
                      donViId: Long,
                      name: String
                      ) extends WithId[Long]

class PhongBangs(tag: Tag) extends AbstractTable[PhongBang](tag, "phong_bang") {

  def donViId = column[Long]("donViId", O.NotNull)

  def donVi = foreignKey("doi_vi_phong_bang_fk", donViId, DonVis)(_.id)

  def name = column[String]("name", O.NotNull)

  def * = (id.?, donViId, name) <>(PhongBang.tupled, PhongBang.unapply)
}

object PhongBangs extends AbstractQuery[PhongBang, PhongBangs](new PhongBangs(_)) {

}