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

  val quanLyPhongBang = MenuItemDto(
    text = "Quản lý phòng bang",
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

  val rootMenu = MenuItemDto(
    expanded = true,
    children = List(
      userManagerMenu,
      quanLyPhongBang
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
