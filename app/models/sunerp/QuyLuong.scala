package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._

/**
 * The Class QuyLuong.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:30 AM
 *
 */
case class QuyLuong(
                     id: Option[Long] = None,
                     soTien: Long
                     ) extends WithId[Long]

class QuyLuongs(tag: Tag) extends AbstractTable[QuyLuong](tag, "quyluong") {

  def soTien = column[Long]("soTien", O.NotNull)

  def * = (id.?, soTien) <>(QuyLuong.tupled, QuyLuong.unapply)
}

object QuyLuongs extends AbstractQuery[QuyLuong, QuyLuongs](new QuyLuongs(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "soTien" -> longNumber
    )(QuyLuong.apply)(QuyLuong.unapply)
  )

}