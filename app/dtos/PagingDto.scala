package dtos

import play.api.mvc._
import utils.{String2Long, String2Int}
import play.api.libs.json._
import org.apache.commons.lang3.StringUtils

/**
 * The Class PageDto.
 *
 * @author Nguyen Duc Dung
 * @since 1/6/14 3:10 AM
 *
 */
case class PagingDto(
                      //do not call it directly user @see getFilters instead
                      filters: List[ColumnFilter],
                      page: Int,
                      start: Int,
                      limit: Int,
                      sorts: List[SortDirection]
                      ) {
  def getFilters : List[ColumnFilter] = filters.filter(_.value.isDefined)
}

case class SortDirection(property: String, direction: String)

case class ColumnFilter(property: String, value: Option[String]) {
  def asLikeValue = value.map(_value => "%" + _value.toLowerCase + "%").getOrElse("%")

  def asInt = value.collect {
    case String2Int(intValue) => intValue
  }.getOrElse(0)

  def asLong = value.collect {
    case String2Long(intValue) => intValue
  }.getOrElse(0l)
}

object PagingDto {

  implicit val sortJsonFormat = Json.format[SortDirection]
  implicit val filterJsonFormat = Json.format[ColumnFilter]


  def apply(request: Request[AnyContent]) = {
    val page = request.getQueryString("page").collect {
      case String2Int(_page) => _page
    }.getOrElse(1)

    val start = request.getQueryString("start").collect {
      case String2Int(_start) => _start
    }.getOrElse(0)

    val limit = request.getQueryString("limit").collect {
      case String2Int(_limit) => _limit
    }.getOrElse(0)

    val sorts = request.getQueryString("sort").fold(List.empty[SortDirection])(sort => {
      val jsValue = Json.parse(sort)
      jsValue.as[List[SortDirection]]
    })

    val filters = request.getQueryString("filter").fold(List.empty[ColumnFilter])(filter => {
      val jsValue = Json.parse(filter)
      jsValue.as[List[ColumnFilter]]
    })

    new PagingDto(
      page = page,
      start = start,
      limit = limit,
      sorts = sorts,
      filters = filters
    )
  }

}