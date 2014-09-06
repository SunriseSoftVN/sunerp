# --- !Ups

ALTER TABLE `company` DROP FOREIGN KEY `company_company_setting_fk`;
ALTER TABLE `company` DROP COLUMN `companySettingId`;
ALTER TABLE `companysetting` DROP COLUMN `luongToiThieu`;
ALTER TABLE `companysetting` ADD COLUMN `key` VARCHAR(250) NOT NULL DEFAULT '0' AFTER `id`, ADD COLUMN `value` VARCHAR(250) NOT NULL DEFAULT '0' AFTER `key`;