package dtos.report

import java.lang

import dtos.report.row.ChungTuLuongRow
import models.sunerp.CongThucLuongs
import play.api.db.slick._

import scala.collection.mutable.ListBuffer

/**
 * Created by dungvn3000 on 9/11/2014.
 */
class ChungTuLuong(
                    klLuongSanPhamTi: Double,
                    klLuongGianTiep: Double,
                    klLuongThoiGian: Double,
                    klPhepNam: Double,
                    klNghiLe: Double,
                    klAnToan: Double,
                    klTrachNhiem: Double,
                    klPhuCapDocHai: Double,
                    klPhuCapLamDem: Double,
                    klPhuCapTrucNhat: Double,
                    klOmDau: Double,
                    klThaiSan: Double,
                    klTaiNanLD: Double,
                    klGiuaCa: Double,
                    klNuocUong: Double,
                    month: Int
                    )(implicit session: Session) {

  val donGiaTiKey = "ti.dongia"
  val kGianTiepKey = "giantiep.k"
  val kThoiGianKey = "thoigian.k"
  val kPhepKey = "phep.k"
  val kLeKey = "le.k"
  val kAnToanKey = "antoan.k"
  val kTrachNhiemKey = "trachnhiem.k"
  val heSoTrachNhiemKey = "trachnhiem.heso"
  val kPhuCapKhuVucKey = "phucapkhuvuc.k"
  val klPhuCapKhuVucKey = "phucapkhuvuc.khoiluong"
  val kPhuCapDocHaiKey = "phucapdochai.k"
  val kPhuCapLamDemKey = "phucaplamdem.k"
  val kPhuCapBhldKey = "phucapbhld.dongia"
  val thueNgoaiKey = "thuengoai"
  val buLuongKey = "buluong"
  val kOmDauKey = "omdau.k"
  val kThaiSanKey = "thaisan.k"
  val thaiSanThangKey = "thaisan.thang"
  val kTaiNanKey = "tainan.k"
  val giuaCaDonGiaKey = "giuaca.dongia"
  val nuocUongDonGiaKey = "nuocuong.dongia"

  val congThucLuongs = CongThucLuongs.findByMonth(month)
  val donGiaTi = congThucLuongs.find(_.key == donGiaTiKey).get.value
  val kGianTiep = congThucLuongs.find(_.key == kGianTiepKey).get.value
  val kThoiGian = congThucLuongs.find(_.key == kThoiGianKey).get.value
  val kPhep = congThucLuongs.find(_.key == kPhepKey).get.value
  val kLe = congThucLuongs.find(_.key == kLeKey).get.value
  val kAnToan = congThucLuongs.find(_.key == kAnToanKey).get.value
  val kTrachNhiem = congThucLuongs.find(_.key == kTrachNhiemKey).get.value
  val heSoTrachNhiem = congThucLuongs.find(_.key == heSoTrachNhiemKey).get.value
  val kPhuCapKhuVuc = congThucLuongs.find(_.key == kPhuCapKhuVucKey).get.value
  val klPhuCapKhuVuc = congThucLuongs.find(_.key == klPhuCapKhuVucKey).get.value
  val kDocHai = congThucLuongs.find(_.key == kPhuCapDocHaiKey).get.value
  val kPhuCapLamDem = congThucLuongs.find(_.key == kPhuCapLamDemKey).get.value
  val kPhuCapBhld = congThucLuongs.find(_.key == kPhuCapBhldKey).get.value
  val theuNgoaiThanhTien = congThucLuongs.find(_.key == thueNgoaiKey).get.value
  val buLuongThangThanhTien = congThucLuongs.find(_.key == buLuongKey).get.value
  val kOmDau = congThucLuongs.find(_.key == kOmDauKey).get.value
  val kThaiSan = congThucLuongs.find(_.key == kThaiSanKey).get.value
  val thaiSanThang = congThucLuongs.find(_.key == thaiSanThangKey).get.value
  val kTaiNan = congThucLuongs.find(_.key == kTaiNanKey).get.value
  val giuaCaDonGia = congThucLuongs.find(_.key == giuaCaDonGiaKey).get.value
  val nuocUongDonGia = congThucLuongs.find(_.key == nuocUongDonGiaKey).get.value

  val rows = new ListBuffer[ChungTuLuongRow]

  lazy val thuocQuyLuongRow = {
    val row = new ChungTuLuongRow()
    row.stt = "I"
    row.danhMuc = "Thuộc quỹ lương"
    row.coThuong = luongSanPhamTiRow.coThuong + luongSanPhamGianTiepRow.coThuong
    + luongSanPhamThoiGianRow.coThuong + phepNamRow.coThuong
    + nghiLeRow.coThuong + anToanVeSinhRow.coThuong
    + trachNhiemRow.coThuong + phuCapKhuVucRow.coThuong + phuCapDocHaiRow.coThuong
    + phuCapLamDemRow.coThuong + phuCapTrucNhatRow.coThuong
    + thueNgoaiRow.coThuong + buLuongThangRow.coThuong
    row
  }

  lazy val baoHiemXaHoi = {
    val row = new ChungTuLuongRow()
    row.stt = "II"
    row.danhMuc = "Bảo hiểm xã hội"
    row
  }

  lazy val chiPhiKhacRow = {
    val row = new ChungTuLuongRow()
    row.stt = "III"
    row.danhMuc = "Chi phí khác"
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
    row.coThuong = thuocQuyLuongRow.coThuong * 0.4
    row
  }

  lazy val luongSanPhamT2iRow = {
    val row = new ChungTuLuongRow()
    row.stt = "b"
    row.danhMuc = "Lương T2i"
    row.donVi = "Đồng"
    row.coThuong = thuocQuyLuongRow.coThuong * 0.6
    row
  }

  val luongSanPhamGianTiepRow = {
    val row = new ChungTuLuongRow()
    row.stt = "2"
    row.danhMuc = "Lương gián tiếp"
    row.donVi = "Công"
    row.khoiLuong = klLuongGianTiep
    row
  }

  val luongSanPhamThoiGianRow = {
    val row = new ChungTuLuongRow()
    row.stt = "3"
    row.danhMuc = "Lương thời gian"
    row.donVi = "Công"
    row.khoiLuong = klLuongThoiGian
    row
  }

  val phepNamRow = {
    val row = new ChungTuLuongRow()
    row.stt = "4"
    row.danhMuc = "Phép năm"
    row.donVi = "Công"
    row.khoiLuong = klPhepNam
    row
  }

  val nghiLeRow = {
    val row = new ChungTuLuongRow()
    row.stt = "5"
    row.danhMuc = "Nghỉ lễ, tết, nghỉ hưởng  lương"
    row.donVi = "Công"
    row.khoiLuong = klNghiLe
    row
  }

  val anToanVeSinhRow = {
    val row = new ChungTuLuongRow()
    row.stt = "6"
    row.danhMuc = "An toàn vệ sinh viên"
    row.donVi = "Đồng"
    row.khoiLuong = klAnToan
    row
  }

  val trachNhiemRow = {
    val row = new ChungTuLuongRow()
    row.stt = "7"
    row.danhMuc = "Trách nhiệm, chức vụ"
    row.donVi = "Đồng"
    row.khoiLuong = klTrachNhiem
    row.donGia = heSoTrachNhiem * kTrachNhiem * klTrachNhiem
    row
  }

  val phuCapKhuVucRow = {
    val row = new ChungTuLuongRow()
    row.stt = "8"
    row.danhMuc = "Phụ cấp khu vực"
    row.donVi = "Người"
    row.khoiLuong = klPhuCapKhuVuc
    row
  }

  val phuCapDocHaiRow = {
    val row = new ChungTuLuongRow()
    row.stt = "9"
    row.danhMuc = "Phụ cấp độc hại"
    row.donVi = "Công"
    row.khoiLuong = klPhuCapDocHai
    row
  }

  val phuCapLamDemRow = {
    val row = new ChungTuLuongRow()
    row.stt = "10"
    row.danhMuc = "Phụ cấp làm đêm"
    row.donVi = "Công"
    row.khoiLuong = klPhuCapLamDem
    row
  }

  val phuCapTrucNhatRow = {
    val row = new ChungTuLuongRow()
    row.stt = "11"
    row.danhMuc = "Phụ cấp trực nhật BHLĐ"
    row.donVi = "Công"
    row.khoiLuong = klPhuCapTrucNhat
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
    row.stt = "12"
    row.danhMuc = "Thuê ngoài"
    row.donVi = "Đồng"
    row.coThuong = buLuongThangThanhTien
    row
  }

  val omDauRow = {
    val row = new ChungTuLuongRow()
    row.stt = "1"
    row.danhMuc = "Ốm đau"
    row.donVi = "Công"
    row.khoiLuong = klOmDau
    row
  }

  val thaiSanRow = {
    val row = new ChungTuLuongRow()
    row.stt = "2"
    row.danhMuc = "Thai sản"
    row.donVi = "Công"
    row.khoiLuong = klThaiSan
    row
  }

  val taiNanLaoDongRow = {
    val row = new ChungTuLuongRow()
    row.stt = "3"
    row.danhMuc = "Tai nạn lao động, bệnh NN"
    row.donVi = "Công"
    row.khoiLuong = klTaiNanLD
    row
  }

  val giuaCaRow  = {
    val row = new ChungTuLuongRow()
    row.stt = "1"
    row.danhMuc = "Giữa ca"
    row.donVi = "Công"
    row.khoiLuong = klGiuaCa
    row
  }

  val nuocUongRow = {
    val row = new ChungTuLuongRow()
    row.stt = "2"
    row.danhMuc = "Nước uống"
    row.donVi = "Người"
    row.khoiLuong = klNuocUong
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
  rows +=buLuongThangRow

  //II Bao hiem xa hoi
  rows += baoHiemXaHoi
  rows +=omDauRow
  rows +=thaiSanRow
  rows +=taiNanLaoDongRow

  //III Chi phi khac
  rows += chiPhiKhacRow
  rows += giuaCaRow
  rows += nuocUongRow
}
