package models.sunerp

import models.core.{AbstractQuery, AbstractTable}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * The Class XepLoai.
 *
 * @author Nguyen Duc Dung
 * @since 5/9/14 5:07 AM
 *
 */
case class XepLoai(
                    id: Option[Long] = None,
                    nhanVienId: Long,
                    month: Int,
                    year: Int,
                    xepLoai: String
                    )

object XepLoaiType {
  val A = "A"
  val B = "B"
  val C = "C"
}

class XepLoais(tag: Tag) extends AbstractTable[XepLoai](tag, "xeploai") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanVien = foreignKey("xep_loai_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def xepLoai = column[String]("xepLoai", O.NotNull)

  def * = (id.?, nhanVienId, month, year, xepLoai) <>(XepLoai.tupled, XepLoai.unapply)

}

object XepLoais extends AbstractQuery[XepLoai, XepLoais](new XepLoais(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "month" -> number,
      "year" -> number,
      "xepLoai" -> nonEmptyText
    )(XepLoai.apply)(XepLoai.unapply)
  )

  implicit val xepLoaiJsonFormat = Json.format[XepLoai]
}