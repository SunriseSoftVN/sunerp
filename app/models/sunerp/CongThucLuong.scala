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
                          year: Int,
                          phongBangId: Long
                          ) extends WithId[Long]

class CongThucLuongs(tag: Tag) extends AbstractTable[CongThucLuong](tag, "congthucluong") {

  def key = column[String]("key", O.NotNull)

  def value = column[Double]("value", O.NotNull)

  def name = column[String]("name", O.NotNull)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def phongBanId = column[Long]("phongBangId", O.NotNull)

  def phongBan = foreignKey("fk_congthucluong_phongban", phongBanId, PhongBans)(_.id)

  def * = (id.?, key, value, name, month, year, phongBanId) <>(CongThucLuong.tupled, CongThucLuong.unapply)
}

object CongThucLuongs extends AbstractQuery[CongThucLuong, CongThucLuongs](new CongThucLuongs(_)) {
  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "key" -> nonEmptyText,
      "value" -> of[Double],
      "name" -> nonEmptyText,
      "month" -> number,
      "year" -> number,
      "phongBangId" -> longNumber
    )(CongThucLuong.apply)(CongThucLuong.unapply)
  )

  def findByMonth(month: Int, year: Int, phongBangId: Long)(implicit session: Session) = {

    def load = where {
      r => r.month === month && r.year === year && r.phongBanId === phongBangId
    }.list()

    val congThucLuongs = load

    var changed = false

    if (!congThucLuongs.exists(_.key == "phucapkhac.kl")) {
      save(new CongThucLuong(
        key = "phucapkhac.kl",
        value = 0d,
        name = "Phụ cấp khác khối lượng",
        month = month,
        year = year,
        phongBangId = phongBangId
      ))
      changed = true
    }

    if (!congThucLuongs.exists(_.key == "phucapkhac.kl")) {
      save(new CongThucLuong(
        key = "phucapkhac.dongia",
        value = 0d,
        name = "Phụ cấp khác đơn giá",
        month = month,
        year = year,
        phongBangId = phongBangId
      ))
      changed = true
    }

    if (!congThucLuongs.exists(_.key == "boiduongdochai.dongia")) {
      save(new CongThucLuong(
        key = "boiduongdochai.dongia",
        value = 0d,
        name = "Bồi dưỡng độc hại đơn giá",
        month = month,
        year = year,
        phongBangId = phongBangId
      ))
      changed = true
    }

    if(changed) load else congThucLuongs
  }

  def findByKey(key: String)(implicit session: Session) = where(_.key === key).firstOption

  def copyDataFromLastMonth(month: Int, phongBangId: Long)(implicit session: Session) {
    val last = LocalDate.now.withMonthOfYear(month).minusMonths(1)
    val lastMonth = last.getMonthOfYear
    val year = last.getYear

    val lastMonthQuery = for {
      row <- this
      if row.month === lastMonth && row.year === year && row.phongBanId === phongBangId
    } yield row

    val query = for {
      row <- this
      if row.month === month && row.year === LocalDate.now.getYear && row.phongBanId === phongBangId
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
          case "phongBanId" => table.phongBanId === filter.asLong
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