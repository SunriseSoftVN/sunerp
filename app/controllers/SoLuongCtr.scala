package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{KhoiDonVis, SoLuong, SoLuongs}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.PagingDto
import play.api.db.slick.Session

/**
 * The Class SoLuongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
object SoLuongCtr extends BaseCtr[SoLuong, SoLuongs] with MainTemplate {
  override implicit val jsonWrite: Writes[SoLuong] = SoLuongs.soLuongJsonFormat
  override val dao: AbstractQuery[SoLuong, SoLuongs] = SoLuongs
  override val domainName: String = "soLuong"
  override def editForm = SoLuongs.editForm
  override protected def doIndex(paging: PagingDto)(implicit session: Session): JsValue = {
    val result = SoLuongs.load(paging)
    Json.toJson(result)
  }
}
