package models.sunerp

import models.core.{AbstractTable, AbstractQuery, WithId}
import play.api.db.slick.Config.driver.simple._
import dtos.{QuyenHanhDto, ExtGirdDto, PagingDto}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * The Class QuyenHanh.
 *
 * @author Nguyen Duc Dung
 * @since 3/21/14 11:15 AM
 *
 */
case class QuyenHanh(
                      id: Option[Long] = None,
                      domain: String,
                      read: Boolean = true,
                      write: Boolean = true,
                      chucVuId: Long
                      ) extends WithId[Long]


class QuyenHanhs(tag: Tag) extends AbstractTable[QuyenHanh](tag, "quyenHanh") {
  def domain = column[String]("domain", O.NotNull)

  def read = column[Boolean]("read", O.NotNull)

  def write = column[Boolean]("write", O.NotNull)

  def chucVuId = column[Long]("chucVuId", O.NotNull)

  def chucVu = foreignKey("quyen_hanh_chuc_vu_fk", chucVuId, ChucVus)(_.id)

  def * = (id.?, domain, read, write, chucVuId) <>(QuyenHanh.tupled, QuyenHanh.unapply)
}

object QuyenHanhs extends AbstractQuery[QuyenHanh, QuyenHanhs](new QuyenHanhs(_)) {

  def findByNhanVienId(nhanVienId: Long)(implicit session: Session) = NhanViens.findById(nhanVienId).map(nhanVien => {
    where(_.chucVuId === nhanVien.chucVuId).list()
  }).getOrElse(Nil)

  def findByChucVuId(chucVuId: Long)(implicit session: Session) = where(_.chucVuId === chucVuId).list()

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[QuyenHanhDto] = {
    var query = for (quyenHanh <- this; chucVu <- quyenHanh.chucVu) yield (quyenHanh, chucVu)

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        val (quyenHanh, chucVu) = table
        filter.property match {
          case "domain" => quyenHanh.domain.toLowerCase like filter.valueForLike
          case "chucVu.name" => chucVu.name.toLowerCase like filter.valueForLike
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (quyenHanh, chucVu) = table
        sort.property match {
          case "domain" => orderColumn(sort.direction, quyenHanh.domain)
          case "read" => orderColumn(sort.direction, quyenHanh.read)
          case "write" => orderColumn(sort.direction, quyenHanh.write)
          case "chucVu.name" => orderColumn(sort.direction, chucVu.name)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(QuyenHanhDto.apply)

    ExtGirdDto[QuyenHanhDto](
      total = totalRow,
      data = rows
    )
  }

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "domain" -> nonEmptyText,
      "read" -> boolean,
      "write" -> boolean,
      "chucVuId" -> longNumber
    )(QuyenHanh.apply)(QuyenHanh.unapply)
  )

  implicit val jsonFormat = Json.format[QuyenHanh]
}