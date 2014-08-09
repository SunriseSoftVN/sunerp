# --- !Ups

TRUNCATE khoasophancong;
ALTER TABLE `khoasophancong` ADD `domViId` BIGINT NOT NULL AFTER `id`, ADD INDEX (`donViId`) ;
ALTER TABLE `khoasophancong` ADD FOREIGN KEY (`donViId`) REFERENCES `sunerp`.`donvi`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `khoasophancong` ADD `lock` TINYINT(1) NOT NULL AFTER `donViId`;