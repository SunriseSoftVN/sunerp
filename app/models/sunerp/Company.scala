package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import dtos.{ExtGirdDto, PagingDto}

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
                    mst: String,
                    companySettingId: Long
                    ) extends WithId[Long]

class Companies(tag: Tag) extends AbstractTable[Company](tag, "company") {

  def name = column[String]("name", O.NotNull)

  def address = column[String]("address", O.NotNull)

  def phone = column[String]("phone", O.NotNull)

  def email = column[String]("email", O.NotNull)

  def mst = column[String]("mst", O.NotNull)

  def companySettingId = column[Long]("companySettingId", O.NotNull)

  def companySetting = foreignKey("company_company_setting_fk", companySettingId, CompanySettings)(_.id)

  override def * = (id.?, name, address, phone, email, mst, companySettingId) <>(Company.tupled, Company.unapply)

  override def columnByName = {
    // could be implemented in AbstractQuery using reflection
    Map[String,Column[_]](
      "name"    -> name,
      "address" -> address,
      "phone"   -> phone,
      "email"   -> email,
      "mst"     -> mst
    )
  }
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
      "mst" -> text(minLength = 4),
      "companySettingId" -> longNumber
    )(Company.apply)(Company.unapply)
  )

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[Company] = abstractLoad(pagingDto)
}