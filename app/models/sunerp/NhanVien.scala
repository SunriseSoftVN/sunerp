package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * The Class NhanVien.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:56 AM
 *
 */
case class NhanVien(
                     id: Option[Long] = None,
                     firstName: String,
                     lastName: String,
                     heSoLuong: Long,
                     chucVuId: Long,
                     phongBangId: Long
                     ) extends WithId[Long]

class NhanViens(tag: Tag) extends AbstractTable[NhanVien](tag, "nhanVien") {

  def firstName = defColumn[String]("firstName", O.NotNull)

  def lastName = defColumn[String]("lastName", O.NotNull)

  def heSoLuong = defColumn[Long]("heSoLuong", O.NotNull)

  def chucVuId = defColumn[Long]("chucVuId", O.NotNull)

  def chucVu = foreignKey("chuc_vu_nhan_vien_fk", chucVuId, ChucVus)(_.id)

  def phongBangId = defColumn[Long]("phongBangId", O.NotNull)

  def phongBang = foreignKey("phong_bang_nhan_vien_fk", phongBangId, PhongBangs)(_.id)

  def * = (id.?, firstName, lastName, heSoLuong, chucVuId, phongBangId) <>(NhanVien.tupled, NhanVien.unapply)
}

object NhanViens extends AbstractQuery[NhanVien, NhanViens](new NhanViens(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "firstName" -> text(minLength = 4),
      "lastName" -> text(minLength = 4),
      "heSoLuong" -> longNumber,
      "chucVuId" -> longNumber,
      "phongBangId" -> longNumber
    )(NhanVien.apply)(NhanVien.unapply)
  )

  implicit val nhanVienJsonFormat = Json.format[NhanVien]

}