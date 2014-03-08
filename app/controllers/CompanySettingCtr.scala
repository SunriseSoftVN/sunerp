package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{CompanySettings, CompanySetting}
import models.core.AbstractQuery
import play.api.libs.json.Writes

/**
 * The Class CompanySettingCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:19 PM
 *
 */
object CompanySettingCtr extends BaseCtr[CompanySetting, CompanySettings] with MainTemplate {
  override def editForm = CompanySettings.editForm
  override implicit val jsonWrite: Writes[CompanySetting] = CompanySettings.companySettingJsonFormat
  override val dao: AbstractQuery[CompanySetting, CompanySettings] = CompanySettings
  override val domainName: String = "companySetting"
}
