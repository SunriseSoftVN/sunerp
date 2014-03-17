package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.core.AbstractQuery
import play.api.data.Form
import play.api.libs.json.{Json, Writes}
import dtos.PagingDto
import models.sunerp
import models.sunerp.{Authorities, Authority}
import play.api.db.slick.Session

/**
 * The Class AuthorityCtr.
 *
 * @author Nguyen Duc Dung
 * @since 2/20/14 9:58 AM
 *
 */
object AuthorityCtr extends BaseCtr[Authority, Authorities] with MainTemplate {
  override val domainName: String = "authority"
  override implicit val jsonWrite: Writes[sunerp.Authority] = Authorities.jsonFormat
  override val dao: AbstractQuery[sunerp.Authority, Authorities] = Authorities
  override val editForm: Form[sunerp.Authority] = Authorities.editForm
  override protected def doIndex(paging: PagingDto)(implicit session: Session) = {
    val auths = Authorities.loadWithRole(paging)
    Json.toJson(auths)
  }
}
