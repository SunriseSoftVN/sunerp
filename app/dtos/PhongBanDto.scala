package dtos

import models.sunerp.{DonVis, PhongBan, DonVi}
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * The Class PhongBangDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 10:20 AM
 *
 */
case class PhongBanDto(
                         id: Long,
                         name: String,
                         donViId: Long,
                         donVi: DonVi
                         )


object PhongBanDto {

  def apply(tuple: (DonVi, PhongBan)) = {
    val (donVi, phongBang) = tuple
    new PhongBanDto(
      id = phongBang.id.get,
      name = phongBang.name,
      donViId = phongBang.donViId,
      donVi = donVi
    )
  }

  implicit val jsonWrite = new Writes[PhongBanDto] {
    override def writes(o: PhongBanDto): JsValue = Json.obj(
      "id" -> o.id,
      "name" -> o.name,
      "donViId" -> o.donViId,
      "donVi" -> DonVis.donViJsonFormat.writes(o.donVi)
    )
  }
}