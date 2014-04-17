# --- !Ups

ALTER TABLE `donVi` ADD `shortName` VARCHAR(255) NULL AFTER `name`;
ALTER TABLE `phongBan` ADD `shortName` VARCHAR(255) NULL AFTER `name`;