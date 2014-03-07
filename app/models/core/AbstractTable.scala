package models.core

import play.api.db.slick.Config.driver.simple._

/**
 * The Class AbstractTable.
 *
 * @author Nguyen Duc Dung
 * @since 2/15/14 2:53 PM
 *
 */
abstract class AbstractTable[E](tag: Tag, tableName: String) extends Table[E](tag, tableName) {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
}
