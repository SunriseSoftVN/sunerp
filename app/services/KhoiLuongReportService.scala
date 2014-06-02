package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import play.api.Play
import play.api.Play.current
import dtos.report._
import models.sunerp._
import models.qlkh.{Branchs, Stations, Tasks}
import play.api.db.slick.Config.driver.simple._
import dtos.report.DonViDto
import dtos.report.PhongBanDto
import dtos.report.KhoiLuongDto
import models.sunerp.DonVi
import models.sunerp.PhongBan
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder
import play.api.libs.ws.WS
import scala.concurrent.{Promise, ExecutionContext, Future}
import ExecutionContext.Implicits.global
import dtos.report.qlkh.TaskReportBean
import scala.collection.JavaConverters._

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
  def doThCongViecHangNgay(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String]

  def inSoPhanCong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String

  def inBangChamCong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String]

  def doThKhoiLuong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String]

  def doBcThKhoiLuong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String]

  def doThKhoiLuongQuy(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String]
}

import net.sf.dynamicreports.report.builder.DynamicReports._

class KhoiLuongReportServiceImpl(implicit val bindingModule: BindingModule) extends KhoiLuongReportService {

  val reportDir = "report/"
  lazy val qlkhUrl = Play.configuration.getString("qlkh.url").getOrElse(throw new Exception("Config key 'qlkh.url' is missing"))

