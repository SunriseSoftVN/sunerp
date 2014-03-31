package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{QuyenHanh, QuyenHanhs}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.data.Form
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent

/**
 * The Class QuyenHanhCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/22/14 7:24 AM
 *
 */
object QuyenHanhCtr extends BaseCtr[QuyenHanh, QuyenHanhs] with MainTemplate {
  override val domainName: String = "quyenHanh"
  override def editForm: Form[QuyenHanh] = QuyenHanhs.editForm
  override implicit val jsonWrite: Writes[QuyenHanh] = QuyenHanhs.jsonFormat
  override val dao: AbstractQuery[QuyenHanh, QuyenHanhs] = QuyenHanhs
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = QuyenHanhs.load(paging)
    Json.toJson(result)
  }
}
