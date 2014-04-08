package models.sunerp

import models.core.{Hash, AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import dtos.{NhanVienDto, ExtGirdDto, PagingDto}
import org.apache.commons.digester.SimpleRegexMatcher
import org.apache.commons.lang3.StringUtils

/**
 * The Class NhanVien.
 *
 * @author Nguyen Duc Dung
 * @since 3/4/14 9:56 AM
 *
 */
case class NhanVien(
                     id: Option[Long] = None,
                     maNv: String,
                     password: String,
                     firstName: String,
                     lastName: String,
                     heSoLuong: Long,
                     chucVuId: Long,
                     phongBangId: Long
                     ) extends WithId[Long] {

  private var _quyenHanhs: Option[List[QuyenHanh]] = None

  def quyenHanhs(implicit session: Session): List[QuyenHanh] = _quyenHanhs.getOrElse {
    _quyenHanhs = Some(QuyenHanhs.findByChucVuId(chucVuId))
    _quyenHanhs.get
  }

  def checkAuth(authority: String)(implicit session: Session) = {
    val matcher = new SimpleRegexMatcher
    quyenHanhs.exists(quyenHanh => StringUtils.isBlank(authority) || matcher.`match`(authority.toLowerCase, quyenHanh.domain.toLowerCase))
  }

  def menuAuth(authority: String)(implicit session: Session) = {
    val matcher = new SimpleRegexMatcher
    quyenHanhs.exists(quyenHanh => {
      val isFullAccess = quyenHanh.write && quyenHanh.read
      val check = matcher.`match`(authority.toLowerCase, quyenHanh.domain.toLowerCase)
      StringUtils.isBlank(authority) || (check && isFullAccess)
    })
  }

}

class NhanViens(tag: Tag) extends AbstractTable[NhanVien](tag, "nhanVien") {

  def maNv = column[String]("maNv", O.NotNull)

  def password = column[String]("password", O.NotNull)

  def firstName = column[String]("firstName", O.NotNull)

  def lastName = column[String]("lastName", O.NotNull)

  def heSoLuong = column[Long]("heSoLuong", O.NotNull)

  def chucVuId = column[Long]("chucVuId", O.NotNull)

  def chucVu = foreignKey("chuc_vu_nhan_vien_fk", chucVuId, ChucVus)(_.id)

  def phongBangId = column[Long]("phongBangId", O.NotNull)

  def phongBang = foreignKey("phong_bang_nhan_vien_fk", phongBangId, PhongBangs)(_.id)

  def idx = index("nhanvien_index", maNv, unique = true)

  def * = (id.?, maNv, password, firstName, lastName, heSoLuong, chucVuId, phongBangId) <>(NhanVien.tupled, NhanVien.unapply)
}

object NhanViens extends AbstractQuery[NhanVien, NhanViens](new NhanViens(_)) {

  def editForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "maNv" -> text(minLength = 4),
      "password" -> text(minLength = 4),
      "firstName" -> text(minLength = 4),
      "lastName" -> text(minLength = 4),
      "heSoLuong" -> longNumber,
      "chucVuId" -> longNumber,
      "phongBangId" -> longNumber
    )(NhanVien.apply)(NhanVien.unapply)
  )

  implicit val nhanVienJsonFormat = Json.format[NhanVien]

  def findByMaNv(maNv: String)(implicit session: Session) = where(_.maNv === maNv).firstOption()

  def findByPhongBangId(phongBangId: Long)(implicit session: Session) = where(_.phongBangId === phongBangId).list()

  def login(maNv: String, password: String)(implicit session: Session) = {
    val nhanVien = findByMaNv(maNv)
    nhanVien.isDefined && Hash.checkPassword(password, nhanVien.get.password)
  }

  override def beforeSave(entity: NhanVien)(implicit session: Session) = {
    entity.copy(password = Hash.createPassword(entity.password))
  }

  override def beforeUpdate(entity: NhanVien)(implicit session: Session) = {
    val oldUser = findById(entity.id.get)
    oldUser.map(user => {
      if (user.password == entity.password) {
        entity
      } else {
        entity.copy(password = Hash.createPassword(entity.password))
      }
    }).getOrElse(entity)
  }

  def load(pagingDto: PagingDto, currentUser: NhanVien)(implicit session: Session): ExtGirdDto[NhanVienDto] = {
    var query = for (
      nhanVien <- this;
      chucVu <- nhanVien.chucVu;
      phongBang <- nhanVien.phongBang
    ) yield (nhanVien, chucVu, phongBang)

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        val (nhanVien, chucVu, phongBang) = table
        filter.property match {
          case "firstName" => nhanVien.firstName.toLowerCase like filter.asLikeValue
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val (nhanVien, chucVu, phongBang) = table
        sort.property match {
          case "maNv" => orderColumn(sort.direction, nhanVien.maNv)
          case "firstName" => orderColumn(sort.direction, nhanVien.firstName)
          case "lastName" => orderColumn(sort.direction, nhanVien.lastName)
          case "heSoLuong" => orderColumn(sort.direction, nhanVien.heSoLuong)
          case "chucVu.name" => orderColumn(sort.direction, chucVu.name)
          case "phongBang.name" => orderColumn(sort.direction, phongBang.name)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list
      .map(NhanVienDto.apply)

    ExtGirdDto[NhanVienDto](
      total = totalRow,
      data = rows
    )
  }
}