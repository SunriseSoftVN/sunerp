package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{ChucVus, DonVis, DonVi}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.PagingDto
import play.api.db.slick.Session

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
  override protected def doIndex(paging: PagingDto)(implicit session: Session): JsValue = {
    val result = DonVis.load(paging)
    Json.toJson(result)
  }
}
