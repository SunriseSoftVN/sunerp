package models.sunerp

import models.core.{WithId, AbstractQuery, AbstractTable}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import dtos.{XepLoaiDto, ExtGirdDto, PagingDto}

/**
 * The Class XepLoai.
 *
 * @author Nguyen Duc Dung
 * @since 5/9/14 5:07 AM
 *
 */
case class XepLoai(
                    id: Option[Long] = None,
                    nhanVienId: Long,
                    month: Int,
                    year: Int,
                    xepLoai: String = XepLoaiType.A
                    ) extends WithId[Long]

object XepLoaiType {
  val A = "A"
  val B = "B"
  val C = "C"
}

class XepLoais(tag: Tag) extends AbstractTable[XepLoai](tag, "xeploai") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nhanVien = foreignKey("xep_loai_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def xepLoai = column[String]("xepLoai", O.NotNull)

  def * = (id.?, nhanVienId, month, year, xepLoai) <>(XepLoai.tupled, XepLoai.unapply)

}

object XepLoais extends AbstractQuery[XepLoai, XepLoais](new XepLoais(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "nhanVienId" -> longNumber,
      "month" -> number,
      "year" -> number,
      "xepLoai" -> nonEmptyText
    )(XepLoai.apply)(XepLoai.unapply)
  )

  def findByNhanVienId(nhanVienId: Long, month: Int, year: Int)(implicit session: Session) =
    where(xl => xl.nhanVienId === nhanVienId && xl.month === month && xl.year === year).firstOption()


  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[XepLoaiDto] = {
    var query = for (xepLoai <- this; nhanVien <- xepLoai.nhanVien) yield (xepLoai, nhanVien)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(tuple => {
        val (xepLoai, nhanVien) = tuple
        filter.property match {
          case "nhanVien.firstName" => nhanVien.firstName.toLowerCase like filter.asLikeValue
          case "month" => xepLoai.month === filter.asInt
          case "phongBanId" => nhanVien.phongBanId === filter.asLong
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(tuple => {
        val (xepLoai, nhanVien) = tuple
        sort.property match {
          case "nhanVien.fullName" => orderColumn(sort.direction, nhanVien.firstName)
          case "month" => orderColumn(sort.direction, xepLoai.month)
          case "xepLoai" => orderColumn(sort.direction, xepLoai.xepLoai)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(XepLoaiDto(_))

    ExtGirdDto[XepLoaiDto](
      total = totalRow,
      data = rows
    )
  }

  implicit val xepLoaiJsonFormat = Json.format[XepLoai]
}