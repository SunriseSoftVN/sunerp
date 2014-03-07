package dtos

import play.api.libs.json.{Writes, Json}
import models.sunerp.{Role, Authority, Roles}

/**
 * The Class AuthorityDto.
 *
 * @author Nguyen Duc Dung
 * @since 2/20/14 10:20 AM
 *
 */
case class AuthorityDto(
                         id: Long,
                         domain: String,
                         roleId: Long,
                         role: Role
                         )


object AuthorityDto {

  def apply(tuple: (Authority, Role)) = new AuthorityDto(
    id = tuple._1.id.get,
    domain = tuple._1.domain,
    roleId = tuple._1.roleId,
    role = tuple._2
  )

  import Roles.roleJsonFormat

  implicit val jsonFormat = new Writes[AuthorityDto] {
    override def writes(o: AuthorityDto) = Json.obj(
      "id" -> o.id,
      "domain" -> o.domain,
      "roleId" -> o.roleId,
      "role" -> roleJsonFormat.writes(o.role)
    )
  }
}