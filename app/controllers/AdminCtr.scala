package controllers

import play.api.mvc.{Action, Controller}
import play.api.Play
import com.escalatesoft.subcut.inject.BindingModule

/**
 * The Class AdminCtr.
 *
 * @author Nguyen Duc Dung
 * @since 4/8/14 10:46 AM
 *
 */
class AdminCtr(implicit val bindingModule: BindingModule) extends Controller {

  def shutdown = Action {
    Play.stop()
    System.exit(0)
    Ok
  }

}
