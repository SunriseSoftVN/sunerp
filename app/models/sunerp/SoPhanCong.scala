package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import com.github.tototoshi.slick.MySQLJodaSupport._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import models.qlkh.Tasks
import dtos.{SoPhanCongDto, ExtGirdDto, PagingDto}
import com.github.nscala_time.time.Imports._
import scala.Some
import exception.ForeignKeyNotFound
import models.qlkh.Task
import com.github.nscala_time.time.TypeImports.LocalDate
import com.github.nscala_time.time.StaticForwarderImports.LocalDate
import play.api.Logger
import org.joda.time.IllegalFieldValueException
import utils.DateTimeUtils


/**
 * The Class SoPhanCong.
 *
 * @author Nguyen Duc Dung
 * @since 3/7/14 5:42 PM
 *
 */
case class SoPhanCong(
                       id: Option[Long] = None,
                       nhanVienId: Long,
                       taskId: Option[Long] = None,
                       taskName: Option[String] = None,
                       phongBanId: Long,
                       khoiLuong: Double,
                       gio: Double,
                       ghiChu: String,
                       soPhanCongExtId: Long,
                       ngayPhanCong: LocalDate
                       ) extends WithId[Long] {

  private var _soPhanCongExt: Option[SoPhanCongExt] = None

  def soPhanCongExt(implicit session: Session) = _soPhanCongExt.getOrElse {
    val result = SoPhanCongExts.findById(soPhanCongExtId).getOrElse(throw ForeignKeyNotFound())
    _soPhanCongExt = Some(result)
    result
  }

  private var _task: Option[Task] = None

  def task = _task.orElse {
    taskId.flatMap {
      _id =>
        _task = Tasks.findById(_id)
        _task
    }
  }
}

class SoPhanCongs(tag: Tag) extends AbstractTable[SoPhanCong](tag, "soPhanCong") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanVien = foreignKey("nhan_vien_so_phan_cong_fk", nhanVienId, NhanViens)(_.id)

  def taskId = column[Long]("taskId")

  def taskName = column[String]("taskName")

  def phongBanId = column[Long]("phongBanId", O.NotNull)

  def phongBan = foreignKey("phong_ban_so_phan_cong_fk", phongBanId, PhongBans)(_.id)

  def khoiLuong = column[Double]("khoiLuong", O.NotNull)

  def gio = column[Double]("gio", O.NotNull)

  def ghiChu = column[String]("ghiChu")

  def soPhanCongExtId = column[Long]("soPhanCongExtId", O.NotNull)

  def soPhanCongExt = foreignKey("so_phan_cong_ext_so_phan_cong_fk", soPhanCongExtId, SoPhanCongExts)(_.id)

  def ngayPhanCong = column[LocalDate]("ngayPhanCong", O.NotNull)

  def * = (id.?, nhanVienId, taskId.?, taskName.?, phongBanId, khoiLuong, gio, ghiChu, soPhanCongExtId, ngayPhanCong) <>(SoPhanCong.tupled, SoPhanCong.unapply)
}

object SoPhanCongs extends AbstractQuery[SoPhanCong, SoPhanCongs](new SoPhanCongs(_)) {

  val soPhanCongQuery = for (
    soPhanCong <- SoPhanCongs;
    nhanVien <- soPhanCong.nhanVien;
    phongBan <- soPhanCong.phongBan;
    soPhanCongExt <- soPhanCong.soPhanCongExt
  ) yield (soPhanCong, soPhanCongExt, nhanVien, phongBan)

  def editForm(implicit session: Session) = Form(
    tuple(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "taskId" -> optional(longNumber),
      "taskName" -> optional(nonEmptyText),
      "phongBanId" -> longNumber,
      "khoiLuong" -> of[Double],
      "gio" -> of[Double],
      "ghiChu" -> text,
      "ngayPhanCong" -> jodaLocalDate("yyyy-MM-dd'T'HH:mm:ss"),
      "soPhanCongExt" -> SoPhanCongExts.editForm.mapping
    )
  )

  def khoiLuongQuery(month: Int, year: Int, phongBanId: Long) = for (
    soPhanCong <- khoiLuongQueryRange(month, year)
    if soPhanCong.phongBanId === phongBanId
  ) yield soPhanCong

