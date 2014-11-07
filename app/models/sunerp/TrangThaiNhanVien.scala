package models.sunerp

import models.core.{AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._

import scala.slick.lifted.Tag

/**
 * Created by dungvn3000 on 07/11/2014.
 */
case class TrangThaiNhanVien(
                              id: Option[Long] = None,
                              nhanVienId: Long,
                              nghiViec: Boolean,
                              month: Int,
                              year: Int
                              ) extends WithId[Long]

class TrangThaiNhanViens(tag: Tag) extends AbstractTable[TrangThaiNhanVien](tag, "trangthainhanvien") {

  def nhanVienId = column[Long]("nhanVienId", O.NotNull)

  def nghiViec = column[Boolean]("nghiViec", O.NotNull)

  def month = column[Int]("month", O.NotNull)

  def year = column[Int]("year", O.NotNull)

  def nhanVien = foreignKey("trang_thai_nhan_vien_fk", nhanVienId, NhanViens)(_.id)

  override def * = (id.?, nhanVienId, nghiViec, month, year) <>(TrangThaiNhanVien.tupled, TrangThaiNhanVien.unapply)
}

