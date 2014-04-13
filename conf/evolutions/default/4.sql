# --- !Ups

ALTER TABLE `soPhanCong` ADD `taskCode` VARCHAR(255) NOT NULL AFTER `taskName`, ADD `taskUnit` VARCHAR(255) NOT NULL AFTER `taskCode`, ADD `taskQuota` DOUBLE NULL AFTER `taskUnit`;