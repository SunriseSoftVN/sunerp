package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{PhongBangs, NhanViens, NhanVien}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent

/**
 * The Class NhanVienCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
object NhanVienCtr extends BaseCtr[NhanVien,NhanViens] with MainTemplate {
  override def editForm = NhanViens.editForm
  override implicit val jsonWrite: Writes[NhanVien] = NhanViens.nhanVienJsonFormat
  override val dao: AbstractQuery[NhanVien, NhanViens] = NhanViens
  override val domainName: String = "nhanVien"
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = NhanViens.load(paging, loggedIn(request))
    Json.toJson(result)
  }
}
