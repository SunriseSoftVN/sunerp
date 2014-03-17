package models.core

import play.api.db.slick.Config.driver.simple._
import scala.slick.ast.{TypedType, ColumnOption}

/**
 * The Class AbstractTable.
 *
 * @author Nguyen Duc Dung
 * @since 2/15/14 2:53 PM
 *
 */
abstract class AbstractTable[E](tag: Tag, tableName: String) extends Table[E](tag, tableName) {
  private var _columns: List[ColumnInfo] = Nil
  def id = defColumn[Long]("id", O.PrimaryKey, O.AutoInc)
  def defColumn[C](name: String, options: ColumnOption[C]*)(implicit tm: TypedType[C]) = {
    _columns = ColumnInfo(name) :: _columns
    column[C](name, options: _*)
  }
}
