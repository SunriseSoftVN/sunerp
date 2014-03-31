package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import org.joda.time.DateTime
import com.github.tototoshi.slick.MySQLJodaSupport._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import exception.ForeignKeyNotFound
import models.qlkh.{Task, Tasks}
import dtos.{SoPhanCongDto, ExtGirdDto, PagingDto}

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
                       taskId: Long,
                       phongBangId: Long,
                       khoiLuong: Double,
                       gio: Double,
                       ghiChu: String,
                       soPhanCongExtId: Long,
                       ngayPhanCong: DateTime
                       ) extends WithId[Long] {

  private var _soPhanCongExt: Option[SoPhanCongExt] = None

  def soPhanCongExt(implicit session: Session) = _soPhanCongExt.getOrElse {
    val result = SoPhanCongExts.findById(soPhanCongExtId).getOrElse(throw ForeignKeyNotFound())
    _soPhanCongExt = Some(result)
    result
  }

  private var _task: Option[Task] = None

  def task = _task.getOrElse {
    val result = Tasks.findById(taskId).getOrElse(throw ForeignKeyNotFound())
    _task = Some(result)
    result
  }
}

class SoPhanCongs(tag: Tag) extends AbstractTable[SoPhanCong](tag, "soPhanCong") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanVien = foreignKey("nhan_vien_so_phan_cong_fk", nhanVienId, NhanViens)(_.id)

  def taskId = column[Long]("taskId", O.NotNull)

  def phongBangId = column[Long]("phongBangId", O.NotNull)

  def phongBang = foreignKey("phong_bang_so_phan_cong_fk", phongBangId, PhongBangs)(_.id)

  def khoiLuong = column[Double]("khoiLuong", O.NotNull)

  def gio = column[Double]("gio", O.NotNull)

  def ghiChu = column[String]("ghiChu")

  def soPhanCongExtId = column[Long]("soPhanCongExtId", O.NotNull)

  def soPhanCongExt = foreignKey("so_phan_cong_ext_so_phan_cong_fk", soPhanCongExtId, SoPhanCongExts)(_.id)

  def ngayPhanCong = column[DateTime]("ngayPhanCong", O.NotNull)

  def * = (id.?, nhanVienId, taskId, phongBangId, khoiLuong, gio, ghiChu, soPhanCongExtId, ngayPhanCong) <>(SoPhanCong.tupled, SoPhanCong.unapply)
}

object SoPhanCongs extends AbstractQuery[SoPhanCong, SoPhanCongs](new SoPhanCongs(_)) {

  val soPhanCongQuery = for (
    soPhanCong <- SoPhanCongs;
    nhanVien <- soPhanCong.nhanVien;
    phongBang <- soPhanCong.phongBang;
    soPhanCongExt <- soPhanCong.soPhanCongExt
  ) yield (soPhanCong, soPhanCongExt, nhanVien, phongBang)

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "taskId" -> longNumber,
      "phongBangId" -> longNumber,
      "khoiLuong" -> of[Double],
      "gio" -> of[Double],
      "ghiChu" -> text,
      "soPhanCongExtId" -> longNumber,
      "ngayPhanCong" -> jodaDate
    )(SoPhanCong.apply)(SoPhanCong.unapply)
  )

  implicit val soPhanCongJsonFormat = Json.format[SoPhanCong]

  def load(pagingDto: PagingDto)(implicit session: Session) = {
    var query = soPhanCongQuery

    pagingDto.filters.foreach(filter => {
      query = query.where(tuple => {
        val (soPhanCong, soPhanCongExt, nhanVien, phongBang) = tuple
        filter.property match {
          case "nhanVien.firstName" => nhanVien.firstName.toLowerCase like filter.valueForLike
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(tuple => {
        val (soPhanCong, soPhanCongExt, nhanVien, phongBang) = tuple
        sort.property match {
          case "ghiChu" => orderColumn(sort.direction, soPhanCong.ghiChu)
          case "khoiLuong" => orderColumn(sort.direction, soPhanCong.khoiLuong)
          case "gio" => orderColumn(sort.direction, soPhanCong.gio)
          case "ngayPhanCong" => orderColumn(sort.direction, soPhanCong.ngayPhanCong)
          case "nhanVien.firstName" => orderColumn(sort.direction, nhanVien.firstName)
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
}
