package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.format.Formats._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * The Class CacKhoangTru.
 *
 * @author Nguyen Duc Dung
 * @since 3/5/14 11:37 AM
 *
 */
case class CacKhoangTru(
                         id: Option[Long] = None,
                         doanPhi: Long,
                         ungKy1: Long,
                         bhyt: Long,
                         bhxh: Long,
                         thuNo: Long
                         ) extends WithId[Long]

class CacKhoangTrus(tag: Tag) extends AbstractTable[CacKhoangTru](tag, "cacKhoangTru") {

  def doanPhi = column[Long]("doanPhi", O.NotNull)

  def ungKy1 = column[Long]("ungKy1", O.NotNull)

  def bhyt = column[Long]("bhyt", O.NotNull)

  def bhxh = column[Long]("bhxh", O.NotNull)

  def thuNo = column[Long]("thuNo", O.NotNull)

  def * = (id.?, doanPhi, ungKy1, bhyt, bhxh, thuNo) <>(CacKhoangTru.tupled, CacKhoangTru.unapply)
}

object CacKhoangTrus extends AbstractQuery[CacKhoangTru, CacKhoangTrus](new CacKhoangTrus(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(of[Long]),
      "doanPhi" -> longNumber,
      "ungKy1" -> longNumber,
      "bhyt" -> longNumber,
      "bhxh" -> longNumber,
      "thuNo" -> longNumber
    )(CacKhoangTru.apply)(CacKhoangTru.unapply)
  )

  implicit val cacKhoanTruJsonFormat = Json.format[CacKhoangTru]

}