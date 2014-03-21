package controllers.element

import play.api.mvc._
import models.sunerp.NhanVien

/**
 * The Class MainTemplate.
 *
 * @author Nguyen Duc Dung
 * @since 1/7/14 1:06 PM
 *
 */
trait MainTemplate extends Controller {

  def renderOk(implicit user: Option[NhanVien]) = Ok(views.html.tml.main())

  def renderBadRequest(implicit user: Option[NhanVien]) = BadRequest(views.html.tml.main())

  implicit def currentUser(implicit nhanVien: NhanVien): Option[NhanVien] = Some(nhanVien)

  implicit class RichOption[E](op: Option[E]) {
    def mapRender(f: E => SimpleResult) = op.map(f).getOrElse(NotFound)
  }
}
