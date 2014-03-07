package dtos

import play.api.libs.json.{JsNumber, Writes, JsValue, Json}

/**
 * The Class ExtGirdDto.
 *
 * @author Nguyen Duc Dung
 * @since 2/19/14 8:43 PM
 *
 */
case class ExtGirdDto[E](total: Int, data: List[E])

object ExtGirdDto {

  implicit def jsonWrite[E](implicit fmt: Writes[E]): Writes[ExtGirdDto[E]] = new Writes[ExtGirdDto[E]] {
    override def writes(o: ExtGirdDto[E]) = Json.obj(
      "total" -> JsNumber(o.total),
      "data" -> Json.toJson(o.data)
    )
  }

}