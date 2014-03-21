import models.core.Hash
import models.sunerp._
import play.api.db.slick._
import play.api.Play.current

/**
 * The Class DBinit.
 *
 * @author Nguyen Duc Dung
 * @since 2/13/14 11:15 AM
 *
 */
object DBinit {

  def init() {
    DB.withTransaction(implicit session => {
      if (Companies.countAll == 0) {
        val companySetting = new CompanySetting(
          luongToiThieu = 1005000
        )

        val companySettingId = CompanySettings.save(companySetting)

        val company = new Company(
          name = "Mặc định",
          address = "Mặc định",
          phone = "Mặc định",
          email = "demo@demo.vn",
          mst = "0123456789",
          companySettingId = companySettingId
        )

        val companyId = Companies.save(company)

        val khoiDonVi = new KhoiDonVi(
          name = "Mặc định",
          companyId = companyId
        )

        val khoiDonViId = KhoiDonVis.save(khoiDonVi)

        val donVi = new DonVi(
          name = "Mặc định",
          khoiDonViId = khoiDonViId
        )

        val donViId = DonVis.save(donVi)

        val phongBang = new PhongBang(
          name = "Phòng kỹ thuật",
          donViId = donViId
        )

        val phongBangId = PhongBangs.save(phongBang)

        val chucVu = new ChucVu(
          name = "Quản trị hệ thống",
          phuCapTrachNhiem = 0
        )

        val chucVuId = ChucVus.save(chucVu)

        val nhanVien = NhanVien(
          maNv = "quantri",
          password = Hash.createPassword("quantri"),
          firstName = "quantri",
          lastName = "quantri",
          heSoLuong = 0,
          chucVuId = chucVuId,
          phongBangId = phongBangId
        )

        NhanViens.save(nhanVien)

        val quyenHanh = new QuyenHanh(
          domain = "*",
          chucVuId = chucVuId
        )

        QuyenHanhs.save(quyenHanh)

      }
    })
  }

}
