package dtos.report

import dtos.report.row.{BangChamCongRow, PhongBanKhoiLuongRow}
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

  implicit class ReportDouble(d: Double) {
    def asJava: java.lang.Double = if (d > 0) d else null
  }

  implicit class ReportInt(i: Int) {
    def asJava: java.lang.Integer = if (i > 0) i else null
  }

  def bangChamCongs = {
    var stt = 0
    nhanViens.map(nhanVien => {
      stt += 1
      val row = new BangChamCongRow
      row.stt = stt
      row.tenNV = nhanVien.name
      row.hsl = nhanVien.heSoLuong.asJava

      val hop = sumHop(nhanVien.id)
      val hocNH = sumHocNH(nhanVien.id)
      val hocDH = sumHocDH(nhanVien.id)
      val phep = sumPhep(nhanVien.id)
      val leTet = sumLeTet(nhanVien.id)
      val tongCacKhoanCong = hop + hocDH + hocDH + phep + leTet

      row.hop = hop.asJava
      row.hocNH = hocNH.asJava
      row.hocDH = hocDH.asJava
      row.phep = phep.asJava
      row.leTet = leTet.asJava
      row.tongCacKhoanCong = tongCacKhoanCong.asJava

      val omDau = sumOmDau(nhanVien.id)
      val thaiSan = sumThaiSan(nhanVien.id)
      val conOm = sumConOm(nhanVien.id)
      val taiNanLaoDong = sumTNLD(nhanVien.id)

      val tongCacKhoanTru = omDau + conOm + thaiSan + taiNanLaoDong

      row.omDau = omDau.asJava
      row.thaiSan = thaiSan.asJava
      row.conOm = conOm.asJava
      row.taiNanLaoDong = taiNanLaoDong.asJava
      row.tongCacKhoanTru = tongCacKhoanTru.asJava

      row.phuCapLamDem = sumLamDem(nhanVien.id).asJava
      row.trucBHLD = sumBHLD(nhanVien.id).asJava
      row.phuCapDocHai = sumPhuCapDH(nhanVien.id).asJava

      for (i <- 1 to 31) {
        val daily = sumByNvAndDay(nhanVien.id, i)
        if (daily > 0) row.getKhoiLuongCongViec.put(i.toString, formater.format(daily))
      }

      row.congSanPham = sumByNv(nhanVien.id).asJava

      row
    }).asJavaCollection
  }
}
