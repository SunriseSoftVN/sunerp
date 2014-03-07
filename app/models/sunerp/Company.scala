package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import play.api.libs.json.Json

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

  def name = column[String]("name", O.NotNull)

  def address = column[String]("address", O.NotNull)

  def phone = column[String]("phone", O.NotNull)

  def email = column[String]("email", O.NotNull)

  def mst = column[String]("mst", O.NotNull)

  override def * = (id.?, name, address, phone, email, mst) <>(Company.tupled, Company.unapply)
}

object Companies extends AbstractQuery[Company, Companies](new Companies(_)) {
  implicit val companyJsonFormat = Json.format[Company]
}