package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import net.sf.dynamicreports.report.datasource.DRDataSource

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

import net.sf.dynamicreports.report.builder.DynamicReports._

class KhoiLuongReportServiceImpl(implicit val bindingModule: BindingModule) extends KhoiLuongReportService {

  override def doReport(implicit session: Session): Unit = {
    val ds = new DRDataSource("item")
    ds.add("macbook")
    ds.add("iphone")
    report.columns(
      col.column("Item", "item", `type`.stringType())
    ).title(cmp.text("Dung ne"))
      .setDataSource(ds)
      .show(false)
  }

}
