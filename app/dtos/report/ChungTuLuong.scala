package dtos.report

import dtos.report.row.ChungTuLuongRow

import scala.collection.mutable.ListBuffer

/**
 * Created by dungvn3000 on 9/11/2014.
 */
class ChungTuLuong(
                    klLuongSanPhamTi: Double,
                    klLuongSanPhamT1i: Double,
                    klLuongSanPhamT2i: Double,
                    klLuongGianTiep: Double,
                    klLuongThoiGian: Double,
                    klPhepNam: Double,
                    klNghiLe: Double,
                    klAnToan: Double,
                    klTrachNhiem: Double,
                    klPhuCapKhuVuc: Double,
                    klPhuCapDocHai: Double,
                    klPhuCapLamDem: Double,
                    klPhuCapTrucNhat: Double,
                    klTheuNgoai: Double,
                    klBuLuongThang: Double,
                    klOmDau: Double,
                    klThaiSan: Double,
                    klTaiNanLD: Double,
                    klGiuaCa: Double,
                    klNuocUong: Double
                    ) {


  val rows = new ListBuffer[ChungTuLuongRow]

  val thuocQuyLuong = new ChungTuLuongRow()
  thuocQuyLuong.danhMuc = "Thuộc quỹ lương"
  rows += thuocQuyLuong

  luongSanPhamTi()
  luongSanPhamT1i()
  luongSanPhamT2i()
  luongSanPhamGianTiep()
  luongSanPhamThoiGian()
  phepNam()
  nghiLe()
  anToanVeSinh()
  trachNhiem()
  phuCapKhuVuc()
  phuCapDocHai()
  phuCapLamDem()
  phuCapTrucNhat()
  thueNgoai()
  buLuongThang()

  val baoHiemXaHoi = new ChungTuLuongRow()
  baoHiemXaHoi.danhMuc = "Bảo hiểm xã hội"
  rows += baoHiemXaHoi

  omDau()
  thaiSan()
  taiNanLaoDong()

  val chiPhiKhac = new ChungTuLuongRow()
  chiPhiKhac.danhMuc = "Chi phí khác"
  rows += chiPhiKhac

  giuaCa()
  nuocUong()

  def luongSanPhamTi(): Unit = {

  }

  def luongSanPhamT1i(): Unit = {

  }

  def luongSanPhamT2i(): Unit = {

  }

  def luongSanPhamGianTiep(): Unit = {

  }

  def luongSanPhamThoiGian(): Unit = {

  }

  def phepNam(): Unit = {

  }

  def nghiLe(): Unit = {

  }

  def anToanVeSinh(): Unit = {

  }

  def trachNhiem(): Unit = {

  }

  def phuCapKhuVuc(): Unit = {

  }

  def phuCapDocHai(): Unit = {

  }

  def phuCapLamDem(): Unit = {

  }

  def phuCapTrucNhat(): Unit = {

  }

  def thueNgoai(): Unit = {

  }

  def buLuongThang(): Unit = {

  }

  def omDau(): Unit = {

  }

  def thaiSan(): Unit = {

  }

  def taiNanLaoDong(): Unit = {

  }

  def giuaCa(): Unit = {

  }

  def nuocUong(): Unit = {

  }
}
