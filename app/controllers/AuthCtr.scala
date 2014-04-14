package controllers

import play.api.mvc._
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global
import jp.t2v.lab.play2.stackc.{StackableController, RequestWithAttributes}
import controllers.element.{AuthConfigImpl, MainTemplate, TransactionElement}
import models.sunerp.NhanViens
import com.escalatesoft.subcut.inject.BindingModule

/**
 * The Class AuthCtr.
 *
 * @author Nguyen Duc Dung
 * @since 1/7/14 2:39 PM
 *
 */
class AuthCtr(implicit val bindingModule: BindingModule) extends Controller with StackableController with AuthConfigImpl with LoginLogout with TransactionElement with MainTemplate {

  private def loginForm(implicit request: RequestWithAttributes[_]) = Form {
    tuple(
      "maNv" -> nonEmptyText,
      "password" -> nonEmptyText
    ) verifying("login.failed", fields => fields match {
      case (maNv, password) => NhanViens.login(maNv, password)
    })
  }

  def login = StackAction(implicit request => renderOk(None))

  def auth = AsyncStack(implicit request => {
    loginForm.bindFromRequest.fold(
      error => Future.successful(InternalServerError),
      tuple => gotoLoginSucceeded(tuple._1)
    )
  })

  def logout = AsyncStack(implicit request => {
    gotoLogoutSucceeded
  })
}
