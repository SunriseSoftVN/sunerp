package dtos.report

import scala.collection.JavaConverters._
import java.text.DecimalFormat

import scalaz._
import Scalaz._

/**
 * The Class KhoiLuongContainer.
 *
 * @author Nguyen Duc Dung
 * @since 4/18/14 9:56 AM
 *
 */
abstract class KhoiLuongContainer[R] {

  val id: Long
  val name: String
  val children: List[_ <: KhoiLuongContainer[_]] = List.empty
  val tasks: List[TaskDto]
  protected val _khoiLuongs: List[KhoiLuongDto] = List.empty

  lazy val klFormater = new DecimalFormat("0.##")
  lazy val gioFormater = new DecimalFormat("0")

  lazy final val khoiLuongs: List[KhoiLuongDto] = if (children.isEmpty) _khoiLuongs else children.flatMap(_.khoiLuongs)
  lazy val nhanViens: List[NhanVienDto] = khoiLuongs.map(_.nhanVien).distinct

  /**
   * do sum "khoiluong" of the task.
   * @param taskId
   */
  def sumKL(taskId: Long) = khoiLuongs
    .filter(kl => kl.task.isDefined && kl.task.get.id == taskId)
    .foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)

  def sumKLByDay(taskId: Long, dayOfMonth: Int) = khoiLuongs
    .filter(khoiLuong => khoiLuong.task.isDefined && khoiLuong.task.get.id == taskId && khoiLuong.ngayPhanCong.getDayOfMonth == dayOfMonth)
    .foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)

  def sumByNv(nhanVienId: Long) = khoiLuongs
    .filter(_.nhanVien.id == nhanVienId)
    .foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)

  def sumGioByNv(nhanVienId: Long) = khoiLuongs
    .filter(_.nhanVien.id == nhanVienId)
    .foldLeft(0d)((gio, dto) => dto.gio + gio)

  def sumByNvAndDay(nhanVienId: Long, dayOfMonth: Int) = khoiLuongs
    .filter(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.ngayPhanCong.getDayOfMonth == dayOfMonth)
    .foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)

  def sumHop(nhanVienId: Long): Double = {
    var hop = 0d
    for (khoiLuong <- khoiLuongs if khoiLuong.nhanVien.id == nhanVienId) {
      if (khoiLuong.hop) {
        if (khoiLuong.khoiLuong > 0) {
          hop += 0.5
        } else {
          hop += 1
        }
      }
    }
    hop
  }

  def sumHocNH(nhanVienId: Long): Double = {
    var hocNH = 0d
    for (khoiLuong <- khoiLuongs if khoiLuong.nhanVien.id == nhanVienId) {
      if (khoiLuong.hocDotXuat) {
        if (khoiLuong.khoiLuong > 0) {
          hocNH += 0.5
        } else {
          hocNH += 1
        }
      }
    }
    hocNH
  }

  def sumHocDH(nhanVienId: Long): Int = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.hocDaiHan)

  def sumViecRieng(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.viecRieng)

  def sumLeTet(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && (khoiLuong.le || khoiLuong.tet))

  def sumOmDau(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.dauOm)

  def sumThaiSan(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.thaiSan)

  def sumConOm(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.conOm)

  def sumTNLD(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.taiNanLd)

  def sumLamDem(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.lamDem)

  def sumBHLD(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.baoHoLaoDong)

  def sumPhep(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.phep)

  def sumPhuCapDH(nhanVienId: Long) = khoiLuongs
    .count(khoiLuong => khoiLuong.nhanVien.id == nhanVienId && khoiLuong.docHai)

  def sumGio(taskId: Long): Double = {
    def _sum(taskId: Long) = khoiLuongs
      .filter(kl => kl.task.isDefined && kl.task.get.id == taskId)
      .foldLeft(0d)((gio, dto) => dto.gio + gio)

    val task = tasks.find(_.id == taskId).getOrElse(throw new Exception("Something go wrong!"))
    if (task.children.isEmpty) {
      _sum(taskId)
    } else {
      task.children.foldLeft(0d)((gio, child) => gio + sumGio(child.id))
    }
  }

  def sumKLByChildId(taskId: Long, childId: Long): Double = children
    .filter(_.id == childId)
    .foldLeft(0d)((kl, child) => child.sumKL(taskId) + kl)

  def sumGioByChildId(taskId: Long, childId: Long): Double = children
    .filter(_.id == childId)
    .foldLeft(0d)((gio, child) => child.sumGio(taskId) + gio)

  /**
   * Tranfrom data to report row.
   * @return
   */
  def khoiLuongRows: List[R]

  def javaKLRows() = khoiLuongRows.asJavaCollection
}
