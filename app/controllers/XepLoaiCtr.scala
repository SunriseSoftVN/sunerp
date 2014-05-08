package controllers

import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{QuyenHanhs, XepLoais, XepLoai}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.data.Form
import dtos.PagingDto
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent
import play.api.db.slick.Session

/**
 * The Class XepLoaiCtr.
 *
 * @author Nguyen Duc Dung
 * @since 5/9/14 5:32 AM
 *
 */
class XepLoaiCtr(implicit val bindingModule: BindingModule) extends BaseCtr[XepLoai, XepLoais] with MainTemplate {
  override def editForm(implicit session: _root_.play.api.db.slick.Session): Form[XepLoai] = XepLoais.editForm
  override implicit val jsonWrite: Writes[XepLoai] = XepLoais.xepLoaiJsonFormat
  override val dao: AbstractQuery[XepLoai, XepLoais] = XepLoais
  override val domainName: String = DomainKey.xepLoai
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = XepLoais.load(paging)
    Json.toJson(result)
  }
}
