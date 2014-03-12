package controllers

import play.api.mvc._
import controllers.element.{AuthConfigImpl, TransactionElement, MainTemplate}
import jp.t2v.lab.play2.auth.AuthenticationElement
import dtos.MenuItemDto
import play.api.libs.json.Json

object HomeCtr extends Controller with AuthenticationElement with AuthConfigImpl with MainTemplate with TransactionElement {

  val userManagerMenu = MenuItemDto(
    text = "Quản lý nguời dùng",
    expanded = true,
    children = List(
      MenuItemDto(
        id = "user",
        view = "sunerp.view.user.UserList",
        text = "Tài khoản",
        leaf = true
      ),
      MenuItemDto(
        id = "role",
        view = "sunerp.view.role.RoleList",
        text = "Quyền hành",
        leaf = true
      ),
      MenuItemDto(
        id = "authority",
        view = "sunerp.view.authority.AuthorityList",
        text = "Phân quyền",
        leaf = true
      )
    )
  )

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
        id = "khoiDonVi",
        view = "sunerp.view.khoidonvi.KhoiDonViList",
        text = "Khối đơn vị",
        leaf = true
      ),
      MenuItemDto(
        id = "donVi",
        view = "sunerp.view.donvi.DonViList",
        text = "Đơn vị",
        leaf = true
      ),
      MenuItemDto(
        id = "phongBang",
        view = "sunerp.view.phongbang.PhongBangList",
        text = "Phòng bang",
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
      userManagerMenu,
      quanLyNhanVien,
      quanLyLuong,
      quanLyCongViec
    )
  )

  def index = StackAction(implicit request => {
    renderOk
  })

  def menuItems = StackAction(implicit request => {

    val authorities = loggedIn.authorities

    def travel(menu: MenuItemDto): MenuItemDto = menu.copy(
      children = menu.children
        .filter(child => checkAuth(authorities, child.id))
        .map(travel)
    )

    val authMenu = travel(rootMenu)

    //remove empty menu
    Ok(Json.toJson(authMenu.copy(children = authMenu.children.filterNot(_.children.isEmpty))))
  })

}
