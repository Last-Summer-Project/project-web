DROP TABLE IF EXISTS `timelapse`;
DROP TABLE IF EXISTS `detect`;
DROP TABLE IF EXISTS `device_log`;
DROP TABLE IF EXISTS `image`;
DROP TABLE IF EXISTS `device`;

CREATE TABLE `device_log`
(
    `id`                BIGINT   NOT NULL AUTO_INCREMENT,
    `device_id`         BIGINT   NOT NULL,
    `temperature`       FLOAT    NOT NULL,
    `relative_humidity` FLOAT    NOT NULL,
    `soil_humidity`     FLOAT    NOT NULL,
    `image_id`          BIGINT   NOT NULL,
    `date_created`      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `PK_DEVICE_LOG` PRIMARY KEY (`id`)
);

CREATE TABLE `image`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(255) NOT NULL,
    `url`          VARCHAR(255) NOT NULL,
    `date_created` DATE         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `PK_IMAGE` PRIMARY KEY (`id`)
);

CREATE TABLE `detect`
(
    `id`     BIGINT                                    NOT NULL,
    `status` ENUM ('not_started','in_progress','done') NOT NULL,
    `result` VARCHAR(255)                              NULL,
    CONSTRAINT `PK_DETECT` PRIMARY KEY (`id`)
);

CREATE TABLE `timelapse`
(
    `id`           BIGINT                                    NOT NULL AUTO_INCREMENT,
    `device_id`    BIGINT                                    NOT NULL,
    `start_date`   DATETIME                                  NOT NULL,
    `end_date`     DATETIME                                  NOT NULL,
    `status`       ENUM ('not_started','in_progress','done') NOT NULL,
    `result`       VARCHAR(255)                              NULL,
    `date_created` DATETIME                                  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_edited`  DATETIME                                  NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `PK_TIMELAPSE` PRIMARY KEY (`id`)
);

CREATE TABLE `device`
(
    `id`           BIGINT       NOT NULL,
    `login_id`     VARCHAR(255) NOT NULL,
    `password`     VARCHAR(255) NOT NULL,
    `date_created` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_edited`  DATETIME     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `PK_DEVICE` PRIMARY KEY (`id`)
);

ALTER TABLE `device_log`
    ADD CONSTRAINT `FK_device_TO_device_log_1` FOREIGN KEY (
                                                            `device_id`
        )
        REFERENCES `device` (
                             `id`
            );

ALTER TABLE `device_log`
    ADD CONSTRAINT `FK_image_TO_device_log_1` FOREIGN KEY (
                                                           `image_id`
        )
        REFERENCES `image` (
                            `id`
            );

ALTER TABLE `detect`
    ADD CONSTRAINT `FK_device_log_TO_detect_1` FOREIGN KEY (
                                                            `id`
        )
        REFERENCES `device_log` (
                                 `id`
            );

ALTER TABLE `timelapse`
    ADD CONSTRAINT `FK_device_TO_timelapse_1` FOREIGN KEY (
                                                           `device_id`
        )
        REFERENCES `device` (
                             `id`
            );
