USE `test`;

CREATE TABLE `users`
(
    `id`       bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`     varchar(32)         NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname` varchar(32)         NOT NULL DEFAULT '' COMMENT '昵称',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;