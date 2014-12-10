import com.typesafe.config.ConfigFactory
import config.ApplicationContext
import filter.{ProfilingFilter, HTMLCompressorFilter}
import java.io.File
import play.api._
import play.api.mvc.Results._
import play.libs.Akka
import play.api.mvc.{RequestHeader, WithFilters}
import play.api.Play.current
import scala.concurrent.Future

/**
 * The Class Global.
 *
 * @author Nguyen Duc Dung
 * @since 1/6/14 9:51 AM
 *
 */
object Global extends WithFilters(HTMLCompressorFilter(), ProfilingFilter) {

  private lazy val devConfFilePath = "conf/dev.conf"
  private lazy val prodConfFilePath = "prod.conf"
  private val ScriptHeader = "-- SQL DDL script\n-- Generated file - do not edit\n\n"


  override def onStart(app: Application) {
    Logger.info("Starting...")
  }

  override def onLoadConfig(config: Configuration, path: File, classLoader: ClassLoader, mode: Mode.Mode) = if (mode == Mode.Prod) {
    val prodConfig = ConfigFactory.parseResources(classLoader, prodConfFilePath)
    config ++ Configuration(prodConfig)
  } else {
    val devConfig = ConfigFactory.parseFileAnySyntax(new File(path, devConfFilePath))
    config ++ Configuration(devConfig)
  }

  override def onStop(app: Application) = {
    Akka.system.shutdown()
    Akka.system.awaitTermination()
    Logger.info("Shutdown...")
  }


  override def onError(request: RequestHeader, ex: Throwable) = if (Play.isDev) {
    super.onError(request, ex)
  } else {
    Future.successful(InternalServerError(views.html.error()))
  }


  override def onHandlerNotFound(request: RequestHeader) = if (Play.isDev) {
    super.onHandlerNotFound(request)
  } else {
    Future.successful(NotFound(views.html.notfound()))
  }

  /**
   * Controllers must be resolved through the bindings. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = ApplicationContext.getControllerInstance(controllerClass)
}
