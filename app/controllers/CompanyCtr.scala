package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{Companies, Company}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.data.Form
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent
import com.escalatesoft.subcut.inject.BindingModule

/**
 * The Class CompanyCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 8:00 AM
 *
 */
class CompanyCtr(implicit val bindingModule: BindingModule) extends BaseCtr[Company, Companies] with MainTemplate {
  override implicit val jsonWrite: Writes[Company] = Companies.companyJsonFormat
  override val dao: AbstractQuery[Company, Companies] = Companies
  override val domainName: String = DomainKey.company
  override def editForm(implicit session: Session): Form[Company] = Companies.editFrom
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = Companies.load(paging)
    Json.toJson(result)
  }
}
