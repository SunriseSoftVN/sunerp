package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{KhoiDonVis, PhongBangs, PhongBang}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.PagingDto
import play.api.db.slick.Session

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
  override protected def doIndex(paging: PagingDto)(implicit session: Session): JsValue = {
    val result = PhongBangs.load(paging)
    Json.toJson(result)
  }
}
