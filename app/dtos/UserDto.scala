package dtos

import play.api.libs.json.{Writes, Json}
import models.sunerp.{User, Role, Roles}

/**
 * The Class UserDto.
 *
 * @author Nguyen Duc Dung
 * @since 2/17/14 6:16 AM
 *
 */
case class UserDto(
                    id: Long,
                    username: String,
                    fullname: String,
                    password: String,
                    roleId: Long,
                    role: Role
                    )

object UserDto {
  def apply(tuple: (User, Role)) = {
    val (user, role) = tuple
    new UserDto(
      id = user.id.get,
      username = user.username,
      fullname = user.fullname,
      password = user.password,
      roleId = user.roleId,
      role = role
    )
  }

  import Roles.roleJsonFormat

  implicit val jsonWrite = new Writes[UserDto] {
    override def writes(dto: UserDto) = Json.obj(
      "id" -> dto.id,
      "username" -> dto.username,
      "fullname" -> dto.fullname,
      "password" -> dto.password,
      "roleId" -> dto.roleId,
      "role" -> roleJsonFormat.writes(dto.role)
    )
  }
}