  override def inBangChamCong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String] = {
    val promise = Promise[String]()

    def buildReport(tasks: List[TaskDto]) = Future {
      val fileName = s"sophancong-${req.phongBanNameStrip}-thang${req.month}-nam${req.year}"
      val report = KhoiLuongReportColumnBuilder.buildBangChamCong(req)
      val phongBanDto = buildPhongBanData(req.month, req._quarter, req.year, req.getPhongBan, tasks, Nil)
      //build data
      val ds = new JRBeanCollectionDataSource(phongBanDto.bangChamCongs)
      report.setDataSource(ds)
      exportReport(fileType, fileName, report)
    }

    promise.completeWith {
      for {
        tasks <- getTasks
        fileName <- buildReport(tasks)
      } yield fileName
    }

    promise.future
  }

  override def inSoPhanCong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String = {
    val fileName = s"sophancong-${req.phongBanNameStrip}-thang${req.month}-nam${req.year}"
    val report = KhoiLuongReportColumnBuilder.buildSoPhanCong(req)

    val query = for {
      soPhanCong <- SoPhanCongs.khoiLuongMonthQuery(req.month, req.year, req.phongBanId)
      nhanVien <- soPhanCong.nhanVien
    } yield (soPhanCong, nhanVien)

    val data = query
      .sortBy(_._2.firstName.asc)
      .sortBy(_._1.ngayPhanCong.asc)
      .list()
      .map(SoPhanCongDto(_))

    val ds = new JRBeanCollectionDataSource(data.asJavaCollection)
    report.setDataSource(ds)

    exportReport(fileType, fileName, report)
  }

  override def doBcThKhoiLuong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String] = {
    val promise = Promise[String]()

    val station = Stations.findByName(req.donViName)
    val branch = Branchs.findByName(req.phongBanName)

    def callWebService = if (station.isDefined && branch.isDefined) {
      WS.url(s"$qlkhUrl/rest/reportBranch")
        .withQueryString(
          "stationId" -> station.get.getId.toString,
          "branchId" -> branch.get.getId.toString,
          "quarter" -> req.quarter.toString,
          "year" -> req.year.toString
        ).get().map {
        response =>
          if (response.status == 200) {
            response.json.as[List[TaskReportBean]]
          } else Nil
      }
    } else {
      Future.successful(List.empty[TaskReportBean])
    }

    def buildReport(tasks: List[TaskDto], taskExternal: List[TaskReportBean]) = Future {
      val fileName = s"bcthkhoiluong-${req.donViNameStrip}-${req.phongBanNameStrip}-thang${req.month}-nam${req.year}"
      //build data
      val phongBanDto = buildPhongBanData(req.month, req._quarter, req.year, req.getPhongBan, tasks, taskExternal)
      //build layout
      val report = KhoiLuongReportColumnBuilder.buildBcThKhoiLuong(req, phongBanDto.tyLeHoanThanhCongViec)
      val ds = new JRBeanCollectionDataSource(phongBanDto.javaKLRows())
      report.setDataSource(ds)

      exportReport(fileType, fileName, report)
    }

    promise.completeWith {
      for {
        tasks <- getTasks
        taskExternal <- callWebService
        fileName <- buildReport(tasks, taskExternal)
      } yield fileName
    }

    promise.future
  }


  override def doThKhoiLuong(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String] = {
    val promise = Promise[String]()

    def callWebService = Stations.findByName(req.donViName)
      .fold(Future.successful(List.empty[TaskReportBean])) { station =>
      WS.url(s"$qlkhUrl/rest/reportStation")
        .withQueryString(
          "stationId" -> station.getId.toString,
          "quarter" -> req.quarter.toString,
          "year" -> req.year.toString
        ).get().map {
        response =>
          if (response.status == 200) {
            response.json.as[List[TaskReportBean]]
          } else Nil
      }
    }


    def buildReport(tasks: List[TaskDto], taskExternal: List[TaskReportBean]) = Future {
      val fileName = s"khoiluong-${req.donViNameStrip}-thang${req.month}-nam${req.year}"
      //build layout
      val report = KhoiLuongReportColumnBuilder.buildDonViLayout(req)
      val donViDto = buildDonViData(req.month, req._quarter, req.year, req.getDonVi, tasks, taskExternal)
      //build data
      val ds = new JRBeanCollectionDataSource(donViDto.javaKLRows())
      report.setDataSource(ds)

      exportReport(fileType, fileName, report)
    }

    promise.completeWith {
      for {
        tasks <- getTasks
        taskExternal <- callWebService
        fileName <- buildReport(tasks, taskExternal)
      } yield fileName
    }

    promise.future
  }

  override def doThCongViecHangNgay(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String] = {
    val promise = Promise[String]()

    val fileName = s"khoiluong-${req.donViNameStrip}-${req.phongBanNameStrip}-thang${req.month}-nam${req.year}"
    //build layout
    val report = KhoiLuongReportColumnBuilder.buildPhongBanLayout(req)

    def buildReport(tasks: List[TaskDto]) = Future {
      val phongBanDto = buildPhongBanData(req.month, req._quarter, req.year, req.getPhongBan, tasks, Nil)
      //build data
      val ds = new JRBeanCollectionDataSource(phongBanDto.javaKLRows())
      report.setDataSource(ds)
      exportReport(fileType, fileName, report)
    }

    promise.completeWith {
      for {
        tasks <- getTasks
        fileName <- buildReport(tasks)
      } yield fileName
    }

    promise.future
  }

  override def doThKhoiLuongQuy(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): Future[String] = {
    val promise = Promise[String]()

    def buildReport(tasks: List[TaskDto]) = Future {
      val fileName = s"khoiluong-${req.donViNameStrip}-quy${req.quarter}-nam${req.year}"
      //build layout
      val report = KhoiLuongReportColumnBuilder.buildThKhoiLuongQuyLayout(req)
      val donViDto = buildDonViData(req.month, req._quarter, req.year, req.getDonVi, tasks, Nil)
      //build data
      val ds = new JRBeanCollectionDataSource(donViDto.javaKLRows())
      report.setDataSource(ds)

      exportReport(fileType, fileName, report)
    }

    promise.completeWith {
      for {
        tasks <- getTasks
        fileName <- buildReport(tasks)
      } yield fileName
    }

    promise.future
  }

  private def exportReport(fileType: String, fileName: String, report: JasperReportBuilder) = {
    val pdfFile = fileName + ".pdf"
    val xlsFile = fileName + ".xls"
    val pdfExporter = export.pdfExporter(Play.application.getFile(reportDir + pdfFile))
    val xlsExporter = export.xlsExporter(Play.application.getFile(reportDir + xlsFile))
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

  private def buildDonViData(month: Int, quarter: Option[Int], year: Int, donVi: DonVi, tasks: List[TaskDto], taskExternal: List[TaskReportBean])(implicit session: Session) = {
    val phongBans = for {
      phongBan <- PhongBans.findByDonViId(donVi.getId, report = true)
    } yield buildPhongBanData(month, quarter, year, phongBan, tasks, Nil)
    DonViDto(
      id = donVi.getId,
      name = donVi.name,
      tasks = tasks,
      taskExternal = taskExternal,
      children = phongBans
    )
  }

  private def buildPhongBanData(month: Int, quarter: Option[Int], year: Int, phongBan: PhongBan, tasks: List[TaskDto], taskExternal: List[TaskReportBean])(implicit session: Session) = {

    val soPhanCongQuery = quarter
      .map(SoPhanCongs.khoiLuongQuarterQuery(_, year, phongBan.getId))
      .getOrElse(SoPhanCongs.khoiLuongMonthQuery(month, year, phongBan.getId))

    val query = for {
      soPhanCong <- soPhanCongQuery
      nhanVien <- soPhanCong.nhanVien
      soPhanCongEx <- soPhanCong.soPhanCongExt
    } yield (soPhanCong, soPhanCongEx, nhanVien)

    val result = query.list()

    val khoiLuongs = for (tuple <- result) yield {
      val (soPhanCong, soPhanCongEx, nhanVien) = tuple
      //make sure every task in so phan cong always exits.
      val task = tasks.find {
        task => soPhanCong.taskId.isDefined && task.id == soPhanCong.taskId.get
      }
      KhoiLuongDto(
        task = task,
        nhanVien = dtos.report.NhanVienDto(nhanVien),
        khoiLuong = soPhanCong.khoiLuong,
        gio = soPhanCong.gio,
        ngayPhanCong = soPhanCong.ngayPhanCong,
        lamDem = soPhanCongEx.lamDem,
        baoHoLaoDong = soPhanCongEx.baoHoLaoDong,
        docHai = soPhanCongEx.docHai,
        le = soPhanCongEx.le,
        tet = soPhanCongEx.tet,
        thaiSan = soPhanCongEx.thaiSan,
        dauOm = soPhanCongEx.dauOm,
        conOm = soPhanCongEx.conOm,
        taiNanLd = soPhanCongEx.taiNanLd,
        hop = soPhanCongEx.hop,
        hocDaiHan = soPhanCongEx.hocDaiHan,
        hocDotXuat = soPhanCongEx.hocDotXuat,
        viecRieng = soPhanCongEx.viecRieng,
        chuNhat = soPhanCongEx.chuNhat,
        phep = soPhanCongEx.phep
      )
    }

    PhongBanDto(
      id = phongBan.getId,
      name = phongBan.name,
      tasks = tasks,
      taskExternal = taskExternal,
      _khoiLuongs = khoiLuongs,
      month = month,
      year = year
    )
  }

  private def getTasks = WS.url(s"$qlkhUrl/rest/taskTree").get().map { response =>
    if (response.status == 200) {
      response.json.as[List[TaskDto]]
    } else Nil
  }
}
