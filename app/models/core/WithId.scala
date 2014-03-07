package models.core

/**
 * The Class WithId.
 *
 * @author Nguyen Duc Dung
 * @since 2/20/14 1:12 AM
 *
 */
trait WithId[Id] {

  val id: Option[Id]

}
