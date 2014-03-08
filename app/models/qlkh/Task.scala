package models.qlkh

import models.core.{AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current
import play.api.libs.json.Json

/**
 * The Class task.
 *
 * @author Nguyen Duc Dung
 * @since 3/7/14 2:33 PM
 *
 */
case class Task(
                 id: Option[Long] = None,
                 code: String,
                 name: String
                 ) extends WithId[Long]


class Tasks(tag: Tag) extends AbstractTable[Task](tag, "task") {

  def code = column[String]("code", O.NotNull)

  def name = column[String]("name", O.NotNull)

  def * = (id.?, code, name) <>(Task.tupled, Task.unapply)
}

object Tasks extends TableQuery[Tasks](new Tasks(_)) {

  val dbName = "qlkh"

  def findById(id: Long) = DB(dbName).withSession(implicit session => {
    where(_.id === id).firstOption
  })

  implicit val taskJsonFormat = Json.format[Task]
}