package controllers


import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{HeSoLuongs, HeSoLuong, DonVis, DonVi}
import models.core.AbstractQuery
import play.api.data.Form
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.{DonViDto, PagingDto}
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent
import com.escalatesoft.subcut.inject.BindingModule

/**
 * Created by dungvn3000 on 07/11/2014.
 */
class HeSoLuongCtr(implicit val bindingModule: BindingModule) extends BaseCtr[HeSoLuong, HeSoLuongs] with MainTemplate {
  override val domainName: String = DomainKey.hesoluong
  override def editForm(implicit session: Session): Form[HeSoLuong] = HeSoLuongs.editForm
  override val dao: AbstractQuery[HeSoLuong, HeSoLuongs] = HeSoLuongs
  override implicit val jsonWrite: Writes[HeSoLuong] = HeSoLuongs.heSoLuongJsonFormat
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    ???
  }
}