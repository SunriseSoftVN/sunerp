import com.typesafe.config.ConfigFactory
import filter.{ProfilingFilter, HTMLCompressorFilter}
import java.io.File
import play.api._
import play.api.libs.Files
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

  private val ScriptDirectory = "conf/evolutions/activerecord"
  private val CreateScript = "create-database.sql"
  private val ScriptHeader = "-- SQL DDL script\n-- Generated file - do not edit\n\n"


  override def onStart(app: Application) {
    Logger.info("Starting...")
    DBinit.init()
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
   * Writes the given DDL statements to a file.
   */
  private def writeScript(ddlStatements: Seq[String], directory: File, fileName: String): Unit = {
    val createScript = new File(directory, fileName)
    val createSql = ddlStatements.mkString("\n")
    Files.writeFileIfChanged(createScript, ScriptHeader + createSql)
  }


}
