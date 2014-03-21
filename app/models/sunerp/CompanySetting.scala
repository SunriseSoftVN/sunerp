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
                           luongToiThieu: Long
                           ) extends WithId[Long]

class CompanySettings(tag: Tag) extends AbstractTable[CompanySetting](tag, "companySetting") {

  def luongToiThieu = column[Long]("luongToiThieu", O.NotNull)

  def * = (id.?, luongToiThieu) <>(CompanySetting.tupled, CompanySetting.unapply)
}

object CompanySettings extends AbstractQuery[CompanySetting, CompanySettings](new CompanySettings(_)) {
  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "luongToiThieu" -> longNumber
    )(CompanySetting.apply)(CompanySetting.unapply)
  )

  implicit val companySettingJsonFormat = Json.format[CompanySetting]
}