package controllers.element

import play.api.mvc._
import models.sunerp.User

/**
 * The Class MainTemplate.
 *
 * @author Nguyen Duc Dung
 * @since 1/7/14 1:06 PM
 *
 */
trait MainTemplate extends Controller {

  def renderOk(implicit user: Option[User]) = Ok(views.html.tml.main())

  def renderBadRequest(implicit user: Option[User]) = BadRequest(views.html.tml.main())

  implicit def currentUser(implicit user: User): Option[User] = Some(user)

  implicit class RichOption[E](op: Option[E]) {
    def mapRender(f: E => SimpleResult) = op.map(f).getOrElse(NotFound)
  }
}
