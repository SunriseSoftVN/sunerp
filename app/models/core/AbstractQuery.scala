package models.core

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.{Tag, TableQuery}
import dtos.{ExtGirdDto, PagingDto}

/**
 * The Class QueryHelper.
 *
 * @author Nguyen Duc Dung
 * @since 2/15/14 2:56 PM
 *
 */
abstract class AbstractQuery[E, T <: AbstractTable[E]](cons: Tag => T) extends TableQuery[T](cons) {

  val autoInc = this returning map(_.id)

  def findById(id: Long)(implicit session: Session) = where(_.id === id).firstOption

  def deleteById(id: Long)(implicit session: Session) = where(_.id === id).delete

  protected def beforeUpdate(entity: E)(implicit session: Session) = entity

  protected def beforeSave(entity: E)(implicit session: Session) = entity

  def save(entity: E)(implicit session: Session) = autoInc.insert(beforeSave(entity))

  def update(entity: E, id: Option[Long])(implicit session: Session) = id.map(_id => {
    where(_.id === _id).update(beforeUpdate(entity))
  })

  def all(implicit session: Session) = (for (row <- this) yield row).list()

  def update(entity: E, id: Long)(implicit session: Session) = where(_.id === id).update(entity)

  protected def orderColumn(direction: String, column: Column[_]) = if (direction.toLowerCase == "asc") column.asc else column.desc
}
