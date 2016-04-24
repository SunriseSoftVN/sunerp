package models.sunerp

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import org.joda.time.{LocalDate, DateTime}
import com.github.tototoshi.slick.MySQLJodaSupport._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import dtos.{SoLuongDto, ExtGirdDto, PagingDto}

/**
 * The Class SoLuong.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 3:37 PM
 *
 */
case class SoLuong(
                    id: Option[Long] = None,
                    nhanVienId: Long,
                    chucVu: String,
                    heSoLuong: Double,
                    luongNd: Long,
                    k2: Double,
                    luongSP: Long,
                    luongTgCong: Double,
                    luongTgTien: Long,
                    cacKhoangCongId: Long,
                    cacKhoangTruId: Long,
                    createdDate: LocalDate
                    ) extends WithId[Long]

class SoLuongs(tag: Tag) extends AbstractTable[SoLuong](tag, "soluong") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanvien = foreignKey("nhanvien_so_luong_fk", nhanVienId, NhanViens)(_.id)

  def chucVu = column[String]("chucVu", O.NotNull)

  def heSoLuong = column[Double]("heSoLuong", O.NotNull)

  def luongNd = column[Long]("luongNd", O.NotNull)

  def k2 = column[Double]("k2", O.NotNull)

  def luongSP = column[Long]("luongSP", O.NotNull)

  def luongTgCong = column[Double]("luongTgCong", O.NotNull)

  def luongTgTien = column[Long]("luongTgTien", O.NotNull)

  def cacKhoangCongId = column[Long]("cacKhoangCongId", O.NotNull)

  def cacKhoangCong = foreignKey("cac_khoang_cong_so_luong_fk", cacKhoangCongId, CacKhoanCongs)(_.id)

  def cacKhoangTruId = column[Long]("cacKhoangTruId", O.NotNull)

  def cacKhoangTru = foreignKey("cac_khoang_tru_so_luong_fk", cacKhoangTruId, CacKhoangTrus)(_.id)

  def createdDate = column[LocalDate]("createdDate", O.NotNull)

  def * = (id.?, nhanVienId, chucVu, heSoLuong, luongNd, k2, luongSP, luongTgCong, luongTgTien, cacKhoangCongId, cacKhoangTruId, createdDate) <>(SoLuong.tupled, SoLuong.unapply)
}

object SoLuongs extends AbstractQuery[SoLuong, SoLuongs](new SoLuongs(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "chucVu" -> text(minLength = 4),
      "heSoLuong" -> of[Double],
      "luongNd" -> longNumber,
      "k2" -> of[Double],
      "luongSP" -> longNumber,
      "luongTgCong" -> of[Double],
      "luongTgTien" -> longNumber,
      "cacKhoangCongId" -> longNumber,
      "cacKhoangTruId" -> longNumber,
      "createdDate" -> jodaLocalDate
    )(SoLuong.apply)(SoLuong.unapply)
  )

  implicit val soLuongJsonFormat = Json.format[SoLuong]

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[SoLuongDto] = {
    var query = for (
      soluong <- this;
      nhanVien <- soluong.nhanvien;
      cacKhoangCong <- soluong.cacKhoangCong;
      cacKhoangTru <- soluong.cacKhoangTru
    ) yield (soluong, nhanVien, cacKhoangCong, cacKhoangTru)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(table => {
        val (soluong, nhanVien, cacKhoangCong, cacKhoangTru) = table
        filter.property match {
          case "nhanVien.firstName" => nhanVien.firstName.toLowerCase like filter.asLikeValue
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (soluong, nhanVien, cacKhoangCong, cacKhoangTru) = table
        sort.property match {
          case "chucVu" => orderColumn(sort.direction, soluong.chucVu)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(SoLuongDto.apply)

    ExtGirdDto[SoLuongDto](
      total = totalRow,
      data = rows
    )
  }
}