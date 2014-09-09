package models.sunerp

import dtos.{ExtGirdDto, PagingDto}
import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * The Class CompanySetting.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 10:01 AM
 *
 */
case class CompanySetting(
                           id: Option[Long] = None,
                           key: String,
                           value: String,
                           name: String
                           ) extends WithId[Long]

class CompanySettings(tag: Tag) extends AbstractTable[CompanySetting](tag, "companySetting") {

  def key = column[String]("key", O.NotNull)

  def value = column[String]("value", O.NotNull)

  def name = column[String]("name", O.NotNull)

  def * = (id.?, key, value, name) <>(CompanySetting.tupled, CompanySetting.unapply)
}

object CompanySettings extends AbstractQuery[CompanySetting, CompanySettings](new CompanySettings(_)) {
  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "key" -> nonEmptyText,
      "value" -> nonEmptyText,
      "name" -> nonEmptyText
    )(CompanySetting.apply)(CompanySetting.unapply)
  )

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[CompanySetting] = {

    var query = for (row <- this) yield row

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        filter.property match {
          case "name" => table.name.toLowerCase like filter.asLikeValue
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        sort.property match {
          case "name" => orderColumn(sort.direction, table.name)
          case "value" => orderColumn(sort.direction, table.value)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list

    ExtGirdDto[CompanySetting](
      total = totalRow,
      data = rows
    )
  }

  implicit val companySettingJsonFormat = Json.format[CompanySetting]
}