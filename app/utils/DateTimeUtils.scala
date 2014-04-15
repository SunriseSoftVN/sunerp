package utils

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

}
