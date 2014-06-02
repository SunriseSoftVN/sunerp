# --- !Ups

CREATE TABLE `diemheso` (
  `id`         BIGINT(20) NOT NULL,
  `nhanVienId` BIGINT(20) NOT NULL,
  `heSo`       DOUBLE     NOT NULL,
  `year`       INT(11)    NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nhanVienId` (`nhanVienId`),
  CONSTRAINT `diemheso_ibfk_1` FOREIGN KEY (`nhanVienId`) REFERENCES `nhanvien` (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  COLLATE =utf8_unicode_ci
