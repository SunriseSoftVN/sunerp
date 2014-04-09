package models.sunerp

import play.api.libs.json.Json

/**
 * The Class GioiHan is using for QuyenHanh model to limit zone of NhanVien.
 *
 * @author Nguyen Duc Dung
 * @since 4/9/14 2:58 PM
 *
 */
object GioiHan {
  val CONGTY = "congty"
  val PHONGBAN = "phongban"
  val DONVI = "donvi"

  val values = List(CONGTY, PHONGBAN, DONVI)

  def asComboxDataSource = Json.arr(
    Json.obj(
      "value" -> CONGTY,
      "name" -> "Công ty"
    ),
    Json.obj(
      "value" -> DONVI,
      "name" -> "Đơn vị"
    ),
    Json.obj(
      "value" -> PHONGBAN,
      "name" -> "Phòng ban và cung"
    )
  )
}
