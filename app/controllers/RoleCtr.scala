package controllers

import controllers.element.{BaseCtr, MainTemplate}
import play.api.libs.json.{Writes, Json}
import models.core.AbstractQuery
import play.api.data.Form
import models.sunerp.{Roles, Role}

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

}
