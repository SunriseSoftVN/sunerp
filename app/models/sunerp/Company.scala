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


  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[Company] = {
    var query = for (row <- this) yield row

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        filter.property match {
          case "name" => table.name.toLowerCase like filter.valueForLike
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        sort.property match {
          case "name" => orderColumn(sort.direction, table.name)
          case "address" => orderColumn(sort.direction, table.address)
          case "phone" => orderColumn(sort.direction, table.phone)
          case "email" => orderColumn(sort.direction, table.email)
          case "mst" => orderColumn(sort.direction, table.mst)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list

    ExtGirdDto[Company](
      total = totalRow,
      data = rows
    )
  }
}