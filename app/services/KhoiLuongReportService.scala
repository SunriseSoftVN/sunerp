package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import models.sunerp.{SoPhanCongs, SoPhanCong}

/**
 * The Class KhoiLuongReportService.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 2:10 PM
 *
 */
trait KhoiLuongReportService {

  def doReport(implicit session: Session)

}

class KhoiLuongReportServiceImpl(implicit val bindingModule: BindingModule) extends KhoiLuongReportService {
  override def doReport(implicit session: Session): Unit = {
    SoPhanCongs.all
  }
}