  def khoiLuongQueryRange(month: Int, year: Int) = {
    val date = new LocalDate().withYear(year).withMonth(month)
    val firstDayOfMonth = date.dayOfMonth().withMinimumValue()
    val lastDayOfMonth = date.dayOfMonth().withMaximumValue()
    for (
      soPhanCong <- SoPhanCongs
      if soPhanCong.ngayPhanCong >= firstDayOfMonth && soPhanCong.ngayPhanCong <= lastDayOfMonth
    ) yield soPhanCong
  }

  implicit val soPhanCongJsonFormat = Json.format[SoPhanCong]

  def load(pagingDto: PagingDto)(implicit session: Session) = {
    var query = soPhanCongQuery

    pagingDto.getFilters.foreach(filter => {
      query = query.where(tuple => {
        val (soPhanCong, soPhanCongExt, nhanVien, phongBan) = tuple
        filter.property match {
          case "nhanVien.maNv" => nhanVien.maNv.toLowerCase like filter.asLikeValue
          case "nhanVien.firstName" => nhanVien.firstName.toLowerCase like filter.asLikeValue
          case "phongBanId" => soPhanCong.phongBanId === filter.asLong
          case "month" =>
            val date = new LocalDate().withYear(LocalDate.now.getYear).withMonth(filter.asInt)
            val firstDayOfMonth = date.dayOfMonth().withMinimumValue()
            val lastDayOfMonth = date.dayOfMonth().withMaximumValue()
            soPhanCong.ngayPhanCong >= firstDayOfMonth && soPhanCong.ngayPhanCong <= lastDayOfMonth
          case "day" =>
            val month = pagingDto.findFilters("month").map(_.asInt).getOrElse(LocalDate.now.getMonthOfYear)
            soPhanCong.ngayPhanCong === DateTimeUtils.createLocalDate(month, filter.asInt)
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(tuple => {
        val (soPhanCong, soPhanCongExt, nhanVien, phongBan) = tuple
        sort.property match {
          case "taskName" => orderColumn(sort.direction, soPhanCong.taskName)
          case "ghiChu" => orderColumn(sort.direction, soPhanCong.ghiChu)
          case "khoiLuong" => orderColumn(sort.direction, soPhanCong.khoiLuong)
          case "gio" => orderColumn(sort.direction, soPhanCong.gio)
          case "ngayPhanCong" => orderColumn(sort.direction, soPhanCong.ngayPhanCong)
          case "nhanVien.maNv" => orderColumn(sort.direction, nhanVien.maNv)
          case "soPhanCongExt.chuNhat" => orderColumn(sort.direction, soPhanCongExt.chuNhat)
          case "soPhanCongExt.phep" => orderColumn(sort.direction, soPhanCongExt.phep)
          case "soPhanCongExt.viecRieng" => orderColumn(sort.direction, soPhanCongExt.viecRieng)
          case "soPhanCongExt.lamDem" => orderColumn(sort.direction, soPhanCongExt.lamDem)
          case "soPhanCongExt.baoHoLaoDong" => orderColumn(sort.direction, soPhanCongExt.baoHoLaoDong)
          case "soPhanCongExt.docHai" => orderColumn(sort.direction, soPhanCongExt.docHai)
          case "soPhanCongExt.le" => orderColumn(sort.direction, soPhanCongExt.le)
          case "soPhanCongExt.tet" => orderColumn(sort.direction, soPhanCongExt.tet)
          case "soPhanCongExt.dauOm" => orderColumn(sort.direction, soPhanCongExt.dauOm)
          case "soPhanCongExt.conOm" => orderColumn(sort.direction, soPhanCongExt.conOm)
          case "soPhanCongExt.hop" => orderColumn(sort.direction, soPhanCongExt.hop)
          case "soPhanCongExt.hocDaiHan" => orderColumn(sort.direction, soPhanCongExt.hocDaiHan)
          case "soPhanCongExt.hocDotXuat" => orderColumn(sort.direction, soPhanCongExt.hocDotXuat)
          case "soPhanCongExt.taiNanLd" => orderColumn(sort.direction, soPhanCongExt.taiNanLd)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })


    val totalRow = Query(query.length).first()

    val data = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(SoPhanCongDto(_))

    ExtGirdDto[SoPhanCongDto](
      total = totalRow,
      data = data
    )
  }

  override protected def afterDelete(entity: SoPhanCong)(implicit session: Session): SoPhanCong = {
    //remove soPhanCongExt.
    SoPhanCongExts.deleteById(entity.soPhanCongExtId)
    super.afterDelete(entity)
  }
}
