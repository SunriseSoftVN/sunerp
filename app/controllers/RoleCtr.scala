package controllers

import controllers.element.{BaseCtr, MainTemplate}
import play.api.libs.json.{JsValue, Writes, Json}
import models.core.AbstractQuery
import play.api.data.Form
import models.sunerp.{KhoiDonVis, Roles, Role}
import dtos.PagingDto
import play.api.db.slick.Session

/**
 * The Class RoleCtr.
 *
 * @author Nguyen Duc Dung
 * @since 2/17/14 5:39 PM
 *
 */
object RoleCtr extends BaseCtr[Role, Roles] with MainTemplate {
  override val domainName = "role"
  override implicit val jsonWrite: Writes[Role] = Roles.roleJsonFormat
  override val dao: AbstractQuery[Role, Roles] = Roles
  override def editForm: Form[Role] = Roles.editForm
  def all = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok(Json.toJson(Roles.all))
  })
  override protected def doIndex(paging: PagingDto)(implicit session: Session): JsValue = {
    val result = Roles.load(paging)
    Json.toJson(result)
  }
}
