# --- !Ups

ALTER TABLE `quyenhanh` CHANGE `chucVuId` `chucVuId` BIGINT(20) NULL;
ALTER TABLE `quyenhanh` ADD `phongBanId` BIGINT(20) NULL AFTER `chucVuId`;
ALTER TABLE `quyenhanh` ADD CONSTRAINT `quyen_hanh_phong_ban_fk` FOREIGN KEY (`phongBanId`) REFERENCES `sunerp`.`phongban`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;