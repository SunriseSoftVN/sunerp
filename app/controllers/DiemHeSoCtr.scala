package controllers

import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{DiemHeSos, DiemHeSo, XepLoais, XepLoai}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.data.Form
import dtos.PagingDto
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent
import play.api.db.slick.Session

/**
 * The Class DiemHeSoCtr.
 *
 * @author Nguyen Duc Dung
 * @since 5/9/14 5:32 AM
 *
 */
class DiemHeSoCtr(implicit val bindingModule: BindingModule) extends BaseCtr[DiemHeSo, DiemHeSos] with MainTemplate {
  override def editForm(implicit session: Session): Form[DiemHeSo] = DiemHeSos.editForm
  override implicit val jsonWrite: Writes[DiemHeSo] = DiemHeSos.diemHeSoJsonFormat
  override val dao: AbstractQuery[DiemHeSo, DiemHeSos] = DiemHeSos
  override val domainName: String = DomainKey.diemHeSo
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = DiemHeSos.load(paging)
    Json.toJson(result)
  }
}
