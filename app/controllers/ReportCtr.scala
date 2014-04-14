package controllers

import play.api.mvc.{Action, Controller}
import com.escalatesoft.subcut.inject._
import services.ReportService

/**
 * The Class ReportCtr.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 8:34 AM
 *
 */
class ReportCtr(implicit val bindingModule: BindingModule) extends Controller with Injectable {

  val reportService = inject[ReportService]

  def test = Action(implicit request => Ok)

}
