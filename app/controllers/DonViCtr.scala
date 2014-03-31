package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{DonVis, DonVi}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.{DonViDto, PagingDto}
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent

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
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    implicit val jsonWrite = DonViDto.jsonWrite
    val result = DonVis.load(paging)
    Json.toJson(result)
  }

  def all = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok(Json.toJson(DonVis.all))
  })
}
