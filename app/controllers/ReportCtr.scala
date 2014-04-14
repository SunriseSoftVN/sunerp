package controllers

import play.api.mvc.{Action, Controller}
import com.escalatesoft.subcut.inject._
import services.ReportService
import jp.t2v.lab.play2.stackc.StackableController
import jp.t2v.lab.play2.auth.AuthElement
import controllers.element.{TransactionElement, AuthConfigImpl}
import DomainKey.khoiLuongReport

/**
 * The Class ReportCtr.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 8:34 AM
 *
 */
class ReportCtr(implicit val bindingModule: BindingModule) extends Controller with StackableController
with AuthElement with AuthConfigImpl with TransactionElement with Injectable {

  val reportService = inject[ReportService]

  def test = StackAction(AuthorityKey -> khoiLuongReport)(implicit request => {
    Ok(reportService.test)
  })

}
