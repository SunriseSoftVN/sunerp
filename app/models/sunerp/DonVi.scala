package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._

/**
 * The Class DonVi.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:20 AM
 *
 */
case class DonVi(
                  id: Option[Long] = None,
                  name: String,
                  companyId: Long,
                  khoiDonViId: Option[Long]
                  ) extends WithId[Long]

class DonVis(tag: Tag) extends AbstractTable[DonVi](tag, "donVi") {
  def name = column[String]("name", O.NotNull)

  def companyId = column[Long]("companyId", O.NotNull)

  def company = foreignKey("company_don_vi_fk", companyId, Companies)(_.id)

  def khoiDonViId = column[Long]("khoiDonViId", O.NotNull)

  def khoiDonVi = foreignKey("khoiDonVi_fk", khoiDonViId, KhoiDonVis)(_.id)

  def * = (id.?, name, companyId, khoiDonViId.?) <>(DonVi.tupled, DonVi.unapply)
}

object DonVis extends AbstractQuery[DonVi, DonVis](new DonVis(_)) {
  implicit val donViJsonFormat = Json.format[DonVi]

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(minLength = 4),
      "companyId" -> longNumber,
      "khoiDonViId" -> optional(longNumber)
    )(DonVi.apply)(DonVi.unapply)
  )
}