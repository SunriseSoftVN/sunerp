# --- !Ups

CREATE TABLE `trangthainhanvien` (
  `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nhanVienId` BIGINT(20) NOT NULL,
  `nghiViec`   TINYINT(2) NOT NULL,
  `month`      INT(11)    NOT NULL,
  `year`       INT(11)    NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_trangthainhanvien_nhanvien` (`nhanVienId`),
  CONSTRAINT `FK_trangthainhanvien_nhanvien` FOREIGN KEY (`nhanVienId`) REFERENCES `nhanvien` (`id`)
)
  COLLATE ='utf8_unicode_ci'
  ENGINE =InnoDB
  AUTO_INCREMENT=1;

CREATE TABLE `hesoluong` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nhanVienId` BIGINT(20) NOT NULL,
  `value` DOUBLE NOT NULL,
  `month` INT(11) NOT NULL,
  `year` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nhanVienId_month_year` (`nhanVienId`, `month`, `year`),
  CONSTRAINT `FK_hesoluong_nhanvien` FOREIGN KEY (`nhanVienId`) REFERENCES `nhanvien` (`id`)
)
  COLLATE='utf8_unicode_ci'
  ENGINE=InnoDB
  AUTO_INCREMENT=1;

update `congthucluong` set `name`='Lương thời gian tối thiểu' where `key` ='thoigian.k';