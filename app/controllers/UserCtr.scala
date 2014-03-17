package controllers

import controllers.element.{BaseCtr, MainTemplate}
import play.api.libs.json.{Writes, Json}
import dtos.PagingDto
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
  override def editForm = Users.editForm

  override protected def doIndex(paging: PagingDto)(implicit session: Session) = {
    val users = Users.load(paging)
    Json.toJson(users)
  }
}
