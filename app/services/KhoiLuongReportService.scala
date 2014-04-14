package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import net.sf.dynamicreports.report.datasource.DRDataSource
import play.api.Play
import play.api.Play.current

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
    val pdfExporter = export.pdfExporter(Play.application.getFile("report/report.pdf"))
      .setCharacterEncoding("UTF-8")

    val ds = new DRDataSource("item")
    ds.add("macbook")
    ds.add("iphone")
    val builder = report.columns(
      col.column("Item", "item", `type`.stringType())
    ).title(cmp.text("Bình Thạnh, Gò Vấp"))
      .setDataSource(ds)

    builder.toPdf(pdfExporter)
  }

}
