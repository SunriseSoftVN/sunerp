package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
 * The Class Company.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:09 AM
 *
 */
case class Company(
                    id: Option[Long] = None,
                    name: String,
                    address: String,
                    phone: String,
                    email: String,
                    mst: String
                    ) extends WithId[Long]

class Companies(tag: Tag) extends AbstractTable[Company](tag, "company") {

  def name = defColumn[String]("name", O.NotNull)

  def address = defColumn[String]("address", O.NotNull)

  def phone = defColumn[String]("phone", O.NotNull)

  def email = defColumn[String]("email", O.NotNull)

  def mst = defColumn[String]("mst", O.NotNull)

  override def * = (id.?, name, address, phone, email, mst) <>(Company.tupled, Company.unapply)
}

object Companies extends AbstractQuery[Company, Companies](new Companies(_)) {
  implicit val companyJsonFormat = Json.format[Company]

  def editFrom = Form(
    mapping(
      "id" -> optional(of[Long]),
      "name" -> text(minLength = 4),
      "address" -> text(minLength = 4),
      "phone" -> text(minLength = 4),
      "email" -> email,
      "mst" -> text(minLength = 4)
    )(Company.apply)(Company.unapply)
  )
}