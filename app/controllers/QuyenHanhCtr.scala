package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{GioiHan, QuyenHanh, QuyenHanhs}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.data.Form
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent
import com.escalatesoft.subcut.inject.BindingModule

/**
 * The Class QuyenHanhCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/22/14 7:24 AM
 *
 */
class QuyenHanhCtr(implicit val bindingModule: BindingModule) extends BaseCtr[QuyenHanh, QuyenHanhs] with MainTemplate {
  override val domainName: String = DomainKey.quyenHanh

  override def editForm(implicit session: Session): Form[QuyenHanh] = QuyenHanhs.editForm

  override implicit val jsonWrite: Writes[QuyenHanh] = QuyenHanhs.jsonFormat
  override val dao: AbstractQuery[QuyenHanh, QuyenHanhs] = QuyenHanhs

  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = QuyenHanhs.load(paging)
    Json.toJson(result)
  }

  def getGioiHans = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok(GioiHan.asComboxDataSource)
  })
}
