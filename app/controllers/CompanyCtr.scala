package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{Companies, Company}
import models.core.AbstractQuery
import play.api.data.Form
import play.api.libs.json.Writes

/**
 * The Class CompanyCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:19 PM
 *
 */
object CompanyCtr extends BaseCtr[Company, Companies] with MainTemplate {
  override val domainName: String = "company"
  override implicit val jsonWrite: Writes[Company] = Companies.companyJsonFormat
  override val dao: AbstractQuery[Company, Companies] = Companies
  override def editForm: Form[Company] = Companies.editFrom
}
