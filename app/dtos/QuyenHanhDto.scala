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
                         read: Boolean,
                         write: Boolean,
                         chucVuId: Option[Long],
                         phongBanId: Option[Long],
                         gioiHan: String,
                         chucVu: ChucVu
                         )


object QuyenHanhDto {

  def apply(tuple: (QuyenHanh, ChucVu, PhongBan)) = new QuyenHanhDto(
    id = tuple._1.id.get,
    domain = tuple._1.domain,
    write = tuple._1.write,
    read = tuple._1.read,
    chucVuId = tuple._1.chucVuId,
    phongBanId = tuple._1.phongBanId,
    gioiHan = tuple._1.gioiHan,
    chucVu = tuple._2
  )

  implicit val jsonFormat = new Writes[QuyenHanhDto] {
    override def writes(o: QuyenHanhDto) = Json.obj(
      "id" -> o.id,
      "domain" -> o.domain,
      "read" -> o.read,
      "write" -> o.write,
      "chucVuId" -> o.chucVuId,
      "phongBanId" -> o.phongBanId,
      "gioiHan" -> o.gioiHan,
      "chucVu" -> ChucVus.chucVuJsonFormat.writes(o.chucVu)
    )
  }
}