package controllers

import play.api.mvc.Controller
import com.escalatesoft.subcut.inject._
import jp.t2v.lab.play2.stackc.StackableController
import jp.t2v.lab.play2.auth.AuthElement
import controllers.element.{TransactionElement, AuthConfigImpl}
import DomainKey.khoiLuongReport
import services.KhoiLuongReportService
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global

/**
 * The Class ReportCtr.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 8:34 AM
 *
 */
class ReportCtr(implicit val bindingModule: BindingModule) extends Controller with StackableController
with AuthElement with AuthConfigImpl with TransactionElement with Injectable {

  val khoiLuongReportService = inject[KhoiLuongReportService]

  def doKhoiluongreport = AsyncStack(AuthorityKey -> khoiLuongReport)(implicit request => {
    Future {
      khoiLuongReportService.doReport
      Ok
    }
  })
}
