package controllers

import play.api.mvc.Controller
import jp.t2v.lab.play2.auth.AuthElement
import controllers.element.AuthConfigImpl
import dtos.PagingDto

/**
 * The Class TaskCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/12/14 5:10 PM
 *
 */
object TaskCtr extends Controller with AuthElement with AuthConfigImpl {

  def index = StackAction(AuthorityKey -> "task")(implicit request => {
    val paging = PagingDto(request)
    Ok
  })

}
