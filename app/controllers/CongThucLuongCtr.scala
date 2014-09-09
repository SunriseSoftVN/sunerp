package controllers

import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{BaseCtr, MainTemplate}
import dtos.PagingDto
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import models.core.AbstractQuery
import models.sunerp.{CongThucLuongs, CongThucLuong, Companies, Company}
import play.api.data.Form
import play.api.db.slick.Session
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.AnyContent

/**
 * The Class CongThucLuongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 8:00 AM
 *
 */
class CongThucLuongCtr(implicit val bindingModule: BindingModule) extends BaseCtr[CongThucLuong, CongThucLuongs] with MainTemplate {
  override implicit val jsonWrite: Writes[CongThucLuong] = CongThucLuongs.congThucLuongJsonFormat
  override val dao: AbstractQuery[CongThucLuong, CongThucLuongs] = CongThucLuongs
  override val domainName: String = DomainKey.congThucLuong
  override def editForm(implicit session: Session): Form[CongThucLuong] = CongThucLuongs.editForm
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = CongThucLuongs.load(paging)
    Json.toJson(result)
  }
}
