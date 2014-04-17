package dtos.report

/**
 * The Class DonViDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/17/14 9:09 AM
 *
 */
case class DonViDto(id: Long, name: String, phongBans: List[PhongBanDto] = Nil)
