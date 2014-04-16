package controllers

import controllers.element.{TransactionElement, AuthConfigImpl, MainTemplate}
import models.sunerp.{SoPhanCong, SoPhanCongExts, SoPhanCongs}
import play.api.libs.json.Json
import dtos.{FormErrorDto, PagingDto}
import play.api.mvc.Controller
import jp.t2v.lab.play2.auth.AuthElement
import services.SoPhanCongService
import com.escalatesoft.subcut.inject._
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global

/**
 * The Class SoPhanCongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
class SoPhanCongCtr(implicit val bindingModule: BindingModule) extends Controller with AuthElement with AuthConfigImpl
with TransactionElement with MainTemplate with Injectable {

  val domainName = DomainKey.soPhanCong

  val soPhanCongService = inject[SoPhanCongService]

  def index = StackAction(AuthorityKey -> domainName)(implicit request => {
    val paging = PagingDto(request)
    val result = SoPhanCongs.load(paging)
    Ok(Json.toJson(result))
  })

  def delete(id: Long) = StackAction(AuthorityKey -> domainName)(implicit request => {
    SoPhanCongs.deleteById(id)
    Ok
  })

  def save = StackAction(AuthorityKey -> domainName)(implicit request => {
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

  def update(id: Long) = StackAction(AuthorityKey -> domainName)(implicit request => {
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

  def init(month: Int) = AsyncStack(AuthorityKey -> domainName)(implicit request => {
    Future {
      Ok
    }
  })

}
