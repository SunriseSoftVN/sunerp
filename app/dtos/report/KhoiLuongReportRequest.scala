package dtos.report

import play.api.mvc.{AnyContent, Request}
import utils.{StringUtils, String2Long, String2Int}
import org.joda.time.LocalDate
import models.sunerp.{DonVis, PhongBans, PhongBan, DonVi}
import play.api.db.slick.Session

/**
 * The Class KhoiLuongReportRequest.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 3:25 PM
 *
 */
case class KhoiLuongReportRequest(
                                   month: Int,
                                   year: Int,
                                   donVi: Option[DonVi] = None,
                                   phongBan: Option[PhongBan] = None
                                   ) {

  def donViName = donVi.get.name

  def donViId = donVi.get.id

  def phongBanName = phongBan.get.name

  def phongBanId = phongBan.get.id

  def donViNameStrip = StringUtils.convertNonAscii(donViName.toLowerCase).replaceAll(" ", "-")

  def phongBanNameStrip = StringUtils.convertNonAscii(phongBanName.toLowerCase).replaceAll(" ", "-")
}


object KhoiLuongReportRequest {

  def apply(request: Request[AnyContent])(implicit session: Session) = {
    val month = request.getQueryString("month").collect {
      case String2Int(_month) => _month
    }.getOrElse {
      //get current month
      LocalDate.now.getMonthOfYear
    }

    val year = request.getQueryString("year").collect {
      case String2Int(_year) => _year
    }.getOrElse {
      //get current year
      LocalDate.now.getYear
    }

    val phongBan = request.getQueryString("phongBanId").flatMap {
      case String2Long(_id) => PhongBans.findById(_id)
      case _ => None
    }

    val donVi = request.getQueryString("donViId").flatMap {
      case String2Long(_id) => DonVis.findById(_id)
      case _ => None
    }

    new KhoiLuongReportRequest(
      month = month,
      year = year,
      donVi = donVi,
      phongBan = phongBan
    )
  }

}
