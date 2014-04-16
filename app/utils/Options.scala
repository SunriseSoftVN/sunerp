package utils

import org.apache.commons._

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

}
