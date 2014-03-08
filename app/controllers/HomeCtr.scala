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
        controller = "UserCtr",
        view = "userList",
        text = "Tài khoản",
        leaf = true
      ),
      MenuItemDto(
        id = "role",
        controller = "RoleCtr",
        view = "roleList",
        text = "Quyền hành",
        leaf = true
      ),
      MenuItemDto(
        id = "authority",
        controller = "AuthorityCtr",
        view = "authorityList",
        text = "Phân quyền",
        leaf = true
      ),
      MenuItemDto(
        id = "soPhanCong",
        controller = "SoPhanCongCtr",
        view = "soPhanCongList",
        text = "Sổ phân công",
        leaf = true
      )
    )
  )

  val rootMenu = MenuItemDto(
    expanded = true,
    children = List(
      userManagerMenu
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

    Ok(Json.toJson(travel(rootMenu)))
  })

}
