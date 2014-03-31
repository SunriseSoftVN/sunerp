package controllers.element

import play.api.mvc._
import play.api.mvc.Results._
import scala.reflect._
import jp.t2v.lab.play2.auth.{CacheIdContainer, CookieIdContainer, IdContainer, AuthConfig}
import play.api.mvc.AcceptExtractors
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play.current
import play.api.db.slick._
import models.sunerp
import models.sunerp.{NhanViens, QuyenHanh}
import play.api.libs.json.Json
import org.apache.commons.digester.SimpleRegexMatcher
import org.apache.commons.lang3.StringUtils
import play.api.Play

/**
 * The Class AuthConfigImpl.
 *
 * @author Nguyen Duc Dung
 * @since 11/6/12 10:10 AM
 *
 */
trait AuthConfigImpl extends AuthConfig with Rendering with AcceptExtractors {

  type Id = String

  type User = sunerp.NhanVien

  type Authority = String

  def sessionTimeoutInSeconds = 60 * 60 * 24 //1 day

  def resolveUser(username: Id)(implicit context: ExecutionContext): Future[Option[User]] = Future {
    DB.withSession(implicit session => {
      NhanViens.findByMaNv(username)
    })
  }

  implicit val idTag: ClassTag[Id] = classTag[Id]

  def loginSucceeded(request: RequestHeader)(implicit context: ExecutionContext) = Future.successful(Ok(Json.obj("success" -> true)))

  def logoutSucceeded(request: RequestHeader)(implicit context: ExecutionContext) = Future.successful(Redirect("/user/login"))

  def authenticationFailed(request: RequestHeader)(implicit context: ExecutionContext) = Future {
    render {
      case Accepts.Json() => Forbidden("Bạn không đủ quyền để truy cập vào nội dung này")
      case _ => Redirect("/user/login")
    }(request)
  }

  def authorizationFailed(request: RequestHeader)(implicit context: ExecutionContext) = Future {
    Forbidden("Bạn không đủ quyền để truy cập vào nội dung này")
  }

  def authorize(user: User, authority: Authority)(implicit context: ExecutionContext) = Future.successful {
    //do check on db
    DB.withSession(implicit session => {
      user.checkAuth(authority)
    })
  }

  override lazy val idContainer: IdContainer[Id] = if (Play.isDev) {
    new CookieIdContainer[Id]
  } else {
    new CacheIdContainer[Id]
  }
}