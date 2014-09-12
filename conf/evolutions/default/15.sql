# --- !Ups

ALTER TABLE `company` DROP FOREIGN KEY `company_company_setting_fk`;
ALTER TABLE `company` DROP COLUMN `companySettingId`;
ALTER TABLE `companysetting` DROP COLUMN `luongToiThieu`;
ALTER TABLE `companysetting` ADD COLUMN `key` VARCHAR(250) NOT NULL DEFAULT '0' AFTER `id`, ADD COLUMN `value` DOUBLE NOT NULL DEFAULT '0' AFTER `key`;
ALTER TABLE `companysetting` ADD COLUMN `name` VARCHAR(250) NULL AFTER `value`;
RENAME TABLE `companysetting` TO `congthucluong`;
ALTER TABLE `congthucluong` ADD COLUMN `month` INT NOT NULL AFTER `name`, ADD COLUMN `year` INT NOT NULL AFTER `month`;