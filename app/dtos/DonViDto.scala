package dtos

import models.sunerp._
import play.api.libs.json.{Json, JsValue, Writes}
import models.sunerp.DonVi
import models.sunerp.Company

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
                     companyId: Long,
                     company: Company
                     )

object DonViDto {
  def apply(tuple: (DonVi, Company)) = {
    val (donVi, company) = tuple
    new DonViDto(
      id = donVi.id.get,
      name = donVi.name,
      companyId = donVi.companyId,
      company = company
    )
  }

  implicit def jsonWrite = new Writes[DonViDto] {
    override def writes(o: DonViDto): JsValue = Json.obj(
      "id" -> o.id,
      "name" -> o.name,
      "companyId" -> o.companyId,
      "company" -> Companies.companyJsonFormat.writes(o.company)
    )
  }
}
