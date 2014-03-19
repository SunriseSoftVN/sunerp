package dtos

import models.sunerp.{KhoiDonVis, DonVi, KhoiDonVi}
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * The Class DonViDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 10:11 AM
 *
 */
case class DonViDto(
                     id: Long,
                     name: String,
                     khoiDonViId: Long,
                     khoiDonVi: KhoiDonVi
                     )

object DonViDto {
  def apply(tuple: (DonVi, KhoiDonVi)) = {
    val (donVi, khoiDonVi) = tuple
    new DonViDto(
      id = donVi.id.get,
      name = donVi.name,
      khoiDonViId = donVi.khoiDonViId,
      khoiDonVi = khoiDonVi
    )
  }

  implicit def jsonWrite = new Writes[DonViDto] {
    override def writes(o: DonViDto): JsValue = Json.obj(
      "id" -> o.id,
      "name" -> o.name,
      "khoiDonViId" -> o.khoiDonViId,
      "khoiDonVi" -> KhoiDonVis.khoiDonViJsonFormat.writes(o.khoiDonVi)
    )
  }
}
