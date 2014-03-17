package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

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

class ChucVus(tag: Tag) extends AbstractTable[ChucVu](tag, "chucVu") {
  def name = defColumn[String]("name", O.NotNull)

  def phuCapTrachNhiem = defColumn[Long]("phuCapTrachNhiem", O.NotNull)

  def * = (id.?, name, phuCapTrachNhiem) <>(ChucVu.tupled, ChucVu.unapply)
}

object ChucVus extends AbstractQuery[ChucVu, ChucVus](new ChucVus(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(minLength = 4),
      "phuCapTrachNhiem" -> longNumber
    )(ChucVu.apply)(ChucVu.unapply)
  )

  implicit val chucVuJsonFormat = Json.format[ChucVu]

}
