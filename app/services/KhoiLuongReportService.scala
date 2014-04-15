package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import play.api.Play
import play.api.Play.current
import net.sf.dynamicreports.report.constant.{PageType, PageOrientation, WhenNoDataType, HorizontalAlignment}
import java.awt.Color
import scala.collection.JavaConverters._
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import dtos.KhoiLuongReportDto

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

    val report = KhoiLuongReportColumnBuilder.buildLayout()

    val kl = new KhoiLuongReportDto
    kl.setTaskName("dung ne")
    kl.setTaskCode("dung ne")
    kl.setTaskUnit("dung ne")
    kl.setTotal(10)
    kl.getKhoiLuongCongViec.put("1", 18)
    val data = List(kl)
    val ds = new JRBeanCollectionDataSource(data.asJava)

    report.setDataSource(ds)

    fileType match {
      case "pdf" =>
        report.toPdf(pdfExporter)
        pdfFile
      case "xls" =>
        report.toXls(xlsExporter)
        xlsFile
      case _ => ???
    }
  }

}
