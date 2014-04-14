package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{SoLuong, SoLuongs}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent

/**
 * The Class SoLuongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
object SoLuongCtr extends BaseCtr[SoLuong, SoLuongs] with MainTemplate {
  override implicit val jsonWrite: Writes[SoLuong] = SoLuongs.soLuongJsonFormat
  override val dao: AbstractQuery[SoLuong, SoLuongs] = SoLuongs
  override val domainName: String = DomainKey.soLuong

  override def editForm(implicit session: Session) = SoLuongs.editForm

  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = SoLuongs.load(paging)
    Json.toJson(result)
  }
}
