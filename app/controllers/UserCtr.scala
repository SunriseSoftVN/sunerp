package controllers

import controllers.element.{BaseCtr, MainTemplate}
import play.api.libs.json.{Writes, Json}
import dtos.PagingDto
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.db.slick.Config.driver.simple._
import models.sunerp.{Users, User}

/**
 * The Class UserCtr.
 *
 * @author Nguyen Duc Dung
 * @since 2/11/14 9:07 PM
 *
 */
object UserCtr extends BaseCtr[User, Users] with MainTemplate {

  override val domainName = "user"
  override val dao = Users
  override implicit val jsonWrite: Writes[User] = Users.userJsonFomart
  override val editForm = Form(
    mapping(
      "id" -> optional(of[Long]),
      "username" -> text(minLength = 4),
      "fullname" -> text(minLength = 4),
      "password" -> text(minLength = 4),
      "roleId" -> longNumber,
      "userDataId" -> optional(longNumber)
    )(User.apply)(User.unapply)
  )

  override protected def doIndex(paging: PagingDto)(implicit session: Session) = {
    val users = Users.loadWithRole(paging)
    Json.toJson(users)
  }
}
