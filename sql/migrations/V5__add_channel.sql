ALTER TABLE `record_user_tx`
ADD COLUMN `channel` VARCHAR(20) NOT NULL DEFAULT '0' COMMENT '渠道号' AFTER project_token;

ALTER TABLE `sys_user`
ADD COLUMN `channel` VARCHAR(20) NOT NULL DEFAULT '0' COMMENT '渠道号' AFTER project_num;

CREATE TABLE `project_channel_statistic` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_token` VARCHAR(126) NOT NULL COMMENT '项目token',
  `channelName` VARCHAR(32) NOT NULL COMMENT '渠道名称',
  `channel` VARCHAR(20) NOT NULL COMMENT '渠道号',
  `get_eth_amount` DECIMAL(30,10) UNSIGNED NOT NULL  DEFAULT '0.00000'COMMENT '当日认购数量',
  `actual_get_eth_amount` DECIMAL(30,10) UNSIGNED NOT NULL  DEFAULT '0.00000'COMMENT '当日实际认购数量',
  `pay_token_amount` DECIMAL(30,10) UNSIGNED NOT NULL  DEFAULT '0.00000'COMMENT '当日应分发token数量',
  `actual_pay_token_amount` DECIMAL(30,10) UNSIGNED NOT NULL DEFAULT '0.00000' COMMENT '当日实际应分发token数量',
  `user_count` int(11) UNSIGNED NOT NULL  DEFAULT '0'COMMENT '当日认购用户数量',
  `actual_user_count` int(11) UNSIGNED NOT NULL  DEFAULT '0'COMMENT '当日实际认购用户数量',
  `tx_count` int(11) UNSIGNED NOT NULL  DEFAULT '0'COMMENT '当日申请数量',
  `actual_tx_count` int(11) UNSIGNED NOT NULL  DEFAULT '0'COMMENT '当日实际申请数量',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_multi` (`project_gid`,`channel`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目渠道统计表';



CREATE TABLE `sys_channel` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel_gid` VARCHAR(32) NOT NULL COMMENT '渠道唯一标识',
  `channel` VARCHAR(20) NOT NULL COMMENT '渠道号',
  `name` VARCHAR(20) NOT NULL COMMENT '渠道名称',
  `note` VARCHAR(1024) NOT NULL  DEFAULT '' COMMENT '备注',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_channel` (`channel`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='渠道表';