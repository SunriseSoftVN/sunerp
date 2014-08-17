package controllers

import _root_.utils.DateTimeUtils
import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp._
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.data.Form
import dtos.PagingDto
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent
import play.api.db.slick.Session
import models.sunerp.DiemHeSo

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
    val year = paging.findFilters("year").fold(DateTimeUtils.currentYear)(f => f.asInt)
    val nhanViens = NhanViens.findWhoNotInDiemHeSo(year)
    for (nhanVien <- nhanViens) {
      val diemHeSo = new DiemHeSo(
        nhanVienId = nhanVien.id.get,
        heSo = 0d,
        year = year
      )
      DiemHeSos.save(diemHeSo)
    }
    val result = DiemHeSos.load(paging)
    Json.toJson(result)
  }
}
