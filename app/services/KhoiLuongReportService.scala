package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import play.api.Play
import play.api.Play.current
import scala.collection.JavaConverters._
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import dtos.report.{KhoiLuongReportRequest, PhongBanKhoiLuongRow}
import models.sunerp.SoPhanCongs
import models.qlkh.Tasks
import play.api.db.slick.Config.driver.simple._

/**
 * The Class KhoiLuongReportService.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 2:10 PM
 *
 */
trait KhoiLuongReportService {

  /**
   * Build report for a "PhongBan".
   * @param session
   * @return report file name
   */
  def doPhongBanReport(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String

}

import net.sf.dynamicreports.report.builder.DynamicReports._

class KhoiLuongReportServiceImpl(implicit val bindingModule: BindingModule) extends KhoiLuongReportService {

  val reportDir = "report/"

  override def doPhongBanReport(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String = {
    val fileName = s"khoiluong-${req.donViNameStrip}-${req.phongBanNameStrip}-thang${req.month}-nam${req.year}"
    val pdfFile = fileName + ".pdf"
    val xlsFile = fileName + ".xls"
    val pdfExporter = export.pdfExporter(Play.application.getFile(reportDir + pdfFile))
    val xlsExporter = export.xlsExporter(Play.application.getFile(reportDir + xlsFile))
    val report = KhoiLuongReportColumnBuilder.buildPhongBanLayout(req)
    val ds = new JRBeanCollectionDataSource(buildPhongBanData(req))
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

  private def buildPhongBanData(req: KhoiLuongReportRequest)(implicit session: Session) = {
    val tasks = Tasks.all

    val query = for (
      soPhanCong <- SoPhanCongs.soPhanCongCommonQuery(req.month, req.year, req.phongBanId);
      soPhanCongExt <- soPhanCong.soPhanCongExt;
      nhanVien <- soPhanCong.nhanVien
    ) yield (soPhanCong, nhanVien, soPhanCongExt)

    val result = query.list()

    for (tuple <- result) {
      val (soPhanCong, nhanVien, soPhanCongExt) = tuple
      //make sure every task in so phan cong all aways exits.
      val task = tasks.find(_.id == soPhanCong.taskId).get
    }


    val kl = new PhongBanKhoiLuongRow
    kl.setTaskName("dung ne")
    kl.setTaskCode("dung ne")
    kl.setTaskUnit("dung ne")
    kl.setTotalKhoiLuong(10)
    kl.getKhoiLuongCongViec.put("1", 18)
    val data = List(kl)
    data.asJava
  }

}
