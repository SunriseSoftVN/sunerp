package dtos.report

import models.qlkh.Task

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
                    unit: String,
                    khoiLuongDM: Double,
                    gioDM: Option[Double] = None
                    )

object TaskDto {

  def apply(task: Task) = new TaskDto(
    id = task.id.get,
    name = task.name,
    code = task.code,
    unit = task.unit,
    khoiLuongDM = task.defaultValue,
    gioDM = task.quota.map(_ * task.defaultValue)
  )

}