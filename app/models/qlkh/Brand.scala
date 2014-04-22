package models.qlkh

import models.core.{AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.Play.current

/**
 * The Class Brand.
 *
 * @author Nguyen Duc Dung
 * @since 4/22/14 9:28 AM
 *
 */
case class Brand(
                  id: Option[Long] = None,
                  name: String
                  ) extends WithId[Long]

class Brands(tag: Tag) extends AbstractTable[Brand](tag, "brand") {

  def name = column[String]("name", O.NotNull)

  override def * = (id.?, name) <>(Brand.tupled, Brand.unapply)
}

object Brands extends TableQuery[Brands](new Brands(_)) {

  val dbName = "qlkh"

  def findByName(name: String) = DB(dbName).withSession(implicit session => {
    where(_.name === name).firstOption()
  })
}