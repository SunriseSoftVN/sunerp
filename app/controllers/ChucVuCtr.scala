package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{ChucVus, ChucVu}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent

/**
 * The Class ChucVuCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
object ChucVuCtr extends BaseCtr[ChucVu, ChucVus] with MainTemplate {
  override val domainName: String = "chucVu"
  override implicit val jsonWrite: Writes[ChucVu] = ChucVus.chucVuJsonFormat
  override val dao: AbstractQuery[ChucVu, ChucVus] = ChucVus
  override def editForm = ChucVus.editForm
  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = ChucVus.load(paging)
    Json.toJson(result)
  }
  def all = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok(Json.toJson(ChucVus.all))
  })
}
