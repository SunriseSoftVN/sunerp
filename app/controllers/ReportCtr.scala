package controllers

import play.api.mvc.{SimpleResult, Action, Controller}
import com.escalatesoft.subcut.inject._
import jp.t2v.lab.play2.stackc.StackableController
import jp.t2v.lab.play2.auth.AuthElement
import controllers.element.{TransactionElement, AuthConfigImpl}
import DomainKey.khoiLuongReport
import services.KhoiLuongReportService
import scala.concurrent.{Promise, ExecutionContext, Future}
import ExecutionContext.Implicits.global
import play.api.{Logger, Play}
import play.api.Play.current
import java.io.FileInputStream
import org.apache.commons.io.IOUtils
import dtos.report.KhoiLuongReportRequest


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

  def doKhoiluongreport(fileType: String) = AsyncStack(AuthorityKey -> khoiLuongReport)(implicit request => {
    val promise = Promise[SimpleResult]()
    val req = KhoiLuongReportRequest(request)

    if (req.donVi.isDefined && req.phongBan.isDefined) {
      Future {
        promise.success {
          Ok(khoiLuongReportService.doPhongBanReport(fileType, req))
        }
      }
    } else {
      promise.success(BadRequest)
    }

    promise.future
  })

  def view(file: String, download: Boolean) = Action.async(implicit request => {
    val promise = Promise[SimpleResult]()
    Future {
      Play.application.getExistingFile("report/" + file).map(_file => {
        val input = new FileInputStream(_file)
        try {
          val content = IOUtils.toByteArray(input)
          promise.success {
            val result = Ok(content)
            if (download) {
              result
            } else {
              result.as("application/pdf").withHeaders(
                "Content-Disposition" -> s"inline; filename=$file"
              )
            }
          }
        } catch {
          case ex: Exception =>
            Logger.error(ex.getMessage, ex)
            promise.failure(ex)
        } finally {
          input.close()
        }
      }).getOrElse {
        promise.success(NotFound)
      }
    }

    promise.future
  })
}
