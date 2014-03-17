package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * The Class CompanySetting.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 10:01 AM
 *
 */
case class CompanySetting(
                           id: Option[Long] = None,
                           companyId: Long,
                           luongToiThieu: Long
                           ) extends WithId[Long]

class CompanySettings(tag: Tag) extends AbstractTable[CompanySetting](tag, "companySetting") {

  def companyId = defColumn[Long]("companyId", O.NotNull)

  def company = foreignKey("company_company_setting_fk", companyId, Companies)(_.id)

  def luongToiThieu = defColumn[Long]("luongToiThieu", O.NotNull)

  def * = (id.?, companyId, luongToiThieu) <>(CompanySetting.tupled, CompanySetting.unapply)
}

object CompanySettings extends AbstractQuery[CompanySetting, CompanySettings](new CompanySettings(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "companyId" -> longNumber,
      "luongToiThieu" -> longNumber
    )(CompanySetting.apply)(CompanySetting.unapply)
  )

  implicit val companySettingJsonFormat = Json.format[CompanySetting]

}