package controllers

import play.api.mvc.{SimpleResult, Action, Controller}
import com.escalatesoft.subcut.inject._
import jp.t2v.lab.play2.stackc.StackableController
import jp.t2v.lab.play2.auth.AuthElement
import controllers.element.{TransactionElement, AuthConfigImpl}
import DomainKey._
import services.KhoiLuongReportService
import scala.concurrent.{Promise, ExecutionContext, Future}
import ExecutionContext.Implicits.global
import play.api.{Logger, Play}
import play.api.Play.current
import java.io.FileInputStream
import org.apache.commons.io.IOUtils
import dtos.report.KhoiLuongReportRequest
import scala.util.{Failure, Success}


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

  def doDonViReport(fileType: String) = AsyncStack(AuthorityKey -> thThucHienKhoiLuong)(implicit request => {
    val promise = Promise[SimpleResult]()
    val req = KhoiLuongReportRequest(request)
    if (req.donVi.isDefined && req.phongBan.isEmpty) {
      val f = khoiLuongReportService.doThCongViecHangNgay(fileType, req)
      f.onComplete {
        case Success(fileName) => promise.success(Ok(fileName))
        case Failure(t) => promise.failure(t)
      }
    } else {
      promise.success(BadRequest)
    }
    promise.future
  })

  def doPhongBanReport(fileType: String) = AsyncStack(AuthorityKey -> thCongViecHangNgay)(implicit request => {
    val promise = Promise[SimpleResult]()
    val req = KhoiLuongReportRequest(request)
    if (req.donVi.isDefined && req.phongBan.isDefined) {
      val f = khoiLuongReportService.doThKhoiLuong(fileType, req)
      f.onComplete {
        case Success(fileName) => promise.success(Ok(fileName))
        case Failure(t) => promise.failure(t)
      }
    } else {
      promise.success(BadRequest)
    }
    promise.future
  })

  def inSoPhanCong(fileType: String) = AsyncStack(AuthorityKey -> DomainKey.inSoPhanCong)(implicit request => {
    Future {
      val req = KhoiLuongReportRequest(request)
      if (req.donVi.isDefined && req.phongBan.isDefined) {
        val result = khoiLuongReportService.inSoPhanCong(fileType, req)
        Ok(result)
      } else {
        BadRequest
      }
    }
  })

  def inBangChamCong(fileType: String) = AsyncStack(AuthorityKey -> DomainKey.bangChamCong)(implicit request => {
    val promise = Promise[SimpleResult]()
    val req = KhoiLuongReportRequest(request)
    if (req.donVi.isDefined && req.phongBan.isDefined) {
      val f = khoiLuongReportService.inBangChamCong(fileType, req)
      f.onComplete {
        case Success(fileName) => promise.success(Ok(fileName))
        case Failure(t) => promise.failure(t)
      }
    } else {
      promise.success(BadRequest)
    }
    promise.future
  })

  def doBcThKhoiLuongReport(fileType: String) = AsyncStack(AuthorityKey -> bcThucHienKhoiLuong)(implicit request => {
    val promise = Promise[SimpleResult]()
    val req = KhoiLuongReportRequest(request)
    if (req.donVi.isDefined && req.phongBan.isDefined) {
      val f = khoiLuongReportService.doBcThKhoiLuong(fileType, req)
      f.onComplete {
        case Success(fileName) => promise.success(Ok(fileName))
        case Failure(t) => promise.failure(t)
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
          //delete temp file
          _file.delete()
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
