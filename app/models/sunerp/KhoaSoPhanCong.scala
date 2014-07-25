package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json

/**
 * The Class KhoaSoPhanCong.
 *
 * @author Nguyen Duc Dung
 * @since 7/24/2014 3:07 PM
 *
 */
case class KhoaSoPhanCong(
                           id: Option[Long] = None,
                           month: Int,
                           year: Int
                           ) extends WithId[Long]

class KhoaSoPhanCongs(tag: Tag) extends AbstractTable[KhoaSoPhanCong](tag, "khoaSoPhanCong") {
  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  override def * = (id.?, month, year) <>(KhoaSoPhanCong.tupled, KhoaSoPhanCong.unapply)
}

object KhoaSoPhanCongs extends AbstractQuery[KhoaSoPhanCong, KhoaSoPhanCongs](new KhoaSoPhanCongs(_)) {
  implicit val jsonFormat = Json.format[KhoaSoPhanCong]

  def isLock(month: Int, year: Int)(implicit session: Session) = where(k => k.month === month && k.year === year).firstOption.isDefined

  def lock(month: Int, year: Int)(implicit session: Session) {
    if (!isLock(month, year)) {
      save(
        KhoaSoPhanCong(
          month = month,
          year = year
        )
      )
    }
  }

  def unlock(month: Int, year: Int)(implicit session: Session) {
    where(k => k.month === month && k.year === year).delete
  }
}