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

  def load(pagingDto: PagingDto)(implicit session: Session): ExtGirdDto[E] = {
    var query = for (row <- this) yield row

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        val column = findColumn(filter.property, List(table))
        column like "%" + filter.value + "%"
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        val column = findColumn(sort.property, List(table))
        sort.direction.toLowerCase match {
          case "asc" => column.asc
          case "desc" => column.desc
          case o => throw new Exception("Invalid sorting key: " + o)
        }
      })
    })


    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list

    ExtGirdDto[E](
      total = totalRow,
      data = rows
    )
  }

  def findColumn[T1 <: AbstractTable[_]](path: String, tables: List[T1]): Column[String] = {
    val column = getTableName(path).flatMap(data => {
      val (table, column) = data
      tables.find(_.tableName == table).map(_.column[String](column))
    }).getOrElse {
      tables.head.column[String](path)
    }
    column
  }

  private def getTableName(path: String): Option[(String, String)] = {
    if (path.indexOf(".") > 0) {
      val table = path.split('.').head
      val col = path.split('.').last
      Some((table, col))
    } else None
  }

  protected def orderColumn(direction: String, column: Column[_]) = if (direction == "asc") column.asc else column.desc
}
