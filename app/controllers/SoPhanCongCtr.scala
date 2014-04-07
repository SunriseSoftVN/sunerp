package controllers

import controllers.element.{TransactionElement, AuthConfigImpl, MainTemplate, BaseCtr}
import models.sunerp.{SoPhanCong, SoPhanCongs}
import models.core.AbstractQuery
import play.api.libs.json.{Json, Writes}
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.{Controller, AnyContent}
import jp.t2v.lab.play2.auth.AuthElement

/**
 * The Class SoPhanCongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
object SoPhanCongCtr extends Controller with AuthElement with AuthConfigImpl with TransactionElement with MainTemplate {

  val domainName = "soPhanCong"

  def index = StackAction(AuthorityKey -> domainName)(implicit request => {
    val paging = PagingDto(request)
    val result = SoPhanCongs.load(paging)
    Ok(Json.toJson(result))
  })

  def delete(id: Long) = StackAction(AuthorityKey -> domainName)(implicit request => {
    SoPhanCongs.deleteById(id)
    Ok
  })

  def save = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok
  })

  def update(id: Long) = StackAction(AuthorityKey -> domainName)(implicit request => {
    Ok
  })

}
