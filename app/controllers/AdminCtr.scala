package controllers

import play.api.mvc.{Action, Controller}
import play.api.Play

/**
 * The Class AdminCtr.
 *
 * @author Nguyen Duc Dung
 * @since 4/8/14 10:46 AM
 *
 */
object AdminCtr extends Controller {

  def shutdown = Action {
    Play.stop()
    System.exit(0)
    Ok
  }

}
