package dtos.report

import play.api.mvc.{AnyContent, Request}
import utils.{String2Long, String2Int}
import org.joda.time.LocalDate

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
                                   donViId: Option[Long] = None,
                                   phongBanId: Option[Long] = None
                                   )


object KhoiLuongReportRequest {

  def apply(request: Request[AnyContent]) = {
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

    val phongBanId = request.getQueryString("phongBanId").collect {
      case String2Long(_phongBanId) => _phongBanId
    }

    val donViId = request.getQueryString("donViId").collect {
      case String2Long(_donViId) => _donViId
    }

    new KhoiLuongReportRequest(
      month = month,
      year = year,
      donViId = donViId,
      phongBanId = phongBanId
    )
  }

}
