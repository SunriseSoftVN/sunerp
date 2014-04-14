package config

import com.escalatesoft.subcut.inject.Injectable
import controllers.ReportCtr

/**
 * The Class ApplicationContext.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 12:02 PM
 *
 */
object ApplicationContext extends Injectable {
  implicit val bindingModule = ApplicationConfiguration
  val reportCtr = inject[ReportCtr]
  val reportCtrClass = classOf[ReportCtr]

  def getControllerInstance[A](controllerClass: Class[A]): A = {
    controllerClass match {
      case `reportCtrClass` => reportCtr.asInstanceOf[A]
      case _ => throw new IllegalArgumentException
    }
  }

}
