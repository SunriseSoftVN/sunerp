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

        val donVi = new DonVi(
          name = "Mặc định",
          companyId = companyId
        )

        val donViId = DonVis.save(donVi)

        val phongBan = new PhongBan(
          name = "Phòng kỹ thuật",
          donViId = donViId
        )

        val phongBanId = PhongBans.save(phongBan)

        val chucVu = new ChucVu(
          name = "Quản trị hệ thống",
          phuCapTrachNhiem = 0
        )

        val chucVuId = ChucVus.save(chucVu)

        val nhanVien = NhanVien(
          maNv = "quantri",
          password = "123456",
          firstName = "quantri",
          lastName = "quantri",
          heSoLuong = 0,
          chucVuId = chucVuId,
          phongBanId = phongBanId
        )

        NhanViens.save(nhanVien)

        val quyenHanhCongTy = new QuyenHanh(
          domain = "company",
          chucVuId = chucVuId
        )

        val quyenHanhDonVi = new QuyenHanh(
          domain = "donvi",
          chucVuId = chucVuId
        )

        val quyenHanhPhongBan = new QuyenHanh(
          domain = "phongban",
          chucVuId = chucVuId
        )

        val quyenHanhNhanVien = new QuyenHanh(
          domain = "nhanvien",
          chucVuId = chucVuId
        )

        val quyenHanhChucVu = new QuyenHanh(
          domain = "chucvu",
          chucVuId = chucVuId
        )

        val quyenHanh = new QuyenHanh(
          domain = "quyenhanh",
          chucVuId = chucVuId
        )

        val quyenHanhTask = new QuyenHanh(
          domain = "task",
          write = false,
          chucVuId = chucVuId
        )

        QuyenHanhs.save(quyenHanhCongTy)
        QuyenHanhs.save(quyenHanhDonVi)
        QuyenHanhs.save(quyenHanhPhongBan)
        QuyenHanhs.save(quyenHanhNhanVien)
        QuyenHanhs.save(quyenHanhChucVu)
        QuyenHanhs.save(quyenHanh)
        QuyenHanhs.save(quyenHanhTask)
      }
    })
  }

}
