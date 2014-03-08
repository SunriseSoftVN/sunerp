package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{DonVis, DonVi}
import models.core.AbstractQuery
import play.api.libs.json.Writes

/**
 * The Class DonViCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:19 PM
 *
 */
object DonViCtr extends BaseCtr[DonVi, DonVis] with MainTemplate {
  override def editForm = DonVis.editForm
  override implicit val jsonWrite: Writes[DonVi] = DonVis.donViJsonFormat
  override val dao: AbstractQuery[DonVi, DonVis] = DonVis
  override val domainName: String = "donVi"
}
