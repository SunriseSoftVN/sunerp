package models.core

import scalaz._
import Scalaz._

/**
 * The Class WithId.
 *
 * @author Nguyen Duc Dung
 * @since 2/20/14 1:12 AM
 *
 */
trait WithId[Id] {

  val id: Option[Id]

  def getId = id err "The id can't not be empty, something might wrong!"
}
