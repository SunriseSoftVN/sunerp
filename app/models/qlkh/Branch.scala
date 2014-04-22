package models.qlkh

import models.core.{AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.Play.current

/**
 * The Class Branch.
 *
 * @author Nguyen Duc Dung
 * @since 4/22/14 9:28 AM
 *
 */
case class Branch(
                  id: Option[Long] = None,
                  name: String
                  ) extends WithId[Long]

class Branchs(tag: Tag) extends AbstractTable[Branch](tag, "branch") {

  def name = column[String]("name", O.NotNull)

  override def * = (id.?, name) <>(Branch.tupled, Branch.unapply)
}

object Branchs extends TableQuery[Branchs](new Branchs(_)) {

  val dbName = "qlkh"

  def findByName(name: String) = DB(dbName).withSession(implicit session => {
    where(_.name === name).firstOption()
  })
}