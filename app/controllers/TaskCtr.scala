package controllers

import play.api.mvc.Controller
import jp.t2v.lab.play2.auth.AuthElement
import controllers.element.AuthConfigImpl
import dtos.PagingDto
import play.api.libs.json.{Writes, Json}
import models.qlkh.{Task, Tasks}

/**
 * The Class TaskCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/12/14 5:10 PM
 *
 */
object TaskCtr extends Controller with AuthElement with AuthConfigImpl {
  val domainName = DomainKey.task
  implicit val jsonWrite: Writes[Task] = Tasks.taskJsonFormat

  def index = StackAction(AuthorityKey -> domainName)(implicit request => {
    val paging = PagingDto(request)
    Ok(Json.toJson(Tasks.load(paging)))
  })

}
