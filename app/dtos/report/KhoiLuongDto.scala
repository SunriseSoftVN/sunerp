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
                         lamDem: Boolean = false,
                         baoHoLaoDong: Boolean = false,
                         docHai: Boolean = false,
                         le: Boolean = false,
                         tet: Boolean = false,
                         thaiSan: Boolean = false,
                         dauOm: Boolean = false,
                         conOm: Boolean = false,
                         taiNanLd: Boolean = false,
                         hop: Boolean = false,
                         hocDaiHan: Boolean = false,
                         hocDotXuat: Boolean = false,
                         viecRieng: Boolean = false,
                         chuNhat: Boolean = false,
                         phep: Boolean = false,
                         ngayPhanCong: LocalDate
                         )