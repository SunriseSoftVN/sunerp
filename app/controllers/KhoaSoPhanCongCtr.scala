package controllers

import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{BaseCtr, MainTemplate}
import dtos.{KhoaSoPhanCongDto, PagingDto}
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import models.core.AbstractQuery
import models.sunerp.{KhoaSoPhanCong, KhoaSoPhanCongs}
import play.api.data.Form
import play.api.db.slick.Session
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.AnyContent

/**
 * The Class KhoaSoPhanCongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 8/9/2014 9:04 PM
 *
 */
class KhoaSoPhanCongCtr(implicit val bindingModule: BindingModule) extends BaseCtr[KhoaSoPhanCong, KhoaSoPhanCongs] with MainTemplate {
  override val domainName: String = DomainKey.khoaSoPhanCong
  override def editForm(implicit session: Session): Form[KhoaSoPhanCong] = ???
  override val dao: AbstractQuery[KhoaSoPhanCong, KhoaSoPhanCongs] = KhoaSoPhanCongs
  override implicit val jsonWrite: Writes[KhoaSoPhanCong] = KhoaSoPhanCongs.jsonFormat
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    implicit val jsonWrite = KhoaSoPhanCongDto.jsonWrite
    val result = KhoaSoPhanCongs.load(paging)
    Json.toJson(result)
  }
}
