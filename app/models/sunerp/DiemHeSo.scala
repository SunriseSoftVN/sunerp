package models.sunerp

import models.core.{WithId, AbstractQuery, AbstractTable}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json

/**
 * The Class DiemHeSo.
 *
 * @author Nguyen Duc Dung
 * @since 6/2/2014 9:22 AM
 *
 */
case class DiemHeSo(
                     id: Option[Long] = None,
                     nhanVienId: Long,
                     heSo: Double,
                     year: Int
                     ) extends WithId[Long]

class DiemHeSos(tag: Tag) extends AbstractTable[DiemHeSo](tag, "diemheso") {
  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanVien = foreignKey("xep_loai_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  def heSo = column[Double]("heSo", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def * = (id.?, nhanVienId, heSo, year) <>(DiemHeSo.tupled, DiemHeSo.unapply)
}

object DiemHeSos extends AbstractQuery[DiemHeSo, DiemHeSos](new DiemHeSos(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "heSo" -> of[Double],
      "year" -> number
    )(DiemHeSo.apply)(DiemHeSo.unapply)
  )

  implicit val diemHeSoJsonFormat = Json.format[DiemHeSo]

}