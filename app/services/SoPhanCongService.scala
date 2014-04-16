package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import com.github.nscala_time.time.Imports._
import models.sunerp._
import play.api.db.slick.Config.driver.simple._

/**
 * The Class SoPhanCongService.
 *
 * @author Nguyen Duc Dung
 * @since 4/16/14 10:42 AM
 *
 */
trait SoPhanCongService {
  def initData(month: Int, phongBanId: Long)(implicit session: Session)
}

class SoPhanCongServiceImpl(implicit val bindingModule: BindingModule) extends SoPhanCongService {
  def initData(month: Int, phongBanId: Long)(implicit session: Session) {
    val date = LocalDate.now.withMonth(month)
    val firstDayOfMonth = date.dayOfMonth().withMinimumValue()
    val lastDayOfMonth = date.dayOfMonth().withMaximumValue()
    val nhanViens = NhanViens.findByPhongBanId(phongBanId)
    val query = for (soPhanCong <- SoPhanCongs.soPhanCongQueryRange(month, date.getYear)
                     if soPhanCong.phongBanId === phongBanId) yield soPhanCong
    val soPhanCongs = query.list()

    for (day <- firstDayOfMonth.getDayOfMonth to lastDayOfMonth.getDayOfMonth) {
      val _date = date.withDay(day)
      //nhan vien da duoc phan cong trong ngay
      val nhanVienIds = soPhanCongs.filter(_.ngayPhanCong == _date).map(_.nhanVienId)
      //Nhan vien chua duoc phan cong
      val notAssignNhanViens = nhanViens.filterNot(nhanVien => nhanVienIds.exists(_ == nhanVien.id.get))

      notAssignNhanViens.foreach(nhanVien => {
        val extId = SoPhanCongExts.save(SoPhanCongExt())
        val soPhanCong = SoPhanCong(
          nhanVienId = nhanVien.id.get,
          taskName = "",
          phongBanId = phongBanId,
          soPhanCongExtId = extId,
          khoiLuong = 0d,
          gio = 0d,
          ghiChu = "",
          ngayPhanCong = _date
        )
        SoPhanCongs.save(soPhanCong)
      })
    }

  }
}