package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import com.github.nscala_time.time.Imports._
import models.sunerp._
import play.api.db.slick.Config.driver.simple._
import org.scalautils._
import TripleEquals._
import utils.Options.optionLongEq
import com.github.tototoshi.slick.MySQLJodaSupport._

/**
 * The Class SoPhanCongService.
 *
 * @author Nguyen Duc Dung
 * @since 4/16/14 10:42 AM
 *
 */
trait SoPhanCongService {
  def initData(month: Int, phongBanId: Long)(implicit session: Session)

  def dayCopyData(month: Int, day: Int, phongBanId: Long)(implicit session: Session)
}

class SoPhanCongServiceImpl(implicit val bindingModule: BindingModule) extends SoPhanCongService {


  override def dayCopyData(month: Int, day: Int, phongBanId: Long)(implicit session: Session) {
    val date = LocalDate
      .now
      .withMonth(month)
      .withDayOfMonth(day)

    val targetDate = date.minusMonths(1)

    val checkQuery = for {
      soPhanCong <- SoPhanCongs
      if soPhanCong.phongBanId === phongBanId && soPhanCong.ngayPhanCong === date
    } yield soPhanCong

    if (checkQuery.list().isEmpty) {
      val query = for {
        soPhanCong <- SoPhanCongs
        soPhanCongExt <- soPhanCong.soPhanCongExt
        if soPhanCong.phongBanId === phongBanId && soPhanCong.ngayPhanCong === targetDate && soPhanCong.taskId.isNotNull
      } yield (soPhanCong, soPhanCongExt)

      for (tuple <- query.list()) {
        val (soPhanCong, soPhanCongExt) = tuple
        val extId = SoPhanCongExts.save(soPhanCongExt.copy(id = None))
        SoPhanCongs.save(soPhanCong.copy(id = None, ngayPhanCong = date, soPhanCongExtId = extId))
      }
    }
  }

  def initData(month: Int, phongBanId: Long)(implicit session: Session) {
    val date = LocalDate.now.withMonth(month)
    val firstDayOfMonth = date.dayOfMonth().withMinimumValue()
    val lastDayOfMonth = date.dayOfMonth().withMaximumValue()
    val nhanViens = NhanViens.findByPhongBanId(phongBanId)
    val query = for (soPhanCong <- SoPhanCongs.khoiLuongMonthQueryRange(month, date.getYear)
                     if soPhanCong.phongBanId === phongBanId) yield soPhanCong
    val soPhanCongs = query.list()

    for (day <- firstDayOfMonth.getDayOfMonth to lastDayOfMonth.getDayOfMonth) {
      val _date = date.withDay(day)
      //nhan vien da duoc phan cong trong ngay
      val nhanVienIds = soPhanCongs.filter(_.ngayPhanCong == _date).map(_.nhanVienId)
      //Nhan vien chua duoc phan cong
      val notAssignNhanViens = nhanViens.filterNot(nhanVien => nhanVienIds.exists(nhanVien.id === _))

      notAssignNhanViens.foreach(nhanVien => {
        val extId = SoPhanCongExts.save(SoPhanCongExt())
        val soPhanCong = SoPhanCong(
          nhanVienId = nhanVien.id.get,
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