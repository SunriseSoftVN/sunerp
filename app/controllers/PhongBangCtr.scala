package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{PhongBangs, PhongBang}
import models.core.AbstractQuery
import play.api.libs.json.Writes

/**
 * The Class PhongBangCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:19 PM
 *
 */
object PhongBangCtr extends BaseCtr[PhongBang, PhongBangs] with MainTemplate {
  override def editForm = PhongBangs.editForm
  override implicit val jsonWrite: Writes[PhongBang] = PhongBangs.phongBangJsonFormat
  override val dao: AbstractQuery[PhongBang, PhongBangs] = PhongBangs
  override val domainName: String = "phongBang"
}
