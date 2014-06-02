# --- !Ups

ALTER TABLE `phongban` ADD `showOnReport` TINYINT(1) NOT NULL DEFAULT '1' AFTER `shortName`;