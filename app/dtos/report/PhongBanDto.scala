package dtos.report

import dtos.report.row.PhongBanKhoiLuongRow
import dtos.report.qlkh.TaskReportBean
import scala.collection.JavaConverters._

/**
 * The Class PhongBanDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:54 PM
 *
 */
case class PhongBanDto(
                        id: Long, name: String,
                        override val _khoiLuongs: List[KhoiLuongDto] = Nil,
                        taskExternal: List[TaskReportBean]
                        ) extends KhoiLuongContainer[PhongBanKhoiLuongRow] {

  /**
   * Tranfrom data to report row.
   * @return
   */
  override def khoiLuongRows = tasks.map(PhongBanKhoiLuongRow(_, sumKL, sumGio, sumKLByDay, taskExternal)).sortBy(_.taskCode)

  def bangChamCongs = nhanViens.map(nhanVien => {
     val row = new BangChamCongRow
    row.hop = sumHop(nhanVien.id)
    row.hocNH = sumHocNH(nhanVien.id)
    row.hocDH = sumHocDH(nhanVien.id)
    row.phep = sumPhep(nhanVien.id)
    row.leTet = sumLeTet(nhanVien.id)
    row.omDau = sumOmDau(nhanVien.id)
    row.conOm = sumConOm(nhanVien.id)
    row.tnld = sumTNLD(nhanVien.id)
    row.phuCapLamDem  = sumLamDem(nhanVien.id)
    row.trucBHLD  = sumBHLD(nhanVien.id)
    row.phuCapDocHai  = sumPhuCapDH(nhanVien.id)
    row
  }).asJavaCollection
}
