package controllers

import _root_.utils.DateTimeUtils
import com.escalatesoft.subcut.inject._
import controllers.element.{AuthConfigImpl, MainTemplate, TransactionElement}
import dtos.{FormErrorDto, PagingDto}
import jp.t2v.lab.play2.auth.AuthElement
import models.sunerp.{KhoaSoPhanCongs, SoPhanCong, SoPhanCongExts, SoPhanCongs}
import play.api.libs.json.Json
import play.api.mvc.Controller
import services.SoPhanCongService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

/**
 * The Class SoPhanCongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
class SoPhanCongCtr(implicit val bindingModule: BindingModule) extends Controller with AuthElement with AuthConfigImpl
with TransactionElement with MainTemplate with Injectable {


  val soPhanCongService = inject[SoPhanCongService]

  def index = StackAction(AuthorityKey -> DomainKey.soPhanCong)(implicit request => {
    val paging = PagingDto(request)
    val result = SoPhanCongs.load(paging)
    Ok(Json.toJson(result))
  })

  def delete(id: Long) = StackAction(AuthorityKey -> DomainKey.soPhanCong)(implicit request => {
    SoPhanCongs.deleteById(id)
    Ok
  })

  def save = StackAction(AuthorityKey -> DomainKey.soPhanCong)(implicit request => {
    SoPhanCongs.editForm.bindFromRequest.fold(
      formError => {
        val errors = formError.errors.map(FormErrorDto.apply)
        BadRequest(Json.toJson(errors))
      },
      tuple => {
        val (id, nhanVienId, taskId, taskName, phongBanId, khoiLuong, gio, ghiChu, ngayPhanCong, soPhanCongExt) = tuple
        val soPhanCongExtId = SoPhanCongExts.save(soPhanCongExt)
        val newId = SoPhanCongs.save(SoPhanCong(
          id = id,
          nhanVienId = nhanVienId,
          taskId = taskId,
          taskName = taskName,
          phongBanId = phongBanId,
          khoiLuong = khoiLuong,
          gio = gio,
          ghiChu = ghiChu,
          ngayPhanCong = ngayPhanCong,
          soPhanCongExtId = soPhanCongExtId
        ))
        Ok(Json.obj(
          "metaData" -> Json.obj(
            "id" -> newId,
            "soPhanCongExtId" -> soPhanCongExtId
          )
        ))
      }
    )
  })

  def update(id: Long) = StackAction(AuthorityKey -> DomainKey.soPhanCong)(implicit request => {
    SoPhanCongs.editForm.bindFromRequest.fold(
      formError => {
        val errors = formError.errors.map(FormErrorDto.apply)
        BadRequest(Json.toJson(errors))
      },
      tuple => {
        val (id, nhanVienId, taskId, taskName, phongBanId, khoiLuong, gio, ghiChu, ngayPhanCong, soPhanCongExt) = tuple
        id.map(_id => {
          SoPhanCongs.findById(_id).map(soPhanCong => {
            SoPhanCongs.update(soPhanCong.copy(
              nhanVienId = nhanVienId,
              taskId = taskId,
              taskName = taskName,
              phongBanId = phongBanId,
              khoiLuong = khoiLuong,
              gio = gio,
              ghiChu = ghiChu,
              ngayPhanCong = ngayPhanCong
            ), id)

            SoPhanCongExts.update(
              soPhanCongExt.copy(id = Some(soPhanCong.soPhanCongExtId)),
              Some(soPhanCong.soPhanCongExtId)
            )
          })
        })
        Ok
      }
    )
  })

  def dayCopyData(month: Int, day: Int, phongBanId: Long) = StackAction(AuthorityKey -> DomainKey.soPhanCong) { implicit request =>
    soPhanCongService.dayCopyData(month, day, phongBanId)
    Ok
  }

  def yesterdayCopyData(month: Int, day: Int, phongBanId: Long) = StackAction(AuthorityKey -> DomainKey.soPhanCong) { implicit request =>
    soPhanCongService.yesterdayCopyData(month, day, phongBanId)
    Ok
  }

  def init(month: Int, phongBanId: Long) = AsyncStack(AuthorityKey -> DomainKey.soPhanCong)(implicit request => {
    Future {
      soPhanCongService.initData(month, phongBanId)
      Ok
    }
  })

  def isLock(month: Int) = StackAction(AuthorityKey -> DomainKey.soPhanCong)(implicit request => {
    val isLock = KhoaSoPhanCongs.isLock(loggedIn.phongBan.donViId, month, DateTimeUtils.currentYear)
    Ok(Json.obj(
      "lock" -> isLock
    ))
  })
}
