package models.sunerp

import dtos.{ExtGirdDto, KhoaSoPhanCongDto, PagingDto}
import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json
import utils.DateTimeUtils

import scala.text

/**
 * The Class KhoaSoPhanCong.
 *
 * @author Nguyen Duc Dung
 * @since 7/24/2014 3:07 PM
 *
 */
case class KhoaSoPhanCong(
                           id: Option[Long] = None,
                           donViId: Long,
                           lock: Boolean,
                           month: Int,
                           year: Int
                           ) extends WithId[Long]

class KhoaSoPhanCongs(tag: Tag) extends AbstractTable[KhoaSoPhanCong](tag, "khoaSoPhanCong") {
  def month = column[Int]("month", O.NotNull)

  def donViId = column[Long]("donViId", O.NotNull)

  def lock = column[Boolean]("lock", O.NotNull)

  def donVi = foreignKey("doi_vi_phong_ban_fk", donViId, DonVis)(_.id)

  def year = column[Int]("year", O.NotNull)

  override def * = (id.?, donViId, lock, month, year) <>(KhoaSoPhanCong.tupled, KhoaSoPhanCong.unapply)
}

object KhoaSoPhanCongs extends AbstractQuery[KhoaSoPhanCong, KhoaSoPhanCongs](new KhoaSoPhanCongs(_)) {
  implicit val jsonFormat = Json.format[KhoaSoPhanCong]

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "donViId" -> longNumber,
      "lock" -> boolean,
      "month" -> number,
      "year" -> number
    )(KhoaSoPhanCong.apply)(KhoaSoPhanCong.unapply)
  )

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[KhoaSoPhanCongDto] = {
    var query = for {
      khoaSoPhanCong <- this
      donVi <- khoaSoPhanCong.donVi
      if khoaSoPhanCong.year === DateTimeUtils.currentYear
    } yield (khoaSoPhanCong, donVi)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(table => {
        val (khoaSoPhanCong, donVi) = table
        filter.property match {
          case "donVi.name" => donVi.name.toLowerCase like filter.asLikeValue
          case "month" => khoaSoPhanCong.month === filter.asInt
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (khoaSoPhanCong, donVi) = table
        sort.property match {
          case "donVi.name" => orderColumn(sort.direction, donVi.name)
          case "lock" => orderColumn(sort.direction, khoaSoPhanCong.lock)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(KhoaSoPhanCongDto.apply)

    ExtGirdDto[KhoaSoPhanCongDto](
      total = totalRow,
      data = rows
    )
  }

  def isLock(donViId: Long, month: Int, year: Int)(implicit session: Session) = {
    val khoaSo = where {
      _khoaSo => _khoaSo.donViId === donViId && _khoaSo.month === month && _khoaSo.year === year
    }.firstOption
    khoaSo.isDefined && khoaSo.get.lock
  }
}