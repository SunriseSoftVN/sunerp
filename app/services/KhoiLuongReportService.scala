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

  /**
   * Build report
   * @param session
   * @return report file name
   */
  def doReport(fileType: String)(implicit session: Session): String

}

import net.sf.dynamicreports.report.builder.DynamicReports._

class KhoiLuongReportServiceImpl(implicit val bindingModule: BindingModule) extends KhoiLuongReportService {

  override def doReport(fileType: String)(implicit session: Session): String = {
    val reportDir = "report/"
    val xlsFile = "report.xls"
    val pdfFile = "report.pdf"

    val pdfExporter = export.pdfExporter(Play.application.getFile(reportDir + pdfFile))
    val xlsExporter = export.xlsExporter(Play.application.getFile(reportDir + xlsFile))

    val ds = new DRDataSource("item")
    ds.add("macbook")
    ds.add("iphone")
    val builder = report.columns(
      col.column("Item", "item", `type`.stringType())
    ).title(cmp.text("Bình Thạnh, Gò Vấp"))
      .setDataSource(ds)

    fileType match {
      case "pdf" =>
        builder.toPdf(pdfExporter)
        pdfFile
      case "xls" =>
        builder.toXls(xlsExporter)
        xlsFile
      case _ => ???
    }
  }

}
