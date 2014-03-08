package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{NhanViens, NhanVien}
import models.core.AbstractQuery
import play.api.libs.json.Writes

/**
 * The Class NhanVienCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
object NhanVienCtr extends BaseCtr[NhanVien,NhanViens] with MainTemplate {
  override def editForm = NhanViens.editForm
  override implicit val jsonWrite: Writes[NhanVien] = NhanViens.nhanVienJsonFormat
  override val dao: AbstractQuery[NhanVien, NhanViens] = NhanViens
  override val domainName: String = "nhanVien"
}
