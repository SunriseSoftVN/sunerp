package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._

import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.data.Form

/**
 * The Class SoPhanCongExt.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 6:14 PM
 *
 */
case class SoPhanCongExt(
                          id: Option[Long] = None,
                          lamDem: Boolean = false,
                          baoHoLaoDong: Boolean = false,
                          docHai: Boolean = false,
                          le: Boolean = false,
                          tet: Boolean = false,
                          thaiSan: Boolean = false,
                          dauOm: Boolean = false,
                          conOm: Boolean = false,
                          taiNanLd: Boolean = false,
                          hop: Boolean = false,
                          hocDaiHan: Boolean = false,
                          hocDotXuat: Boolean = false,
                          viecRieng: Boolean = false,
                          chuNhat: Boolean = false
                          ) extends WithId[Long]


class SoPhanCongExts(tag: Tag) extends AbstractTable[SoPhanCongExt](tag, "soPhanCongExt") {

  def lamDem = column[Boolean]("lamDem", O.NotNull, O.Default(false))

  def baoHoLaoDong = column[Boolean]("baoHoLaoDong", O.NotNull, O.Default(false))

  def docHai = column[Boolean]("docHai", O.NotNull, O.Default(false))

  def le = column[Boolean]("le", O.NotNull, O.Default(false))

  def tet = column[Boolean]("tet", O.NotNull, O.Default(false))

  def thaiSan = column[Boolean]("thaiSan", O.NotNull, O.Default(false))

  def dauOm = column[Boolean]("dauOm", O.NotNull, O.Default(false))

  def conOm = column[Boolean]("conOm", O.NotNull, O.Default(false))

  def taiNanLd = column[Boolean]("taiNanLd", O.NotNull, O.Default(false))

  def hop = column[Boolean]("hop", O.NotNull, O.Default(false))

  def hocDaiHan = column[Boolean]("hocDaiHan", O.NotNull, O.Default(false))

  def hocDotXuat = column[Boolean]("hocDotXuat", O.NotNull, O.Default(false))

  def viecRieng = column[Boolean]("viecRieng", O.NotNull, O.Default(false))

  def chuNhat = column[Boolean]("chuNhat", O.NotNull, O.Default(false))

  def * = (id.?, lamDem, baoHoLaoDong, docHai, le, tet, thaiSan, dauOm, conOm, taiNanLd, hop, hocDaiHan, hocDotXuat, viecRieng, chuNhat) <>(SoPhanCongExt.tupled, SoPhanCongExt.unapply)
}

object SoPhanCongExts extends AbstractQuery[SoPhanCongExt, SoPhanCongExts](new SoPhanCongExts(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "lamDem" -> boolean,
      "baoHoLaoDong" -> boolean,
      "docHai" -> boolean,
      "le" -> boolean,
      "tet" -> boolean,
      "thaiSan" -> boolean,
      "dauOm" -> boolean,
      "conOm" -> boolean,
      "taiNanLd" -> boolean,
      "hop" -> boolean,
      "hocDaiHan" -> boolean,
      "hocDotXuat" -> boolean,
      "viecRieng" -> boolean,
      "chuNhat" -> boolean
    )(SoPhanCongExt.apply)(SoPhanCongExt.unapply)
  )

  implicit val soPhanCongExtJsonFormat = Json.format[SoPhanCongExt]

}