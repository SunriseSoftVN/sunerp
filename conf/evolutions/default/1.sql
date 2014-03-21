# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table `authority` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`domain` VARCHAR(254) NOT NULL,`roleId` BIGINT NOT NULL);
create table `cacKhoangCong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`phuCapTn` BIGINT NOT NULL,`phuCapLd` BIGINT NOT NULL,`trucBHLD` BIGINT NOT NULL,`phuCapKV` BIGINT NOT NULL,`congPhanLuong` BIGINT NOT NULL,`chiKhac` BIGINT NOT NULL,`luongDocHai` BIGINT NOT NULL,`nuocUong` BIGINT NOT NULL,`anGiuaCa` BIGINT NOT NULL,`omDauSinhDe` BIGINT NOT NULL);
create table `cacKhoangTru` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`doanPhi` BIGINT NOT NULL,`ungKy1` BIGINT NOT NULL,`bhyt` BIGINT NOT NULL,`bhxh` BIGINT NOT NULL,`thuNo` BIGINT NOT NULL);
create table `chucVu` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`phuCapTrachNhiem` BIGINT NOT NULL);
create table `companySetting` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`luongToiThieu` BIGINT NOT NULL);
create table `company` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`address` VARCHAR(254) NOT NULL,`phone` VARCHAR(254) NOT NULL,`email` VARCHAR(254) NOT NULL,`mst` VARCHAR(254) NOT NULL,`companySettingId` BIGINT NOT NULL);
create table `donVi` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`khoiDonViId` BIGINT NOT NULL);
create table `khoiDonVi` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`companyId` BIGINT NOT NULL);
create table `nhanVien` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`maNv` VARCHAR(254) NOT NULL,`password` VARCHAR(254) NOT NULL,`firstName` VARCHAR(254) NOT NULL,`lastName` VARCHAR(254) NOT NULL,`heSoLuong` BIGINT NOT NULL,`chucVuId` BIGINT NOT NULL,`phongBangId` BIGINT NOT NULL);
create unique index `nhanvien_index` on `nhanVien` (`maNv`);
create table `phongBang` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`donViId` BIGINT NOT NULL,`name` VARCHAR(254) NOT NULL);
create table `quyLuong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`soTien` BIGINT NOT NULL);
create table `quyenHanh` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`domain` VARCHAR(254) NOT NULL,`chucVuId` BIGINT NOT NULL);
create table `role` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL);
create table `soLuong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`nhanVienId` BIGINT NOT NULL,`chucVu` VARCHAR(254) NOT NULL,`heSoLuong` DOUBLE NOT NULL,`luongNd` BIGINT NOT NULL,`k2` DOUBLE NOT NULL,`luongSP` BIGINT NOT NULL,`luongTgCong` DOUBLE NOT NULL,`luongTgTien` BIGINT NOT NULL,`cacKhoangCongId` BIGINT NOT NULL,`cacKhoangTruId` BIGINT NOT NULL,`createdDate` TIMESTAMP NOT NULL);
create table `soPhanCongExt` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`lamDem` BOOLEAN DEFAULT false NOT NULL,`baoHoLaoDong` BOOLEAN DEFAULT false NOT NULL,`docHai` BOOLEAN DEFAULT false NOT NULL,`le` BOOLEAN DEFAULT false NOT NULL,`tet` BOOLEAN DEFAULT false NOT NULL,`thaiSan` BOOLEAN DEFAULT false NOT NULL,`dauOm` BOOLEAN DEFAULT false NOT NULL,`conOm` BOOLEAN DEFAULT false NOT NULL,`taiNanLd` BOOLEAN DEFAULT false NOT NULL,`hop` BOOLEAN DEFAULT false NOT NULL,`hocDaiHan` BOOLEAN DEFAULT false NOT NULL,`hocDotXuat` BOOLEAN DEFAULT false NOT NULL,`viecRieng` BOOLEAN DEFAULT false NOT NULL,`chuNhat` BOOLEAN DEFAULT false NOT NULL);
create table `soPhanCong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`nhanVienId` BIGINT NOT NULL,`taskId` BIGINT NOT NULL,`phongBangId` BIGINT NOT NULL,`khoiLuong` DOUBLE NOT NULL,`gio` DOUBLE NOT NULL,`ghiChu` VARCHAR(254) NOT NULL,`soPhanCongExtId` BIGINT NOT NULL,`ngayPhanCong` TIMESTAMP NOT NULL);
create table `user` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`username` VARCHAR(254) NOT NULL,`password` VARCHAR(254) NOT NULL,`roleId` BIGINT NOT NULL,`nhanVienId` BIGINT);
create unique index `user_index` on `user` (`username`);
alter table `authority` add constraint `role_authority_fk` foreign key(`roleId`) references `role`(`id`) on update NO ACTION on delete NO ACTION;
alter table `company` add constraint `company_company_setting_fk` foreign key(`companySettingId`) references `companySetting`(`id`) on update NO ACTION on delete NO ACTION;
alter table `donVi` add constraint `khoiDonVi_fk` foreign key(`khoiDonViId`) references `khoiDonVi`(`id`) on update NO ACTION on delete NO ACTION;
alter table `khoiDonVi` add constraint `company_khoi_don_vi_fk` foreign key(`companyId`) references `company`(`id`) on update NO ACTION on delete NO ACTION;
alter table `nhanVien` add constraint `phong_bang_nhan_vien_fk` foreign key(`phongBangId`) references `phongBang`(`id`) on update NO ACTION on delete NO ACTION;
alter table `nhanVien` add constraint `chuc_vu_nhan_vien_fk` foreign key(`chucVuId`) references `chucVu`(`id`) on update NO ACTION on delete NO ACTION;
alter table `phongBang` add constraint `doi_vi_phong_bang_fk` foreign key(`donViId`) references `donVi`(`id`) on update NO ACTION on delete NO ACTION;
alter table `quyenHanh` add constraint `quyen_hanh_chuc_vu_fk` foreign key(`chucVuId`) references `chucVu`(`id`) on update NO ACTION on delete NO ACTION;
alter table `soLuong` add constraint `nhanvien_so_luong_fk` foreign key(`nhanVienId`) references `nhanVien`(`id`) on update NO ACTION on delete NO ACTION;
alter table `soLuong` add constraint `cac_khoang_tru_so_luong_fk` foreign key(`cacKhoangTruId`) references `cacKhoangTru`(`id`) on update NO ACTION on delete NO ACTION;
alter table `soLuong` add constraint `cac_khoang_cong_so_luong_fk` foreign key(`cacKhoangCongId`) references `cacKhoangCong`(`id`) on update NO ACTION on delete NO ACTION;
alter table `soPhanCong` add constraint `nhan_vien_so_phan_cong_fk` foreign key(`nhanVienId`) references `nhanVien`(`id`) on update NO ACTION on delete NO ACTION;
alter table `soPhanCong` add constraint `so_phan_cong_ext_so_phan_cong_fk` foreign key(`soPhanCongExtId`) references `soPhanCongExt`(`id`) on update NO ACTION on delete NO ACTION;
alter table `soPhanCong` add constraint `phong_bang_so_phan_cong_fk` foreign key(`phongBangId`) references `phongBang`(`id`) on update NO ACTION on delete NO ACTION;
alter table `user` add constraint `nhan_vien_user_fk` foreign key(`nhanVienId`) references `nhanVien`(`id`) on update NO ACTION on delete NO ACTION;
alter table `user` add constraint `role_user_fk` foreign key(`roleId`) references `role`(`id`) on update NO ACTION on delete NO ACTION;

