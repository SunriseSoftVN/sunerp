package models.qlkh

import models.core.{AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current

/**
 * The Class Station.
 *
 * @author Nguyen Duc Dung
 * @since 4/22/14 9:00 AM
 *
 */
case class Station(
                    id: Option[Long] = None,
                    name: String
                    ) extends WithId[Long]

class Stations(tag: Tag) extends AbstractTable[Station](tag, "station") {

  def name = column[String]("name", O.NotNull)

  override def * = (id.?, name) <>(Station.tupled, Station.unapply)
}


object Stations extends TableQuery[Stations](new Stations(_)) {

  val dbName = "qlkh"

  def findByName(name: String) = DB(dbName).withSession(implicit session => {
    where(_.name === name).firstOption()
  })

}
