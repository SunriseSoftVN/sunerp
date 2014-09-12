package models.sunerp

import com.github.nscala_time.time.StaticForwarderImports._
import dtos.{ExtGirdDto, PagingDto}
import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.data.format.Formats._

/**
 * The Class CompanySetting.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 10:01 AM
 *
 */
case class CongThucLuong(
                          id: Option[Long] = None,
                          key: String,
                          value: Double,
                          name: String,
                          month: Int,
                          year: Int
                          ) extends WithId[Long]

class CongThucLuongs(tag: Tag) extends AbstractTable[CongThucLuong](tag, "congthucluong") {

  def key = column[String]("key", O.NotNull)

  def value = column[Double]("value", O.NotNull)

  def name = column[String]("name", O.NotNull)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def * = (id.?, key, value, name, month, year) <>(CongThucLuong.tupled, CongThucLuong.unapply)
}

object CongThucLuongs extends AbstractQuery[CongThucLuong, CongThucLuongs](new CongThucLuongs(_)) {
  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "key" -> nonEmptyText,
      "value" -> of[Double],
      "name" -> nonEmptyText,
      "month" -> number,
      "year" -> number
    )(CongThucLuong.apply)(CongThucLuong.unapply)
  )

  def findByMonth(month: Int)(implicit session: Session) = where(r => r.month === month && r.year === LocalDate.now.getYear).list()

  def findByKey(key: String)(implicit session: Session) = where(_.key === key).firstOption

  def copyDataFromLastMonth(month: Int)(implicit session: Session) {
    val last = LocalDate.now.withMonthOfYear(month).minusMonths(1)
    val lastMonth = last.getMonthOfYear
    val year = last.getYear

    val lastMonthQuery = for {
      row <- this
      if row.month === lastMonth && row.year === year
    } yield row

    val query = for {
      row <- this
      if row.month === month && row.year === LocalDate.now.getYear
    } yield row

    val data = lastMonthQuery.list()

    if (data.length > 0) {
      query.delete
      for (row <- data) {
        save(row.copy(id = None, month = month, year = LocalDate.now.getYear))
      }
    }
  }

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[CongThucLuong] = {

    var query = for (row <- this) yield row

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        filter.property match {
          case "name" => table.name.toLowerCase like filter.asLikeValue
          case "month" =>
            table.month === filter.asInt && table.year === LocalDate.now.getYear
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

    ExtGirdDto[CongThucLuong](
      total = totalRow,
      data = rows
    )
  }

  implicit val congThucLuongJsonFormat = Json.format[CongThucLuong]
}