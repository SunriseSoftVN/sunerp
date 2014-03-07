package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._

/**
 * The Class ChucVu.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 10:09 AM
 *
 */
case class ChucVu(
                   id: Option[Long] = None,
                   name: String,
                   phuCapTrachNhiem: Long
                   ) extends WithId[Long]

class ChucVus(tag: Tag) extends AbstractTable[ChucVu](tag, "chuc_vu") {
  def name = column[String]("name", O.NotNull)

  def phuCapTrachNhiem = column[Long]("phuCapTrachNhiem", O.NotNull)

  def * = (id.?, name, phuCapTrachNhiem) <>(ChucVu.tupled, ChucVu.unapply)
}

object ChucVus extends AbstractQuery[ChucVu, ChucVus](new ChucVus(_)) {

}
