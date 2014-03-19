package dtos

import models.sunerp.{DonVis, PhongBang, DonVi}
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * The Class PhongBangDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 10:20 AM
 *
 */
case class PhongBangDto(
                         id: Long,
                         name: String,
                         donViId: Long,
                         donVi: DonVi
                         )


object PhongBangDto {

  def apply(tuple: (DonVi, PhongBang)) = {
    val (donVi, phongBang) = tuple
    new PhongBangDto(
      id = phongBang.id.get,
      name = phongBang.name,
      donViId = phongBang.donViId,
      donVi = donVi
    )
  }

  implicit val jsonWrite = new Writes[PhongBangDto] {
    override def writes(o: PhongBangDto): JsValue = Json.obj(
      "id" -> o.id,
      "name" -> o.name,
      "donViId" -> o.donViId,
      "donVi" -> DonVis.donViJsonFormat.writes(o.donVi)
    )
  }
}