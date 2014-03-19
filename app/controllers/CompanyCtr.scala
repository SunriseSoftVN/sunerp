package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{Companies, Company}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.data.Form
import dtos.PagingDto
import play.api.db.slick.Session

/**
 * The Class CompanyCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 8:00 AM
 *
 */
object CompanyCtr extends BaseCtr[Company, Companies] with MainTemplate {
  override implicit val jsonWrite: Writes[Company] = Companies.companyJsonFormat
  override val dao: AbstractQuery[Company, Companies] = Companies
  override val domainName: String = "company"
  override def editForm: Form[Company] = Companies.editFrom
  override protected def doIndex(paging: PagingDto)(implicit session: Session): JsValue = {
    val result = Companies.load(paging)
    Json.toJson(result)
  }
}
