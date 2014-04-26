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
        val daily = khoiLuongs
          .filter(khoiLuong => khoiLuong.nhanVien.id == nhanVien.id && khoiLuong.ngayPhanCong.getDayOfMonth == i)
        val khoiLuong = daily.foldLeft(0d)((kl, dto) => dto.khoiLuong + kl)
        if (khoiLuong > 0) {
          row.getKhoiLuongCongViec.put(i.toString, khoiLuongCode(khoiLuong, daily))
        }
      }

      row.congSanPham = sumByNv(nhanVien.id).asJava

      row
    }).asJavaCollection
  }


  /**
   *
   * 1 - Ngày làm bình thường chấm điểm đúng theo quy định.VD: Mười điểm thì ghi là 10 vào cột điểm
      2 - Ngày làm đêm thêm dấu cộng (+) sau điểm số: VD 10+
      3 - Ngày làm có trực bảo hộ lao động thêm dấu trừ (-) sau điểm số. VD: 10-
      4 - Ngày làm có độc hại thêm dấu nhân (x) sau điểm số. VD: 10x
      5 - Nếu việc làm đêm, trực bảo hộ lao động và độc hại có thể cùng một lúc cho một người trực
       thì ta cho điểm theo thứ tự. VD: 10+-x
      6 - Nếu là Lễ: L, tết: T, thai sản: TS, Ốm: Ô, con ốm: CO, tai nạn LĐ: TN
      7 - Nếu họp thì chấm H;
      8 - Đối với 1/2 ngày họp, 1/2 ngày làm thì chấm công họp trên điểm: VD: H/10; H/0
      9 - Nếu 1/2 ngày họp, 1/2 ngày làm có trực đêm, trực bảo hộ lao động, làm độc hại thì chấm như sau:
        H/10+ 	: 1/2 ngày họp, 1/2 ngày làm có trực đêm
        H/10-	: 1/2 ngày họp, 1/2 ngày làm có trực bảo hộ lao động
        H/10x	: 1/2 ngày họp, 1/2 ngày làm có độc hại lao động
        H/10+-x	: 1/2 ngày họp, 1/2 ngày làm có trực đêm kiêm trực BHLĐ kiêm độc hại

      10 - Nếu đi học dài hạn không lương hoặc chỉ được trả lương cứng thì chấm : HO, học có được tính giữa ca: HT
      VD:	Học tại chức được công ty trả lương cứng hoặc chỉ bố trí thời gian đi học thì chấm là :      HO  {chữ cái H và chữ cái O không phải số không (0)}
        Học đột xuất dưới sự điều động của công ty như học quân sự, học an toàn vệ sinh viên …chấm là:    HT
      11- Nghỉ việc riêng có lương: VR, không lương thì chấm điểm không(0).
      12 - Nghỉ chủ nhật thì chấm CN

   *
   * @param dailyKhoiLuong
   * @param khoiLuongs
   */
  private def khoiLuongCode(dailyKhoiLuong: Double, khoiLuongs: List[KhoiLuongDto]) = {
    var code = formater.format(dailyKhoiLuong)
    for (khoiLuong <- khoiLuongs) {
      if (khoiLuong.lamDem) {
        code = code + "+"
      }

      if (khoiLuong.baoHoLaoDong) {
        code = code + "-"
      }

      if (khoiLuong.hop) {
        code = "H/" + code
      }
      if (khoiLuong.le) {
        code = "L"
      }
      if (khoiLuong.tet) {
        code = "T"
      }
      if (khoiLuong.thaiSan) {
        code = "TS"
      }
      if (khoiLuong.dauOm) {
        code = "Ô"
      }
      if (khoiLuong.conOm) {
        code = "CO"
      }
      if (khoiLuong.taiNanLd) {
        code = "TN"
      }
      if (khoiLuong.hocDaiHan) {
        code = "HO"
      }
      if (khoiLuong.hocDotXuat) {
        code = "HT/" + code
      }
      if (khoiLuong.viecRieng) {
        code = "VR/"
      }
      if (khoiLuong.chuNhat) {
        code = "CN"
      }
    }
    code
  }
}
