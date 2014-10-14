package dtos.report

import dtos.report.row.ChungTuLuongRow
import models.sunerp.{NhanViens, CongThucLuongs}
import play.api.db.slick._

import scala.collection.mutable.ListBuffer

/**
 * Created by dungvn3000 on 9/11/2014.
 */
class ChungTuLuong(
                    phongBanDto: PhongBanDto,
                    month: Int,
                    year: Int
                    )(implicit session: Session) {

  val monthWorkingDay = 26
  //He truc tiep
  val donGiaTiKey = "ti.dongia"
  val kGianTiepKey = "giantiep.k"
  val kThoiGianKey = "thoigian.k"
  val kPhepKey = "phep.k"
  val kLeKey = "le.k"
  val kAnToanKey = "antoan.k"
  val kTrachNhiemKey = "trachnhiem.k"
  val heSoTrachNhiemKey = "trachnhiem.heso"
  val donGiaPhuCapKhuVucKey = "phucapkhuvuc.dongia"
  val klPhuCapKhuVucKey = "phucapkhuvuc.khoiluong"
  val donGiaPhuCapDocHaiKey = "phucapdochai.dongia"
  val kPhuCapLamDemKey = "phucaplamdem.k"
  val donGianPhuCapBhldKey = "phucapbhld.dongia"
  val thueNgoaiKey = "thuengoai"
  val buLuongKey = "buluong"
  val kOmDauKey = "omdau.k"
  val kThaiSanKey = "thaisan.k"
  val thaiSanThangKey = "thaisan.thang"
  val kTaiNanKey = "tainan.k"
  val giuaCaDonGiaKey = "giuaca.dongia"
  val nuocUongDonGiaKey = "nuocuong.dongia"
  val boiDuongDocHaiDonGiaKey = "boiduongdochai.dongia"
  val phuCapKhacKlKey = "phucapkhac.kl"
  val phuCapKhacDonGiaKey = "phucapkhac.dongia"

  val congThucLuongs = CongThucLuongs.findByMonth(month, year, phongBanDto.id)
  val donGiaTi = congThucLuongs.find(_.key == donGiaTiKey).get.value
  val kGianTiep = congThucLuongs.find(_.key == kGianTiepKey).get.value
  val kThoiGian = congThucLuongs.find(_.key == kThoiGianKey).get.value
  val kPhep = congThucLuongs.find(_.key == kPhepKey).get.value
  val kLe = congThucLuongs.find(_.key == kLeKey).get.value
  val kAnToan = congThucLuongs.find(_.key == kAnToanKey).get.value
  val kTrachNhiem = congThucLuongs.find(_.key == kTrachNhiemKey).get.value
  val heSoTrachNhiem = congThucLuongs.find(_.key == heSoTrachNhiemKey).get.value
  val donGiaPhuCapKhuVuc = congThucLuongs.find(_.key == donGiaPhuCapKhuVucKey).get.value
  val klPhuCapKhuVuc = congThucLuongs.find(_.key == klPhuCapKhuVucKey).get.value
  val donGiaPhuCapDocHai = congThucLuongs.find(_.key == donGiaPhuCapDocHaiKey).get.value
  val kPhuCapLamDem = congThucLuongs.find(_.key == kPhuCapLamDemKey).get.value
  val donGiaPhuCapBhld = congThucLuongs.find(_.key == donGianPhuCapBhldKey).get.value
  val theuNgoaiThanhTien = congThucLuongs.find(_.key == thueNgoaiKey).get.value
  val buLuongThangThanhTien = congThucLuongs.find(_.key == buLuongKey).get.value
  val kOmDau = congThucLuongs.find(_.key == kOmDauKey).get.value
  val kThaiSan = congThucLuongs.find(_.key == kThaiSanKey).get.value
  val thaiSanThang = congThucLuongs.find(_.key == thaiSanThangKey).get.value
  val kTaiNan = congThucLuongs.find(_.key == kTaiNanKey).get.value
  val donGiaGiuaCa = congThucLuongs.find(_.key == giuaCaDonGiaKey).get.value
  val donGiaNuocUong = congThucLuongs.find(_.key == nuocUongDonGiaKey).get.value
  val boiDuongDocHaiDonGia = congThucLuongs.find(_.key == boiDuongDocHaiDonGiaKey).get.value
  val klPhuCapKhac = congThucLuongs.find(_.key == phuCapKhacKlKey).get.value
  val phuCapKhacDonGia = congThucLuongs.find(_.key == phuCapKhacDonGiaKey).get.value

  def double2Double(value: java.lang.Double) = if (value == null) 0d else value.toDouble

  def int2Double(value: java.lang.Integer) = if (value == null) 0d else value.toDouble

  val bangChamCongs = phongBanDto.bangChamCongs
  val klLuongSanPhamTi = bangChamCongs.map(r => double2Double(r.tongGioCong)).sum
  val klLuongGianTiep = bangChamCongs.map(r => double2Double(r.hop)).sum
  val klLuongThoiGian = bangChamCongs.map(r => int2Double(r.hocDH) + double2Double(r.hocNH)).sum
  val klPhepNam = bangChamCongs.map(r => int2Double(r.phep)).sum
  val klNghiLe = bangChamCongs.map(r => int2Double(r.leTet) + int2Double(r.gianTiep)).sum
  val klAnToan = 1
  val klTrachNhiem = 1
  val klPhuCapDocHai = bangChamCongs.map(r => int2Double(r.phuCapDocHai)).sum
  val klPhuCapLamDem = bangChamCongs.map(r => int2Double(r.phuCapLamDem)).sum
  val klPhuCapTrucNhat = bangChamCongs.map(r => int2Double(r.trucBHLD)).sum
  val klOmDau = bangChamCongs.map(r => int2Double(r.omDau) + int2Double(r.conOm)).sum
  val klThaiSan = bangChamCongs.map(r => int2Double(r.thaiSan)).sum
  val klTaiNanLD = bangChamCongs.map(r => int2Double(r.taiNanLaoDong)).sum
  val klGiuaCa = bangChamCongs.map(r => int2Double(r.giuaCa)).sum
  val klNuocUong = bangChamCongs.length

  val rows = new ListBuffer[ChungTuLuongRow]

  lazy val thuocQuyLuongRow = {
    val row = new ChungTuLuongRow()
    row.stt = "I"
    row.danhMuc = "Thuộc quỹ lương"
    row.coThuong = luongSanPhamTiRow.coThuong + luongSanPhamGianTiepRow.coThuong +
      luongSanPhamThoiGianRow.coThuong + phepNamRow.coThuong + nghiLeRow.coThuong +
      anToanVeSinhRow.coThuong + trachNhiemRow.coThuong + phuCapKhuVucRow.coThuong +
      phuCapDocHaiRow.coThuong + phuCapLamDemRow.coThuong + phuCapTrucNhatRow.coThuong +
      thueNgoaiRow.coThuong + buLuongThangRow.coThuong
    row
  }

  lazy val baoHiemXaHoi = {
    val row = new ChungTuLuongRow()
    row.stt = "II"
    row.danhMuc = "Bảo hiểm xã hội"
    row.coThuong = omDauRow.coThuong + thaiSanRow.coThuong + taiNanLaoDongRow.coThuong
    row
  }

  lazy val chiPhiKhacRow = {
    val row = new ChungTuLuongRow()
    row.stt = "III"
    row.danhMuc = "Chi phí khác"
    row.coThuong = giuaCaRow.coThuong + nuocUongRow.coThuong + boiDuongDocHaiRow.coThuong + phuCapKhacRow.coThuong
    row
  }

  lazy val tongCongRow = {
    val row = new ChungTuLuongRow()
    row.danhMuc = "              Cộng (I+II+III)"
    row.coThuong = thuocQuyLuongRow.coThuong + baoHiemXaHoi.coThuong + chiPhiKhacRow.coThuong
    row
  }

  val luongSanPhamTiRow = {
    val row = new ChungTuLuongRow()
    row.stt = "1"
    row.danhMuc = "Lương sản phẩm Ti"
    row.donVi = "Giờ"
    row.khoiLuong = klLuongSanPhamTi
    row.donGia = donGiaTi
    row.coThuong = donGiaTi * klLuongSanPhamTi
    row
  }

  lazy val luongSanPhamT1iRow = {
    val row = new ChungTuLuongRow()
    row.stt = "a"
    row.danhMuc = "Lương T1i"
    row.donVi = "Đồng"
    row.coThuong = luongSanPhamTiRow.coThuong * 0.4
    row
  }

  lazy val luongSanPhamT2iRow = {
    val row = new ChungTuLuongRow()
    row.stt = "b"
    row.danhMuc = "Lương T2i"
    row.donVi = "Đồng"
    row.coThuong = luongSanPhamTiRow.coThuong * 0.6
    row
  }

  val luongSanPhamGianTiepRow = {
    val row = new ChungTuLuongRow()
    row.stt = "2"
    row.danhMuc = "Lương gián tiếp"
    row.donVi = "Công"
    if (klLuongGianTiep > 0) {
      row.khoiLuong = klLuongGianTiep
      row.donGia = bangChamCongs
        .map(r => double2Double(r.hsl) * double2Double(r.hop)).sum / klLuongGianTiep * kGianTiep / monthWorkingDay
      row.coThuong = row.khoiLuong * row.donGia
    }
    row
  }

  val luongSanPhamThoiGianRow = {
    val row = new ChungTuLuongRow()
    row.stt = "3"
    row.danhMuc = "Lương thời gian"
    row.donVi = "Công"
    if (klLuongThoiGian > 0) {
      row.khoiLuong = klLuongThoiGian
      row.donGia = bangChamCongs
        .map(r => double2Double(r.hsl) * (int2Double(r.hocDH) + double2Double(r.hocNH))).sum / klLuongThoiGian * kThoiGian / monthWorkingDay
      row.coThuong = row.khoiLuong * row.donGia
    }
    row
  }

  val phepNamRow = {
    val row = new ChungTuLuongRow()
    row.stt = "4"
    row.danhMuc = "Phép năm"
    row.donVi = "Công"
    if (klPhepNam > 0) {
      row.khoiLuong = klPhepNam
      row.donGia = bangChamCongs
        .map(r => double2Double(r.hsl) * int2Double(r.phep)).sum / klPhepNam * kPhep / monthWorkingDay
      row.coThuong = row.khoiLuong * row.donGia
    }
    row
  }

  val nghiLeRow = {
    val row = new ChungTuLuongRow()
    row.stt = "5"
    row.danhMuc = "Nghỉ lễ, tết, nghỉ hưởng  lương"
    row.donVi = "Công"
    if (klNghiLe > 0) {
      row.khoiLuong = klNghiLe
      row.donGia = bangChamCongs
        .map(r => double2Double(r.hsl) * int2Double(r.leTet)).sum / klNghiLe * kLe / monthWorkingDay
      row.coThuong = row.khoiLuong * row.donGia
    }
    row
  }

  val anToanVeSinhRow = {
    val row = new ChungTuLuongRow()
    row.stt = "6"
    row.danhMuc = "An toàn vệ sinh viên"
    row.donVi = "Đồng"
    if (klAnToan > 0) {
      row.khoiLuong = klAnToan
      row.donGia = 0.1 * kAnToan
      row.coThuong = row.khoiLuong * row.donGia
    }
    row
  }

  val trachNhiemRow = {
    val row = new ChungTuLuongRow()
    row.stt = "7"
    row.danhMuc = "Trách nhiệm, chức vụ"
    row.donVi = "Đồng"
    if (klTrachNhiem > 0) {
      row.khoiLuong = klTrachNhiem
      row.donGia = heSoTrachNhiem * kTrachNhiem
      row.coThuong = heSoTrachNhiem * kTrachNhiem * klTrachNhiem
    }
    row
  }

  val phuCapKhuVucRow = {
    val row = new ChungTuLuongRow()
    row.stt = "8"
    row.danhMuc = "Phụ cấp khu vực"
    row.donVi = "Người"
    if (klPhuCapKhuVuc > 0) {
      row.khoiLuong = klPhuCapKhuVuc
      row.donGia = donGiaPhuCapKhuVuc
      row.coThuong = klPhuCapKhuVuc * donGiaPhuCapKhuVuc
    }
    row
  }

  val phuCapDocHaiRow = {
    val row = new ChungTuLuongRow()
    row.stt = "9"
    row.danhMuc = "Phụ cấp độc hại"
    row.donVi = "Công"
    if (klPhuCapDocHai > 0) {
      row.khoiLuong = klPhuCapDocHai
      row.donGia = donGiaPhuCapDocHai
      row.coThuong = klPhuCapDocHai * donGiaPhuCapDocHai
    }
    row
  }

  val phuCapLamDemRow = {
    val row = new ChungTuLuongRow()
    row.stt = "10"
    row.danhMuc = "Phụ cấp làm đêm"
    row.donVi = "Công"
    if (klPhuCapLamDem > 0) {
      row.khoiLuong = klPhuCapLamDem
      row.donGia = bangChamCongs
        .map(r => double2Double(r.hsl) * int2Double(r.phuCapLamDem)).sum / klPhuCapLamDem * kPhuCapLamDem / monthWorkingDay * 0.12
      row.coThuong = row.khoiLuong * row.donGia
    }
    row
  }

  val phuCapTrucNhatRow = {
    val row = new ChungTuLuongRow()
    row.stt = "11"
    row.danhMuc = "Phụ cấp trực nhật BHLĐ"
    row.donVi = "Công"
    if (klPhuCapTrucNhat > 0) {
      row.khoiLuong = klPhuCapTrucNhat
      row.donGia = donGiaPhuCapBhld
      row.coThuong = donGiaPhuCapBhld * klPhuCapTrucNhat
    }
    row
  }

  val thueNgoaiRow = {
    val row = new ChungTuLuongRow()
    row.stt = "12"
    row.danhMuc = "Thuê ngoài"
    row.donVi = "Đồng"
    row.coThuong = theuNgoaiThanhTien
    row
  }

  val buLuongThangRow = {
    val row = new ChungTuLuongRow()
    row.stt = "13"
    row.danhMuc = "Bù lương tháng"
    row.donVi = "Đồng"
    row.coThuong = buLuongThangThanhTien
    row
  }

  val omDauRow = {
    val row = new ChungTuLuongRow()
    row.stt = "1"
    row.danhMuc = "Ốm đau"
    row.donVi = "Công"
    if (klOmDau > 0) {
      row.khoiLuong = klOmDau
      row.coThuong = bangChamCongs.map {
        r => double2Double(r.hsl) * (int2Double(r.omDau) + int2Double(r.conOm)) * kOmDau / monthWorkingDay * 0.75
      }.sum
    }
    row
  }

  val thaiSanRow = {
    val row = new ChungTuLuongRow()
    row.stt = "2"
    row.danhMuc = "Thai sản"
    row.donVi = "Công"
    if (klThaiSan > 0) {
      row.khoiLuong = klThaiSan
      row.coThuong = bangChamCongs.filter(_.thaiSan != null).map {
        r => (double2Double(r.hsl) * kThaiSan * thaiSanThang) + (2 * kThaiSan)
      }.sum
    }
    row
  }

  val taiNanLaoDongRow = {
    val row = new ChungTuLuongRow()
    row.stt = "3"
    row.danhMuc = "Tai nạn lao động, bệnh NN"
    row.donVi = "Công"
    if (klTaiNanLD > 0) {
      row.khoiLuong = klTaiNanLD
      row.coThuong = bangChamCongs.filter(_.taiNanLaoDong != null).map {
        r => double2Double(r.hsl) * kTaiNan * klTaiNanLD
      }.sum
    }
    row
  }

  val giuaCaRow = {
    val row = new ChungTuLuongRow()
    row.stt = "1"
    row.danhMuc = "Giữa ca"
    row.donVi = "Công"
    if (klGiuaCa > 0) {
      row.khoiLuong = klGiuaCa
      row.donGia = donGiaGiuaCa
      row.coThuong = klGiuaCa * donGiaGiuaCa
    }
    row
  }

  val nuocUongRow = {
    val row = new ChungTuLuongRow()
    row.stt = "2"
    row.danhMuc = "Nước uống"
    row.donVi = "Người"
    if (klNuocUong > 0) {
      row.khoiLuong = klNuocUong
      row.donGia = donGiaNuocUong
      row.coThuong = klNuocUong * donGiaNuocUong
    }
    row
  }

  val boiDuongDocHaiRow = {
    val row = new ChungTuLuongRow()
    row.stt = "3"
    row.danhMuc = "Bồi dưỡng độc hại"
    row.donVi = "Công"
    if (klPhuCapDocHai > 0) {
      row.khoiLuong = klPhuCapDocHai
      row.donGia = boiDuongDocHaiDonGia
      row.coThuong = klPhuCapDocHai * boiDuongDocHaiDonGia
    }
    row
  }


  val phuCapKhacRow = {
    val row = new ChungTuLuongRow()
    row.stt = "4"
    row.danhMuc = "Phụ cấp khác"
    row.donVi = "Người"
    if (klPhuCapKhac > 0) {
      row.khoiLuong = klPhuCapKhac
      row.donGia = phuCapKhacDonGia
      row.coThuong = klPhuCapKhac * phuCapKhacDonGia
    }
    row
  }

  //I Thuoc quy luong
  rows += thuocQuyLuongRow
  rows += luongSanPhamTiRow
  rows += luongSanPhamT1iRow
  rows += luongSanPhamT2iRow
  rows += luongSanPhamGianTiepRow
  rows += luongSanPhamThoiGianRow
  rows += phepNamRow
  rows += nghiLeRow
  rows += anToanVeSinhRow
  rows += trachNhiemRow
  rows += phuCapKhuVucRow
  rows += phuCapDocHaiRow
  rows += phuCapLamDemRow
  rows += phuCapTrucNhatRow
  rows += thueNgoaiRow
  rows += buLuongThangRow

  //II Bao hiem xa hoi
  rows += baoHiemXaHoi
  rows += omDauRow
  rows += thaiSanRow
  rows += taiNanLaoDongRow

  //III Chi phi khac
  rows += chiPhiKhacRow
  rows += giuaCaRow
  rows += nuocUongRow
  rows += boiDuongDocHaiRow
  rows += phuCapKhacRow

  //Tong Cong
  rows += tongCongRow
}
