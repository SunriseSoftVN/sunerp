package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{PhongBangs, PhongBang}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.{PhongBangDto, PagingDto}
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent

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

  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    implicit val jsonWrite = PhongBangDto.jsonWrite
    val result = PhongBangs.load(paging)
    Json.toJson(result)
  }

  def all = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok(Json.toJson(PhongBangs.all))
  })
}
