package dtos.report

import models.qlkh.Task
import play.api.libs.json.Json

/**
 * The Class TaskDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:44 PM
 *
 */
case class TaskDto(
                    id: Long,
                    name: String,
                    code: String,
                    donVi: String,
                    soLan: Option[Double] = None,
                    dinhMuc: Double,
                    children: List[TaskDto] = Nil
                    )

object TaskDto {

  implicit val jsonFormat = Json.format[TaskDto]

}