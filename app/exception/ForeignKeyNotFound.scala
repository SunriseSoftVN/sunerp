package exception

/**
 * The Class ForeignKeyNotFound.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 6:56 PM
 *
 */
case class ForeignKeyNotFound(messages: String = "") extends Exception(messages) {

}
