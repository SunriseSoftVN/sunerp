package dtos

import models.sunerp.{Companies, Company, KhoiDonVi}
import play.api.libs.json.{JsValue, Writes, Json}

/**
 * The Class KhoiDonViDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/19/14 9:55 AM
 *
 */
case class KhoiDonViDto(
                         id: Long,
                         name: String,
                         companyId: Long,
                         company: Company
                         )

object KhoiDonViDto {

  def apply(tuple: (KhoiDonVi, Company)) = {
    val (khoiDonVi, company) = tuple
    new KhoiDonViDto(
      id = khoiDonVi.id.get,
      name = khoiDonVi.name,
      companyId = khoiDonVi.companyId,
      company = company
    )
  }

  implicit val jsonWrite = new Writes[KhoiDonViDto] {
    override def writes(o: KhoiDonViDto): JsValue = Json.obj(
      "id" -> o.id,
      "name" -> o.name,
      "companyId" -> o.companyId,
      "company" -> Companies.companyJsonFormat.writes(o.company)
    )
  }

}