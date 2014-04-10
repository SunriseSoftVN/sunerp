package filter

import play.api.mvc.{SimpleResult, RequestHeader, Filter}
import scala.concurrent.Future

/**
 * The Class ProfilingFilter.
 *
 * @author Nguyen Duc Dung
 * @since 2/13/14 4:41 PM
 *
 */
object ProfilingFilter extends Filter {

  val warningTime = 1000 //ms

  def apply(next: (RequestHeader) => Future[SimpleResult])(request: RequestHeader): Future[SimpleResult] = {
    val startTime = System.currentTimeMillis()
    val result = next(request)
    val endTime = System.currentTimeMillis() - startTime

    if (endTime > warningTime) {
      play.Logger.warn(s"$request in $endTime ms")
    }

    result
  }
}
