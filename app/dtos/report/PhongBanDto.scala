package dtos.report

/**
 * The Class PhongBanDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:54 PM
 *
 */
case class PhongBanDto(id: Long, name: String, khoiLuongs: List[KhoiLuongDto] = Nil)
