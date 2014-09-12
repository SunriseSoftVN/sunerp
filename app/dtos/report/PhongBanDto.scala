package dtos.report

import dtos.report.row.{BangChamCongRow, PhongBanKhoiLuongRow}
import dtos.report.qlkh.TaskReportBean
import scala.collection.JavaConverters._
import org.joda.time.LocalDate
import utils.DateTimeUtils
import play.api.db.slick._
import models.sunerp.{DiemHeSos, XepLoais}

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
                        tasks: List[TaskDto],
                        taskExternal: List[TaskReportBean],
                        month: Int,
                        year: Int
                        ) extends KhoiLuongContainer[PhongBanKhoiLuongRow] {

  /**
   * Tranfrom data to report row.
   * @return
   */
  lazy val khoiLuongRows = tasks
    .map(PhongBanKhoiLuongRow(_, sumKL, sumGio, sumKLByDay, taskExternal))
    .filter(kl => (kl.totalKhoiLuong != null && kl.totalKhoiLuong > 0)
    || (kl.totalGio != null && kl.totalGio > 0)
    || (kl.quyGio != null && kl.quyGio > 0))
    .filterNot(_.task.hidden)

  def tyLeHoanThanhCongViec = khoiLuongRows.headOption.fold(0d)(kl => kl.totalGio / kl.quyGio * 100)

  implicit class ReportDouble(d: Double) {
    def asJava: java.lang.Double = if (d > 0) d else null
  }

  implicit class ReportInt(i: Int) {
    def asJava: java.lang.Integer = if (i > 0) i else null
  }

  def bangChamCongs(implicit session: Session) = {
    var stt = 0
    nhanViens.map(nhanVien => {
      stt += 1
      val row = new BangChamCongRow
      row.stt = stt
      row.tenNV = nhanVien.name
      row.nvId = nhanVien.id
      row.hsl = nhanVien.heSoLuong.asJava
      row.xepLoai = XepLoais.findByNhanVienId(nhanVien.id, month, year).fold("")(_.xepLoai)
      row.diemHeSo = DiemHeSos.findByNhanVienId(nhanVien.id, year).fold(Double.NaN)(_.heSo)

      val hop = sumHop(nhanVien.id)
      val hocNH = sumHocNH(nhanVien.id)
      val hocDH = sumHocDH(nhanVien.id)
      val phep = sumPhep(nhanVien.id)
      val leTet = sumLeTet(nhanVien.id)
      val gianTiep = sumViecRieng(nhanVien.id)
      val tongGioCong = sumGioByNv(nhanVien.id)
      val congSanPham = tongGioCong / 8
      val tongCacKhoanCong = congSanPham + hop + hocNH + hocDH + gianTiep + phep + leTet

      row.hop = hop.asJava
      row.hocNH = hocNH.asJava
      row.hocDH = hocDH.asJava
      row.gianTiep = gianTiep.asJava
      row.phep = phep.asJava
      row.leTet = leTet.asJava
      row.tongCacKhoanCong = tongCacKhoanCong.asJava
      row.congSanPham = congSanPham
      row.tongGioCong = tongGioCong

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

      val now = LocalDate.now().withMonthOfYear(month).withYear(year)
      val soNgayTrongThang = now.dayOfMonth().withMaximumValue().getDayOfMonth
      val weekend = DateTimeUtils.countWeekendDays(now.getYear, now.getMonthOfYear)
      val workingDay = now.dayOfMonth().withMaximumValue().getDayOfMonth - weekend
      var giuaCa = workingDay

      for (i <- 1 to soNgayTrongThang) {
        val klCongViecTrongNgay = khoiLuongs
          .filter(khoiLuong => khoiLuong.nhanVien.id == nhanVien.id && khoiLuong.ngayPhanCong.getDayOfMonth == i)
        val gio = klCongViecTrongNgay.foldLeft(0d)((kl, dto) => dto.gio + kl)
        row.getGioCongViec.put(i.toString, khoiLuongCode(gio, klCongViecTrongNgay))

        val duocTinhCongGiuaCa = klCongViecTrongNgay.exists(khoiLuong => khoiLuong.hop || khoiLuong.hocDotXuat)
        val chuNhat = klCongViecTrongNgay.exists(_.chuNhat)
        val khongDuocTinhCongGiuaCa = klCongViecTrongNgay.exists {
          khoiLuong => khoiLuong.hocDaiHan || khoiLuong.phep || khoiLuong.le ||
            khoiLuong.dauOm || khoiLuong.thaiSan || khoiLuong.conOm || khoiLuong.taiNanLd
        }

        if (khongDuocTinhCongGiuaCa || (gio == 0 && !duocTinhCongGiuaCa && !chuNhat)) {
          giuaCa -= 1
        }
      }
      if (giuaCa > 0) {
        row.giuaCa = giuaCa
      }

      row
    })
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
    var code = gioFormater.format(dailyKhoiLuong)
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
      if (khoiLuong.phep) {
        code = "F"
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
        code = "VR"
      }
      if (khoiLuong.chuNhat) {
        code = "CN"
      }
    }
    code
  }
}
