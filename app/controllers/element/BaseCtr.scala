package controllers.element

import play.api.mvc.{AnyContent, Controller}
import models.core.{WithId, AbstractTable, AbstractQuery}
import jp.t2v.lab.play2.auth.AuthElement
import jp.t2v.lab.play2.stackc.{RequestWithAttributes, StackableController}
import dtos.{FormErrorDto, PagingDto}
import play.api.libs.json.{JsValue, Writes, Json}
import play.api.data.Form
import play.api.db.slick.Session

/**
 * The Class CURDElement.
 *
 * @author Nguyen Duc Dung
 * @since 2/19/14 7:18 PM
 *
 */
abstract class BaseCtr[E <: WithId[Long], T <: AbstractTable[E]] extends Controller with StackableController with AuthElement with AuthConfigImpl with TransactionElement {

  val domainName: String
  val dao: AbstractQuery[E, T]
  implicit val jsonWrite: Writes[E]
  def editForm(implicit session: Session): Form[E]

  def index = StackAction(AuthorityKey -> domainName)(implicit request => {
    val paging = PagingDto(request)
    Ok(doIndex(paging, request))
  })

  protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue

  def update(id: Long) = StackAction(AuthorityKey -> domainName)(implicit request => {
    editForm.bindFromRequest.fold(
      formError => {
        val errors = formError.errors.map(FormErrorDto.apply)
        BadRequest(Json.toJson(errors))
      },
      model => {
        dao.update(model, model.id)
        Ok
      }
    )
  })

  def delete(id: Long) = StackAction(AuthorityKey -> domainName)(implicit request => {
    dao.deleteById(id)
    Ok
  })

  def save = StackAction(AuthorityKey -> domainName)(implicit request => {
    editForm.bindFromRequest.fold(
      formError => {
        val errors = formError.errors.map(FormErrorDto.apply)
        BadRequest(Json.toJson(errors))
      },
      model => {
        val newId: Long = dao.save(model)
        Ok(Json.obj(
          "metaData" -> Json.obj(
            "id" -> newId
          )
        ))
      }
    )
  })

}
