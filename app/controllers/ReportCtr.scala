package controllers

import play.api.mvc.{Action, Controller}
import controllers.element.TransactionElement
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import services.TextGenerator

/**
 * The Class ReportCtr.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 8:34 AM
 *
 */
class ReportCtr(implicit val bindingModule: BindingModule) extends Controller with Injectable {

  // injectOptional provides safe interception of bindings, but falling back on a default implementation otherwise
  def textGenerator = inject[TextGenerator]


  def test = Action(implicit request => {
    Ok(textGenerator.welcomeText)
  })


}
