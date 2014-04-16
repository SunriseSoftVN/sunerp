package config

import com.escalatesoft.subcut.inject._
import controllers._
import services._


object ApplicationConfiguration extends NewBindingModule(module => {
  import module._

  //Controller
  bind[ReportCtr] to moduleInstanceOf[ReportCtr]
  bind[ChucVuCtr] to moduleInstanceOf[ChucVuCtr]
  bind[HomeCtr] to moduleInstanceOf[HomeCtr]
  bind[AdminCtr] to moduleInstanceOf[AdminCtr]
  bind[AuthCtr] to moduleInstanceOf[AuthCtr]
  bind[ChucVuCtr] to moduleInstanceOf[ChucVuCtr]
  bind[CompanyCtr] to moduleInstanceOf[CompanyCtr]
  bind[DonViCtr] to moduleInstanceOf[DonViCtr]
  bind[NhanVienCtr] to moduleInstanceOf[NhanVienCtr]
  bind[PhongBanCtr] to moduleInstanceOf[PhongBanCtr]
  bind[QuyenHanhCtr] to moduleInstanceOf[QuyenHanhCtr]
  bind[ReportCtr] to moduleInstanceOf[ReportCtr]
  bind[SoLuongCtr] to moduleInstanceOf[SoLuongCtr]
  bind[SoPhanCongCtr] to moduleInstanceOf[SoPhanCongCtr]
  bind[TaskCtr] to moduleInstanceOf[TaskCtr]

  //Service
  bind[KhoiLuongReportService] to moduleInstanceOf[KhoiLuongReportServiceImpl]
  bind[SoPhanCongService] to moduleInstanceOf[SoPhanCongServiceImpl]
})
