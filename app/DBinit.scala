import models.core.Hash
import models.sunerp._
import models.sunerp.Authority
import models.sunerp.Role
import play.api.db.slick._
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.Some

/**
 * The Class DBinit.
 *
 * @author Nguyen Duc Dung
 * @since 2/13/14 11:15 AM
 *
 */
object DBinit {

  def init() {
    DB.withTransaction(implicit session => {
      if (Users.countAll == 0) {
        val role = Roles.findByName("admin").getOrElse {
          val role: Role = Role(name = "admin")
          val id = Roles.save(role)
          role.copy(id = Some(id))
        }

        Authorities += Authority(
          domain = "*",
          roleId = role.id.get
        )

        Users += User(
          username = "admin",
          fullname = "admin",
          password = Hash.createPassword("admin"),
          roleId = role.id.get
        )
      }
    })
  }

}
