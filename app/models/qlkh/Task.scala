package models.qlkh

import models.core.{AbstractQuery, AbstractTable, WithId}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current
import play.api.libs.json.Json
import dtos.{ExtGirdDto, PagingDto}

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
                 name: String,
                 defaultValue: Double,
                 taskTypeCode: Int
                 ) extends WithId[Long]


class Tasks(tag: Tag) extends AbstractTable[Task](tag, "task") {

  def code = column[String]("code", O.NotNull)

  def name = column[String]("name", O.NotNull)

  def defaultValue = column[Double]("defaultValue", O.NotNull)

  def taskTypeCode = column[Int]("taskTypeCode", O.NotNull)

  def * = (id.?, code, name, defaultValue, taskTypeCode) <>(Task.tupled, Task.unapply)
}

object Tasks extends AbstractQuery[Task, Tasks](new Tasks(_)) {

  val dbName = "qlkh"

  def findById(id: Long) = DB(dbName).withSession(implicit session => {
    where(_.id === id).firstOption
  })

  def load(pagingDto: PagingDto): ExtGirdDto[Task] = DB(dbName).withSession(implicit session => {
    var query = for (row <- this) yield row

    //only for task dk, kdk, name
    query = query.where(tabel => tabel.taskTypeCode inSet List(3, 0, 4))

    pagingDto.filters.foreach(filter => {
      query = query.where(table => {
        filter.property match {
          case "nameOrCode" => table.name.toLowerCase.like(filter.asLikeValue) || table.code.toLowerCase.like(filter.asLikeValue)
          case _ => throw new Exception("Invalid filtering key: " + filter.property)
        }
      })
    })

    pagingDto.sorts.foreach(sort => {
      query = query.sortBy(table => {
        sort.property match {
          case "name" => orderColumn(sort.direction, table.name)
          case "code" => orderColumn(sort.direction, table.code)
          case "defaultValue" => orderColumn(sort.direction, table.defaultValue)
          case _ => throw new Exception("Invalid sorting key: " + sort.property)
        }
      })
    })

    val totalRow = Query(query.length).first()

    val rows = query
      .drop(pagingDto.start)
      .take(pagingDto.limit)
      .list

    ExtGirdDto[Task](
      total = totalRow,
      data = rows
    )
  })

  implicit val taskJsonFormat = Json.format[Task]
}