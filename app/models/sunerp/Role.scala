package models.sunerp

import play.api.db.slick.Config.driver.simple._
import models.core.{WithId, AbstractQuery, AbstractTable}
import play.api.libs.json.Json
import play.api.data.format.Formats._
import play.api.data.Form
import play.api.data.Forms._

/**
 * The Class Role.
 *
 * @author Nguyen Duc Dung
 * @since 2/12/14 12:56 AM
 *
 */
case class Role(
                 id: Option[Long] = None,
                 name: String
                 ) extends WithId[Long]

class Roles(tag: Tag) extends AbstractTable[Role](tag, "role") {
  def name = column[String]("name")

  def * = (id.?, name) <>(Role.tupled, Role.unapply)
}

object Roles extends AbstractQuery[Role, Roles](new Roles(_)) {

  def findByName(name: String)(implicit session: Session) = where(_.name === name).firstOption

  def editForm = Form(
    mapping(
      "id" -> optional(of[Long]),
      "name" -> text(minLength = 4)
    )(Role.apply)(Role.unapply)
  )

  implicit val roleJsonFormat = Json.format[Role]
}

