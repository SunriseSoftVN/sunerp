package dtos

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data.FormError
import play.api.i18n.Messages
/**
 * The Class FormErrorDto.
 *
 * @author Nguyen Duc Dung
 * @since 2/11/14 6:14 PM
 *
 */
case class FormErrorDto(key: String, msg: String)

object FormErrorDto {

  def apply(formError: FormError) = new FormErrorDto(
    key = formError.key,
    msg = Messages(formError.message, formError.args:_*)
  )

  implicit val jsonWrite = (
    (__ \ "key").write[String] ~
      (__ \ "msg").write[String]
    )(unlift(FormErrorDto.unapply))

}
