package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session

/**
 * The Class ReportService.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 12:00 PM
 *
 */
trait ReportService {
  def test(implicit session: Session): String
}

class ReportServiceImpl(implicit val bindingModule: BindingModule) extends ReportService{
  override def test(implicit session: Session): String = "dung ne"
}