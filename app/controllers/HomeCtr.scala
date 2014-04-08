package controllers

import play.api.mvc._
import controllers.element.{AuthConfigImpl, TransactionElement, MainTemplate}
import jp.t2v.lab.play2.auth.AuthenticationElement
import dtos.MenuItemDto
import play.api.libs.json.Json
import models.sunerp.NhanViens

object HomeCtr extends Controller with AuthenticationElement with AuthConfigImpl with MainTemplate with TransactionElement {

  val quanLyCongViec = MenuItemDto(
    text = "Quản lý công việc",
    expanded = true,
    children = List(
      MenuItemDto(
        id = "soPhanCong",
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
        id = "company",
        view = "sunerp.view.company.CompanyList",
        text = "Công ty",
        leaf = true
      ),
      MenuItemDto(
        id = "donVi",
        view = "sunerp.view.donvi.DonViList",
        text = "Đơn vị",
        leaf = true
      ),
      MenuItemDto(
        id = "phongBan",
        view = "sunerp.view.phongban.PhongBanList",
        text = "Phòng ban",
        leaf = true
      )
    )
  )

  val quanLyNhanVien = MenuItemDto(
    text = "Quản lý nhân viên",
    expanded = true,
    children = List(
      MenuItemDto(
        id = "nhanVien",
        view = "sunerp.view.nhanvien.NhanVienList",
        text = "Nhân viên",
        leaf = true
      ),
      MenuItemDto(
        id = "chucVu",
        view = "sunerp.view.chucvu.ChucVuList",
        text = "Chức vụ",
        leaf = true
      ),
      MenuItemDto(
        id = "quyenHanh",
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
        id = "soLuong",
        view = "sunerp.view.soluong.SoLuongList",
        text = "Sổ lương",
        leaf = true
      )
    )
  )

  val rootMenu = MenuItemDto(
    expanded = true,
    children = List(
      quanLyCongty,
      quanLyNhanVien,
      quanLyLuong,
      quanLyCongViec
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
    Ok(NhanViens.nhanVienJsonFormat.writes(loggedIn))
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

}
