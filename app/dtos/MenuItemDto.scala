package dtos

import play.api.libs.json.Json

/**
 * The Class MenuItemDto.
 *
 * @author Nguyen Duc Dung
 * @since 2/19/14 11:53 AM
 *
 */
case class MenuItemDto(
                        id: String = "",
                        text: String = "",
                        expanded: Boolean = false,
                        leaf: Boolean = false,
                        controller: String = "",
                        view: String = "",
                        children: List[MenuItemDto] = Nil
                        )

object MenuItemDto {

  implicit val jsonFormat = Json.format[MenuItemDto]

}
