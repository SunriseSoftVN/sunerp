package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * The Class LuongPhuCap.
 *
 * @author Nguyen Duc Dung
 * @since 3/5/14 11:34 AM
 *
 */
case class CacKhoangCong(
                         id: Option[Long] = None,
                         phuCapTn: Long,
                         phuCapLd: Long,
                         trucBHLD: Long,
                         phuCapKV: Long,
                         congPhanLuong: Long,
                         chiKhac: Long,
                         luongDocHai: Long,
                         nuocUong: Long,
                         anGiuaCa: Long,
                         omDauSinhDe: Long
                         ) extends WithId[Long]

class CacKhoanCongs(tag: Tag) extends AbstractTable[CacKhoangCong](tag, "cacKhoangCong") {

  def phuCapTn = column[Long]("phuCapTn", O.NotNull)

  def phuCapLd = column[Long]("phuCapLd", O.NotNull)

  def trucBHLD = column[Long]("trucBHLD", O.NotNull)

  def phuCapKV = column[Long]("phuCapKV", O.NotNull)

  def congPhanLuong = column[Long]("congPhanLuong", O.NotNull)

  def chiKhac = column[Long]("chiKhac", O.NotNull)

  def luongDocHai = column[Long]("luongDocHai", O.NotNull)

  def nuocUong = column[Long]("nuocUong", O.NotNull)

  def anGiuaCa = column[Long]("anGiuaCa", O.NotNull)

  def omDauSinhDe = column[Long]("omDauSinhDe", O.NotNull)

  def * = (id.?, phuCapTn, phuCapLd, trucBHLD, phuCapKV, congPhanLuong, chiKhac, luongDocHai, nuocUong, anGiuaCa, omDauSinhDe) <>(CacKhoangCong.tupled, CacKhoangCong.unapply)
}

object CacKhoanCongs extends AbstractQuery[CacKhoangCong, CacKhoanCongs](new CacKhoanCongs(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "phuCapTn" -> longNumber,
      "phuCapLd" -> longNumber,
      "trucBHLD" -> longNumber,
      "phuCapKV" -> longNumber,
      "congPhanLuong" -> longNumber,
      "chiKhac" -> longNumber,
      "luongDocHai" -> longNumber,
      "nuocUong" -> longNumber,
      "anGiuaCa" -> longNumber,
      "omDauSinhDe" -> longNumber
    )(CacKhoangCong.apply)(CacKhoangCong.unapply)
  )

  implicit val cacKhoanCongJsonFormat = Json.format[CacKhoangCong]

}