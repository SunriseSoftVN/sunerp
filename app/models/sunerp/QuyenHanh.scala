package models.sunerp

import models.core.{AbstractTable, AbstractQuery, WithId}
import play.api.db.slick.Config.driver.simple._
import dtos.{QuyenHanhDto, ExtGirdDto, PagingDto}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import models.sunerp

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
                      gioiHan: String = GioiHan.CONGTY,
                      chucVuId: Option[Long] = None,
                      phongBanId: Option[Long] = None
                      ) extends WithId[Long]


class QuyenHanhs(tag: Tag) extends AbstractTable[QuyenHanh](tag, "quyenHanh") {
  def domain = column[String]("domain", O.NotNull)

  def read = column[Boolean]("read", O.NotNull)

  def write = column[Boolean]("write", O.NotNull)

  def gioiHan = column[String]("gioiHan", O.NotNull)

  def chucVuId = column[Long]("chucVuId")

  def phongBanId = column[Long]("phongBanId")

  def chucVu = foreignKey("quyen_hanh_chuc_vu_fk", chucVuId, ChucVus)(_.id)

  def phongBan = foreignKey("quyen_hanh_phong_ban_fk", phongBanId, PhongBans)(_.id)

  def * = (id.?, domain, read, write, gioiHan, chucVuId.?, phongBanId.?) <>(QuyenHanh.tupled, QuyenHanh.unapply)
}

object QuyenHanhs extends AbstractQuery[QuyenHanh, QuyenHanhs](new QuyenHanhs(_)) {

  def findByNhanVienId(nhanVienId: Long)(implicit session: Session) = NhanViens.findById(nhanVienId).map(nhanVien => {
    where(_.chucVuId === nhanVien.chucVuId).list()
  }).getOrElse(Nil)

  def findByChucVuId(chucVuId: Long)(implicit session: Session) = where(_.chucVuId === chucVuId).list()

  def findByPhongBanId(phongBanId: Long)(implicit session: Session) = where(_.phongBanId === phongBanId).list()

  def findByChucVuAndPhongBanId(chucVuId: Long, phongBanId: Long)(implicit session: Session) =
    where(qh => qh.chucVuId === chucVuId && qh.phongBanId === phongBanId).list()

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[QuyenHanhDto] = {
    var query = for {
      ((quyenHanh, chucVu), phongBan) <- this leftJoin ChucVus on (_.chucVuId === _.id) leftJoin PhongBans on (_._1.phongBanId === _.id)
    } yield (quyenHanh, chucVu.name.?, phongBan.name.?)

    pagingDto.getFilters.foreach(filter => {
      query = query.where(table => {
        val (quyenHanh, chucVuName, phongBanName) = table
        filter.property match {
          case "domain" => quyenHanh.domain.toLowerCase like filter.asLikeValue
          case "chucVuId" => quyenHanh.chucVuId === filter.asLong
          case "phongBanId" => quyenHanh.phongBanId === filter.asLong
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })


    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (quyenHanh, chucVuName, phongBanName) = table
        sort.property match {
          case "domain" => orderColumn(sort.direction, quyenHanh.domain)
          case "read" => orderColumn(sort.direction, quyenHanh.read)
          case "write" => orderColumn(sort.direction, quyenHanh.write)
          case "gioiHan" => orderColumn(sort.direction, quyenHanh.gioiHan)
          case "chucVu.name" => orderColumn(sort.direction, chucVuName)
          case "phongBan.name" => orderColumn(sort.direction, phongBanName)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val countQuery = for {
      row <- query
    } yield row._1.id

    val totalRow = Query(countQuery.length).first()

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
      "gioiHan" -> nonEmptyText,
      "chucVuId" -> optional(longNumber),
      "phongBanId" -> optional(longNumber)
    )(QuyenHanh.apply)(QuyenHanh.unapply)
  )

  implicit val jsonFormat = Json.format[QuyenHanh]
}