package controllers

import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{BaseCtr, MainTemplate}
import dtos.PagingDto
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import models.core.AbstractQuery
import models.sunerp.{CompanySettings, CompanySetting, Companies, Company}
import play.api.data.Form
import play.api.db.slick.Session
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.AnyContent

/**
 * The Class CompanySettingCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 8:00 AM
 *
 */
class CompanySettingCtr(implicit val bindingModule: BindingModule) extends BaseCtr[CompanySetting, CompanySettings] with MainTemplate {
  override implicit val jsonWrite: Writes[CompanySetting] = CompanySettings.companySettingJsonFormat
  override val dao: AbstractQuery[CompanySetting, CompanySettings] = CompanySettings
  override val domainName: String = DomainKey.companySetting
  override def editForm(implicit session: Session): Form[CompanySetting] = CompanySettings.editForm
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = CompanySettings.load(paging)
    Json.toJson(result)
  }
}
