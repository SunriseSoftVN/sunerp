package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._

/**
 * The Class KhoiDonVi.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:11 AM
 *
 */
case class KhoiDonVi(
                      id: Option[Long] = None,
                      name: String,
                      companyId: Long
                      ) extends WithId[Long]

class KhoiDonVis(tag: Tag) extends AbstractTable[KhoiDonVi](tag, "khoi_don_vi") {

  def name = column[String]("name", O.NotNull)

  def companyId = column[Long]("companyId", O.NotNull)

  def company = foreignKey("company_khoi_don_vi_fk", companyId, Companies)(_.id)

  def * = (id.?, name, companyId) <>(KhoiDonVi.tupled, KhoiDonVi.unapply)
}

object KhoiDonVis extends AbstractQuery[KhoiDonVi, KhoiDonVis](new KhoiDonVis(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(minLength = 4),
      "companyId" -> longNumber
    )(KhoiDonVi.apply)(KhoiDonVi.unapply)
  )

}