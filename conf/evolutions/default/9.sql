# --- !Ups

create table `xeploai` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`nhanVienId` BIGINT NOT NULL,`month` INTEGER NOT NULL,`year` INTEGER NOT NULL,`xepLoai` VARCHAR(254) NOT NULL);
alter table `xeploai` add constraint `xep_loai_nhan_vien_fk` foreign key(`nhanVienId`) references `nhanVien`(`id`) on update NO ACTION on delete NO ACTION;
