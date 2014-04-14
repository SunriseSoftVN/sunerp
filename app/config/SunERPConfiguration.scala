package config

import com.escalatesoft.subcut.inject._
import services.{TextGenerator, WelcomeTextGenerator}

/**
 * The Class SunERPConfiguration.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 9:33 AM
 *
 */

object TestId extends BindingId

object SunERPConfiguration extends NewBindingModule(module => {
  import module._

  bind[String] idBy TestId toSingle "asdasd"
  bind[TextGenerator] toModuleSingle {
    implicit module => {
      println("dung ne")
      new WelcomeTextGenerator
    }
  }

  bind[controllers.ReportCtr] toModuleSingle {
    implicit module => {
      println("dung ne 2")
      new controllers.ReportCtr
    }
  }

})
