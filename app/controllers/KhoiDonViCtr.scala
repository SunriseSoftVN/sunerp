package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{KhoiDonVis, KhoiDonVi}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.{KhoiDonViDto, PagingDto}
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent

/**
 * The Class KhoiDonViCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:19 PM
 *
 */
object KhoiDonViCtr extends BaseCtr[KhoiDonVi, KhoiDonVis] with MainTemplate {
  override def editForm(implicit session: Session) = KhoiDonVis.editForm
  override implicit val jsonWrite: Writes[KhoiDonVi] = KhoiDonVis.khoiDonViJsonFormat
  override val dao: AbstractQuery[KhoiDonVi, KhoiDonVis] = KhoiDonVis
  override val domainName: String = "khoiDonVi"
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    implicit val jsonWrite = KhoiDonViDto.jsonWrite
    val result = KhoiDonVis.load(paging)
    Json.toJson(result)
  }
  def all = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok(Json.toJson(KhoiDonVis.all))
  })
}
