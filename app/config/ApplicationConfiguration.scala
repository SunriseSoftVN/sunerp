package config

import com.escalatesoft.subcut.inject._
import controllers.ReportCtr
import services.{ReportServiceImpl, ReportService}


object ApplicationConfiguration extends NewBindingModule(module => {
  import module._
  bind[ReportService] to moduleInstanceOf[ReportServiceImpl]
  bind[ReportCtr] to moduleInstanceOf[ReportCtr]
})
