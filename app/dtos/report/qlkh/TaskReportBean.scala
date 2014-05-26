package dtos.report.qlkh

import play.api.libs.json.Json

/**
 * The Class TaskReportBean.
 *
 * @author Nguyen Duc Dung
 * @since 4/20/14 10:21 PM
 *
 */
case class TaskReportBean(
                           id: Long,
                           khoiLuong: Option[Double],
                           gio: Double
                           )

object TaskReportBean {

  implicit val jsonFormat = Json.format[TaskReportBean]

}