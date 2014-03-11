# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table `authority` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`domain` VARCHAR(254) NOT NULL,`roleId` BIGINT NOT NULL);
create table `cac_khoan_cong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`phuCapTn` BIGINT NOT NULL,`phuCapLd` BIGINT NOT NULL,`trucBHLD` BIGINT NOT NULL,`phuCapKV` BIGINT NOT NULL,`congPhanLuong` BIGINT NOT NULL,`chiKhac` BIGINT NOT NULL,`luongDocHai` BIGINT NOT NULL,`nuocUong` BIGINT NOT NULL,`anGiuaCa` BIGINT NOT NULL,`omDauSinhDe` BIGINT NOT NULL);
create table `cac_khoang_tru` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`doanPhi` BIGINT NOT NULL,`ungKy1` BIGINT NOT NULL,`bhyt` BIGINT NOT NULL,`bhxh` BIGINT NOT NULL,`thuNo` BIGINT NOT NULL);
create table `chuc_vu` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`phuCapTrachNhiem` BIGINT NOT NULL);
create table `company_setting` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`companyId` BIGINT NOT NULL,`luongToiThieu` BIGINT NOT NULL);
create table `company` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`address` VARCHAR(254) NOT NULL,`phone` VARCHAR(254) NOT NULL,`email` VARCHAR(254) NOT NULL,`mst` VARCHAR(254) NOT NULL);
create table `don_vi` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`companyId` BIGINT NOT NULL,`khoiDonViId` BIGINT NOT NULL);
create table `khoi_don_vi` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`companyId` BIGINT NOT NULL);
create table `nhan_vien` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`firstName` VARCHAR(254) NOT NULL,`lastName` VARCHAR(254) NOT NULL,`heSoLuong` BIGINT NOT NULL,`chucVuId` BIGINT NOT NULL,`phongBangId` BIGINT NOT NULL);
create table `phong_bang` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`donViId` BIGINT NOT NULL,`name` VARCHAR(254) NOT NULL);
create table `quy_luong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`soTien` BIGINT NOT NULL);
create table `role` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL);
create table `so_luong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`nhanVienId` BIGINT NOT NULL,`chucVu` VARCHAR(254) NOT NULL,`heSoLuong` DOUBLE NOT NULL,`luongNd` BIGINT NOT NULL,`k2` DOUBLE NOT NULL,`luongSP` BIGINT NOT NULL,`luongTgCong` DOUBLE NOT NULL,`luongTgTien` BIGINT NOT NULL,`cacKhoangCongId` BIGINT NOT NULL,`cacKhoangTruId` BIGINT NOT NULL,`createdDate` TIMESTAMP NOT NULL);
create table `so_phan_cong_ext` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`lamDem` BOOLEAN DEFAULT false NOT NULL,`baoHoLaoDong` BOOLEAN DEFAULT false NOT NULL,`docHai` BOOLEAN DEFAULT false NOT NULL,`le` BOOLEAN DEFAULT false NOT NULL,`tet` BOOLEAN DEFAULT false NOT NULL,`thaiSan` BOOLEAN DEFAULT false NOT NULL,`dauOm` BOOLEAN DEFAULT false NOT NULL,`conOm` BOOLEAN DEFAULT false NOT NULL,`taiNanLd` BOOLEAN DEFAULT false NOT NULL,`hop` BOOLEAN DEFAULT false NOT NULL,`hocDaiHan` BOOLEAN DEFAULT false NOT NULL,`hocDotXuat` BOOLEAN DEFAULT false NOT NULL,`viecRieng` BOOLEAN DEFAULT false NOT NULL,`chuNhat` BOOLEAN DEFAULT false NOT NULL);
create table `so_phan_cong` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`nhanVienId` BIGINT NOT NULL,`taskId` BIGINT NOT NULL,`phongBangId` BIGINT NOT NULL,`khoiLuong` DOUBLE NOT NULL,`gio` DOUBLE NOT NULL,`ghiChu` VARCHAR(254) NOT NULL,`soPhanCongExtId` BIGINT NOT NULL,`ngayPhanCong` TIMESTAMP NOT NULL);
create table `user` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`username` VARCHAR(254) NOT NULL,`password` VARCHAR(254) NOT NULL,`role_id` BIGINT NOT NULL,`nhanVienId` BIGINT);
alter table `authority` add constraint `role_authority_fk` foreign key(`roleId`) references `role`(`id`) on update NO ACTION on delete NO ACTION;
alter table `company_setting` add constraint `company_company_setting_fk` foreign key(`companyId`) references `company`(`id`) on update NO ACTION on delete NO ACTION;
alter table `don_vi` add constraint `company_don_vi_fk` foreign key(`companyId`) references `company`(`id`) on update NO ACTION on delete NO ACTION;
alter table `don_vi` add constraint `khoiDonVi_fk` foreign key(`khoiDonViId`) references `khoi_don_vi`(`id`) on update NO ACTION on delete NO ACTION;
alter table `khoi_don_vi` add constraint `company_khoi_don_vi_fk` foreign key(`companyId`) references `company`(`id`) on update NO ACTION on delete NO ACTION;
alter table `nhan_vien` add constraint `chuc_vu_nhan_vien_fk` foreign key(`chucVuId`) references `chuc_vu`(`id`) on update NO ACTION on delete NO ACTION;
alter table `nhan_vien` add constraint `phong_bang_nhan_vien_fk` foreign key(`phongBangId`) references `phong_bang`(`id`) on update NO ACTION on delete NO ACTION;
alter table `phong_bang` add constraint `doi_vi_phong_bang_fk` foreign key(`donViId`) references `don_vi`(`id`) on update NO ACTION on delete NO ACTION;
alter table `so_luong` add constraint `nhanvien_so_luong_fk` foreign key(`nhanVienId`) references `nhan_vien`(`id`) on update NO ACTION on delete NO ACTION;
alter table `so_luong` add constraint `cac_khoang_cong_so_luong_fk` foreign key(`cacKhoangCongId`) references `cac_khoan_cong`(`id`) on update NO ACTION on delete NO ACTION;
alter table `so_luong` add constraint `cac_khoang_tru_so_luong_fk` foreign key(`cacKhoangTruId`) references `cac_khoang_tru`(`id`) on update NO ACTION on delete NO ACTION;
alter table `so_phan_cong` add constraint `phong_bang_so_phan_cong_fk` foreign key(`phongBangId`) references `phong_bang`(`id`) on update NO ACTION on delete NO ACTION;
alter table `so_phan_cong` add constraint `nhan_vien_so_phan_cong_fk` foreign key(`nhanVienId`) references `nhan_vien`(`id`) on update NO ACTION on delete NO ACTION;
alter table `so_phan_cong` add constraint `so_phan_cong_ext_so_phan_cong_fk` foreign key(`soPhanCongExtId`) references `so_phan_cong_ext`(`id`) on update NO ACTION on delete NO ACTION;
alter table `user` add constraint `role_fk` foreign key(`role_id`) references `role`(`id`) on update NO ACTION on delete NO ACTION;

# --- !Downs

ALTER TABLE authority DROP FOREIGN KEY role_authority_fk;
ALTER TABLE company_setting DROP FOREIGN KEY company_company_setting_fk;
ALTER TABLE don_vi DROP FOREIGN KEY company_don_vi_fk;
ALTER TABLE don_vi DROP FOREIGN KEY khoiDonVi_fk;
ALTER TABLE khoi_don_vi DROP FOREIGN KEY company_khoi_don_vi_fk;
ALTER TABLE nhan_vien DROP FOREIGN KEY chuc_vu_nhan_vien_fk;
ALTER TABLE nhan_vien DROP FOREIGN KEY phong_bang_nhan_vien_fk;
ALTER TABLE phong_bang DROP FOREIGN KEY doi_vi_phong_bang_fk;
ALTER TABLE so_luong DROP FOREIGN KEY nhanvien_so_luong_fk;
ALTER TABLE so_luong DROP FOREIGN KEY cac_khoang_cong_so_luong_fk;
ALTER TABLE so_luong DROP FOREIGN KEY cac_khoang_tru_so_luong_fk;
ALTER TABLE so_phan_cong DROP FOREIGN KEY phong_bang_so_phan_cong_fk;
ALTER TABLE so_phan_cong DROP FOREIGN KEY nhan_vien_so_phan_cong_fk;
ALTER TABLE so_phan_cong DROP FOREIGN KEY so_phan_cong_ext_so_phan_cong_fk;
ALTER TABLE user DROP FOREIGN KEY role_fk;
drop table `authority`;
drop table `cac_khoan_cong`;
drop table `cac_khoang_tru`;
drop table `chuc_vu`;
drop table `company_setting`;
drop table `company`;
drop table `don_vi`;
drop table `khoi_don_vi`;
drop table `nhan_vien`;
drop table `phong_bang`;
drop table `quy_luong`;
drop table `role`;
drop table `so_luong`;
drop table `so_phan_cong_ext`;
drop table `so_phan_cong`;
drop table `user`;

