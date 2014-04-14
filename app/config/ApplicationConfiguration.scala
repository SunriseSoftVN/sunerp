package config

import com.escalatesoft.subcut.inject._
import controllers.ReportCtr
import services.{ReportServiceImpl, ReportService}


object ApplicationConfiguration extends NewBindingModule(module => {

  import module._

  bind[ReportService] toModuleSingle {
    implicit module => new ReportServiceImpl
  }

  bind[ReportCtr] toModuleSingle {
    implicit module => new ReportCtr
  }

})
