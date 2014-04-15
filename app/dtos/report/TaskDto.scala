package dtos.report

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
                    gioDM: Double
                    )