# --- !Downs

ALTER TABLE authority DROP FOREIGN KEY role_authority_fk;
ALTER TABLE company DROP FOREIGN KEY company_company_setting_fk;
ALTER TABLE donVi DROP FOREIGN KEY khoiDonVi_fk;
ALTER TABLE khoiDonVi DROP FOREIGN KEY company_khoi_don_vi_fk;
ALTER TABLE nhanVien DROP FOREIGN KEY phong_bang_nhan_vien_fk;
ALTER TABLE nhanVien DROP FOREIGN KEY chuc_vu_nhan_vien_fk;
ALTER TABLE phongBang DROP FOREIGN KEY doi_vi_phong_bang_fk;
ALTER TABLE quyenHanh DROP FOREIGN KEY quyen_hanh_chuc_vu_fk;
ALTER TABLE soLuong DROP FOREIGN KEY nhanvien_so_luong_fk;
ALTER TABLE soLuong DROP FOREIGN KEY cac_khoang_tru_so_luong_fk;
ALTER TABLE soLuong DROP FOREIGN KEY cac_khoang_cong_so_luong_fk;
ALTER TABLE soPhanCong DROP FOREIGN KEY nhan_vien_so_phan_cong_fk;
ALTER TABLE soPhanCong DROP FOREIGN KEY so_phan_cong_ext_so_phan_cong_fk;
ALTER TABLE soPhanCong DROP FOREIGN KEY phong_bang_so_phan_cong_fk;
ALTER TABLE user DROP FOREIGN KEY nhan_vien_user_fk;
ALTER TABLE user DROP FOREIGN KEY role_user_fk;
drop table `authority`;
drop table `cacKhoangCong`;
drop table `cacKhoangTru`;
drop table `chucVu`;
drop table `companySetting`;
drop table `company`;
drop table `donVi`;
drop table `khoiDonVi`;
drop table `nhanVien`;
drop table `phongBang`;
drop table `quyLuong`;
drop table `quyenHanh`;
drop table `role`;
drop table `soLuong`;
drop table `soPhanCongExt`;
drop table `soPhanCong`;
drop table `user`;

