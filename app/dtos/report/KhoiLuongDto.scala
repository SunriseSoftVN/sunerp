package dtos.report

import org.joda.time.LocalDate

/**
 * The Class KhoiLuongDto.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 2:39 PM
 *
 */
case class KhoiLuongDto(
                         task: TaskDto,
                         nhanVien: NhanVienDto,
                         khoiLuong: Double,
                         gio: Double,
                         ngayPhanCong: LocalDate
                         )