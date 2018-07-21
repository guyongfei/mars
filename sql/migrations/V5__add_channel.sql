ALTER TABLE `record_user_tx`
ADD COLUMN `channel` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '渠道号' AFTER project_token;

ALTER TABLE `sys_user`
ADD COLUMN `channel` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '渠道号' AFTER project_num;

ALTER TABLE `project_daily_info`
ADD COLUMN `channel` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '渠道号' AFTER actual_tx_count;

CREATE TABLE `sys_channel` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel` VARCHAR(20) NOT NULL COMMENT '渠道号',
  `name` VARCHAR(20) NOT NULL COMMENT '渠道名称',
  `note` VARCHAR(1024) NOT NULL  DEFAULT '' COMMENT '备注',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_channel` (`channel`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='渠道表';