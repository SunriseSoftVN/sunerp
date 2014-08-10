package dtos

import models.sunerp._
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * The Class KhoaSoPhanCongDto.
 *
 * @author Nguyen Duc Dung
 * @since 8/9/2014 9:27 PM
 *
 */
case class KhoaSoPhanCongDto(
                              id: Long,
                              lock: Boolean,
                              month: Int,
                              year: Int,
                              donViId: Long,
                              donVi: DonVi
                              )

object KhoaSoPhanCongDto {
  def apply(tuple: (KhoaSoPhanCong, DonVi)) = {
    val (khoaSoPhanCong, donVi) = tuple
    new KhoaSoPhanCongDto(
      id = khoaSoPhanCong.id.get,
      lock = khoaSoPhanCong.lock,
      month = khoaSoPhanCong.month,
      year = khoaSoPhanCong.year,
      donViId = khoaSoPhanCong.donViId,
      donVi = donVi
    )
  }

  implicit def jsonWrite = new Writes[KhoaSoPhanCongDto] {
    override def writes(o: KhoaSoPhanCongDto): JsValue = Json.obj(
      "id" -> o.id,
      "lock" -> o.lock,
      "month" -> o.month,
      "donViId" -> o.donViId,
      "year" -> o.year,
      "donVi" -> DonVis.donViJsonFormat.writes(o.donVi)
    )
  }
}