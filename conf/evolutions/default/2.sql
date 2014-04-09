# --- !Ups

ALTER TABLE `quyenHanh` ADD `gioiHan` VARCHAR(250) NOT NULL AFTER `write`;
UPDATE `sunerp`.`quyenHanh` SET `gioiHan` = 'congty';