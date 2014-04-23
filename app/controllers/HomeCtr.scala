package controllers

import play.api.mvc._
import controllers.element.{AuthConfigImpl, TransactionElement, MainTemplate}
import jp.t2v.lab.play2.auth.AuthenticationElement
import dtos.MenuItemDto
import play.api.libs.json.Json
import models.sunerp.NhanViens
import com.escalatesoft.subcut.inject.BindingModule

object DomainKey {
  val soPhanCong = "sophancong"
  val company = "company"
  val donVi = "donvi"
  val phongBan = "phongban"
  val nhanVien = "nhanvien"
  val chucVu = "chucvu"
  val quyenHanh = "quyenhanh"
  val soLuong = "soluong"
  val thCongViecHangNgay = "thcongviechangngay"
  val bcThucHienKhoiLuong = "bcThucHienKhoiLuong"
  val thThucHienKhoiLuong = "ththuchienkhoiluong"
  val inSoPhanCong = "insophancong"
  val bangChamCong = "bangchamcong"
  val task = "task"

  val list = List(
    soPhanCong,
    company,
    donVi,
    phongBan,
    nhanVien,
    chucVu,
    quyenHanh,
    soLuong,
    thCongViecHangNgay,
    bcThucHienKhoiLuong,
    thThucHienKhoiLuong,
    inSoPhanCong,
    bangChamCong,
    task
  )
}

class HomeCtr(implicit val bindingModule: BindingModule) extends Controller with AuthenticationElement with AuthConfigImpl with MainTemplate with TransactionElement {

  import DomainKey._

  val quanLyCongViec = MenuItemDto(
    text = "Quản lý công việc",
    expanded = true,
    children = List(
      MenuItemDto(
        id = soPhanCong,
        view = "sunerp.view.sophancong.SoPhanCongList",
        text = "Sổ phân công",
        leaf = true
      )
    )
  )

  val quanLyCongty = MenuItemDto(
    text = "Quản lý công ty",
    expanded = true,
    children = List(
      MenuItemDto(
        id = company,
        view = "sunerp.view.company.CompanyList",
        text = "Công ty",
        leaf = true
      ),
      MenuItemDto(
        id = donVi,
        view = "sunerp.view.donvi.DonViList",
        text = "Đơn vị",
        leaf = true
      ),
      MenuItemDto(
        id = phongBan,
        view = "sunerp.view.phongban.PhongBanList",
        text = "Phòng ban và cung",
        leaf = true
      )
    )
  )

  val quanLyNhanVien = MenuItemDto(
    text = "Quản lý nhân viên",
    expanded = true,
    children = List(
      MenuItemDto(
        id = nhanVien,
        view = "sunerp.view.nhanvien.NhanVienList",
        text = "Nhân viên",
        leaf = true
      ),
      MenuItemDto(
        id = chucVu,
        view = "sunerp.view.chucvu.ChucVuList",
        text = "Chức vụ",
        leaf = true
      ),
      MenuItemDto(
        id = quyenHanh,
        view = "sunerp.view.quyenhanh.QuyenHanhList",
        text = "Quyền hành",
        leaf = true
      )
    )
  )

  val quanLyLuong = MenuItemDto(
    text = "Quản lý luơng",
    expanded = true,
    children = List(
      MenuItemDto(
        id = soLuong,
        view = "sunerp.view.soluong.SoLuongList",
        text = "Sổ lương",
        leaf = true
      )
    )
  )

  val baoCaoKhoiLuong = MenuItemDto(
    text = "Báo cáo khối lượng",
    expanded = true,
    children = List(
      MenuItemDto(
        id = thCongViecHangNgay,
        view = "sunerp.view.report.THCongViecHangNgay",
        text = "TH công việc hàng ngày",
        leaf = true
      ),
      MenuItemDto(
        id = bcThucHienKhoiLuong,
        view = "sunerp.view.report.BCThucHienKhoiLuong",
        text = "BC thực hiện khối lượng",
        leaf = true
      ),
      MenuItemDto(
        id = thThucHienKhoiLuong,
        view = "sunerp.view.report.THThucHienKhoiLuong",
        text = "TH thực hiện khối lượng",
        leaf = true
      ),
      MenuItemDto(
        id = inSoPhanCong,
        view = "sunerp.view.report.InSoPhanCong",
        text = "In sổ phân công",
        leaf = true
      ),
      MenuItemDto(
        id = bangChamCong,
        view = "sunerp.view.report.BangChamCong",
        text = "Bảng chấm công",
        leaf = true
      )
    )
  )

  val rootMenu = MenuItemDto(
    expanded = true,
    children = List(
      quanLyCongty,
      quanLyNhanVien,
      quanLyCongViec,
      quanLyLuong,
      baoCaoKhoiLuong
    )
  )

  def index = StackAction(implicit request => {
    renderOk
  })

  /**
   * Return current login username
   * @return
   */
  def loginUser = StackAction(implicit request => {
    Ok(NhanViens.nhanVienJsonFormatWithQuyenHanh.writes(loggedIn))
  })

  def menuItems = StackAction(implicit request => {
    def travel(menu: MenuItemDto): MenuItemDto = menu.copy(
      children = menu.children
        .filter(child => loggedIn.menuAuth(child.id))
        .map(travel)
    )

    val authMenu = travel(rootMenu)

    //remove empty menu
    Ok(Json.toJson(authMenu.copy(children = authMenu.children.filterNot(_.children.isEmpty))))
  })

  def domains = StackAction(implicit request => {
    val data = DomainKey.list.map(key => Json.obj(
      "name" -> key.toLowerCase,
      "value" -> key.toLowerCase
    ))
    Ok(Json.toJson(data))
  })

}
