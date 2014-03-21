package dtos

import models.sunerp._
import play.api.libs.json.{Json, Writes}

/**
 * The Class QuyenHanhDto.
 *
 * @author Nguyen Duc Dung
 * @since 3/21/14 11:22 AM
 *
 */
case class QuyenHanhDto(
                         id: Long,
                         domain: String,
                         chucVuId: Long,
                         chucVu: ChucVu
                         )


object QuyenHanhDto {

  def apply(tuple: (QuyenHanh, ChucVu)) = new QuyenHanhDto(
    id = tuple._1.id.get,
    domain = tuple._1.domain,
    chucVuId = tuple._1.chucVuId,
    chucVu = tuple._2
  )

  implicit val jsonFormat = new Writes[QuyenHanhDto] {
    override def writes(o: QuyenHanhDto) = Json.obj(
      "id" -> o.id,
      "domain" -> o.domain,
      "chucVuId" -> o.chucVuId,
      "chucVu" -> ChucVus.chucVuJsonFormat.writes(o.chucVu)
    )
  }
}