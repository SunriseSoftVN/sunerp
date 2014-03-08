package dtos

import models.sunerp._
import models.qlkh.Task
import play.api.libs.json.Json
import models.sunerp.SoPhanCongExt
import models.qlkh.Task
import models.sunerp.NhanVien
import models.sunerp.PhongBang
import models.sunerp.SoPhanCong
import SoPhanCongExts._
import NhanViens._
import models.qlkh.Tasks._
import PhongBangs._
import SoPhanCongs._

/**
 * The Class SoPhanCongDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 7:01 PM
 *
 */
case class SoPhanCongDto(
                          soPhanCong: SoPhanCong,
                          soPhanCongExt: SoPhanCongExt,
                          nhanVien: NhanVien,
                          task: Task,
                          phongBang: PhongBang
                          )


object SoPhanCongDto {


  implicit val jsonFormat = Json.format[SoPhanCongDto]

}