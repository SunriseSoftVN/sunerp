package utils

import com.github.nscala_time.time.Imports._
import org.joda.time.{LocalDate, IllegalFieldValueException}
import java.util.Calendar

/**
 * The Class DateTimeUtils.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 3:57 PM
 *
 */
object DateTimeUtils {

  val quarters = Map(
    1 -> List(1, 2, 3),
    2 -> List(4, 5, 6),
    3 -> List(7, 8, 9),
    4 -> List(10, 11, 12)
  )

  def currentYear = LocalDate.now.getYear

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

  // This takes a 1-based month, e.g. January=1. If you want to use a 0-based
  // month, remove the "- 1" later on.
  // author: http://stackoverflow.com/questions/9909361/how-can-i-find-saturdays-and-sundays-in-a-given-month
  def countWeekendDays(year: Int, month: Int) = {
    val calendar = Calendar.getInstance()
    // Note that month is 0-based in calendar, bizarrely.
    calendar.set(year, month - 1, 1)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    var count = 0
    for (day <- 1 to daysInMonth) {
      calendar.set(year, month - 1, day)
      val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
      if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
        count += 1
      }
    }
    count
  }

  def monthWorkingDay(year: Int, month: Int) = {
    val now = LocalDate.now().withMonthOfYear(month).withYear(year)
    val soNgayTrongThang = now.dayOfMonth().withMaximumValue().getDayOfMonth
    val weekend = DateTimeUtils.countWeekendDays(now.getYear, now.getMonthOfYear)
    val workingDay = now.dayOfMonth().withMaximumValue().getDayOfMonth - weekend
    workingDay
  }

}
