package controllers

import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{BaseCtr, MainTemplate}
import dtos.PagingDto
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import models.core.AbstractQuery
import models.sunerp._
import play.api.data.Form
import play.api.db.slick.Session
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.AnyContent

/**
 * Created by dungvn3000 on 07/11/2014.
 */
class TrangThaiNhanVienCtr(implicit val bindingModule: BindingModule) extends BaseCtr[TrangThaiNhanVien, TrangThaiNhanViens] with MainTemplate {
  override val domainName: String = DomainKey.trangthainhanvien
  override def editForm(implicit session: Session): Form[TrangThaiNhanVien] = TrangThaiNhanViens.editForm
  override val dao: AbstractQuery[TrangThaiNhanVien, TrangThaiNhanViens] = TrangThaiNhanViens
  override implicit val jsonWrite: Writes[TrangThaiNhanVien] = TrangThaiNhanViens.trangThaiJsonFormat
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = TrangThaiNhanViens.load(paging)
    Json.toJson(result)
  }
}