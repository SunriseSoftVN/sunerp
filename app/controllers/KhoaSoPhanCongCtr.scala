package controllers

import com.escalatesoft.subcut.inject.BindingModule
import controllers.element.{BaseCtr, MainTemplate}
import dtos.{KhoaSoPhanCongDto, PagingDto}
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import models.core.AbstractQuery
import models.sunerp.{DonVis, KhoaSoPhanCong, KhoaSoPhanCongs}
import play.api.data.Form
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.Session
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.AnyContent

/**
 * The Class KhoaSoPhanCongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 8/9/2014 9:04 PM
 *
 */
class KhoaSoPhanCongCtr(implicit val bindingModule: BindingModule) extends BaseCtr[KhoaSoPhanCong, KhoaSoPhanCongs] with MainTemplate {
  override val domainName: String = DomainKey.khoaSoPhanCong
  override def editForm(implicit session: Session): Form[KhoaSoPhanCong] = KhoaSoPhanCongs.editForm
  override val dao: AbstractQuery[KhoaSoPhanCong, KhoaSoPhanCongs] = KhoaSoPhanCongs
  override implicit val jsonWrite: Writes[KhoaSoPhanCong] = KhoaSoPhanCongs.jsonFormat

  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    implicit val jsonWrite = KhoaSoPhanCongDto.jsonWrite
    var result = KhoaSoPhanCongs.load(paging)
    val month = paging.findFilters("month").get.asInt
    val year = paging.findFilters("year").get.asInt
    val query = for {
      khoaSoPhanCong <- KhoaSoPhanCongs
      if khoaSoPhanCong.month === month && khoaSoPhanCong.year === year
    } yield khoaSoPhanCong

    if (Query(query.length).first() == 0) {
      val donVis = DonVis.all
      for (donVi <- donVis) {
        val khoaSoPhanCong = KhoaSoPhanCong(
          donViId = donVi.getId,
          lock = false,
          month = month,
          year = year
        )
        KhoaSoPhanCongs.save(khoaSoPhanCong)
      }
      result = KhoaSoPhanCongs.load(paging)
    }
    Json.toJson(result)
  }
}
