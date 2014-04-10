package utils

/**
 * The Class StringToIntLong.
 *
 * @author Nguyen Duc Dung
 * @since 6/5/13 11:28 PM
 *
 */
object String2Long {
  def unapply(s: String): Option[Long] = try {
    Some(s.toLong)
  } catch {
    case _: java.lang.NumberFormatException => None
  }
}
