package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._

/**
 * The Class LuongPhuCap.
 *
 * @author Nguyen Duc Dung
 * @since 3/5/14 11:34 AM
 *
 */
case class CacKhoanCong(
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

class CacKhoanCongs(tag: Tag) extends AbstractTable[CacKhoanCong](tag, "cacKhoanCong") {

  def phuCapTn = defColumn[Long]("phuCapTn", O.NotNull)

  def phuCapLd = defColumn[Long]("phuCapLd", O.NotNull)

  def trucBHLD = defColumn[Long]("trucBHLD", O.NotNull)

  def phuCapKV = defColumn[Long]("phuCapKV", O.NotNull)

  def congPhanLuong = defColumn[Long]("congPhanLuong", O.NotNull)

  def chiKhac = defColumn[Long]("chiKhac", O.NotNull)

  def luongDocHai = defColumn[Long]("luongDocHai", O.NotNull)

  def nuocUong = defColumn[Long]("nuocUong", O.NotNull)

  def anGiuaCa = defColumn[Long]("anGiuaCa", O.NotNull)

  def omDauSinhDe = defColumn[Long]("omDauSinhDe", O.NotNull)

  def * = (id.?, phuCapTn, phuCapLd, trucBHLD, phuCapKV, congPhanLuong, chiKhac, luongDocHai, nuocUong, anGiuaCa, omDauSinhDe) <>(CacKhoanCong.tupled, CacKhoanCong.unapply)
}

object CacKhoanCongs extends AbstractQuery[CacKhoanCong, CacKhoanCongs](new CacKhoanCongs(_)) {

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
    )(CacKhoanCong.apply)(CacKhoanCong.unapply)
  )

}