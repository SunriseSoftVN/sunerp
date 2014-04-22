package dtos.report

import java.util.Date
import scala.beans.BeanProperty
import models.sunerp.{NhanVien, SoPhanCong}

/**
 * The Class SoPhanCongDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/22/14 3:45 PM
 *
 */
class SoPhanCongDto {

  @BeanProperty
  var ngay: String = _

  @BeanProperty
  var tenNV: String = _

  @BeanProperty
  var taskName: String = _

  @BeanProperty
  var taskKL: Double = _

  @BeanProperty
  var taskGio: Double = _

}

object SoPhanCongDto {

  def apply(tuple: (SoPhanCong, NhanVien)) = {
    val (soPhanCong, nhanVien) = tuple
    val dto = new SoPhanCongDto()
    dto.ngay = soPhanCong.ngayPhanCong.getDayOfMonth.toString
    dto.tenNV = nhanVien.lastName + " " + nhanVien.firstName
    dto.taskName = soPhanCong.taskName.getOrElse("")
    dto.taskKL = soPhanCong.khoiLuong
    dto.taskGio = soPhanCong.gio
    dto
  }

}