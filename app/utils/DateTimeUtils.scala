package utils

import com.github.nscala_time.time.Imports._
import org.joda.time.IllegalFieldValueException

/**
 * The Class DateTimeUtils.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 3:57 PM
 *
 */
object DateTimeUtils {

  def getQuarter(month: Int) = {
    val quarter = (month - 1) / 3 + 1
    quarter
  }

  def createLocalDate(month: Int, day: Int) = try {
    LocalDate.now.withMonth(month).withDayOfMonth(day)
  } catch {
    case _: IllegalFieldValueException =>
      LocalDate.now.withMonth(month)
        .withDayOfMonth(LocalDate.now.withMonth(month).dayOfMonth().withMaximumValue().getDayOfMonth)
  }

}
