package utils

import org.apache.commons._
import org.scalautils.Equality

/**
 * The Class Empty2None.
 *
 * @author Nguyen Duc Dung
 * @since 1/11/14 12:58 AM
 *
 */
object Options {

  def trim(value: Option[String]) = value.flatMap(st => {
    if (lang3.StringUtils.isNotBlank(st)) {
      Some(st)
    } else {
      None
    }
  })

  implicit val optionLongEq = new Equality[Option[Long]] {
    def areEqual(a: Option[Long], b: Any): Boolean =
      b match {
        case o: Long => a.isDefined && a.get == o
        case _ => false
      }
  }

}
