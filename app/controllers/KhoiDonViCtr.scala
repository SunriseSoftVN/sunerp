package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{KhoiDonVis, KhoiDonVi}
import models.core.AbstractQuery
import play.api.libs.json.Writes

/**
 * The Class KhoiDonViCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:19 PM
 *
 */
object KhoiDonViCtr extends BaseCtr[KhoiDonVi, KhoiDonVis] with MainTemplate {
  override def editForm = KhoiDonVis.editForm
  override implicit val jsonWrite: Writes[KhoiDonVi] = KhoiDonVis.khoiDonViJsonFormat
  override val dao: AbstractQuery[KhoiDonVi, KhoiDonVis] = KhoiDonVis
  override val domainName: String = "khoiDonVi"
}
