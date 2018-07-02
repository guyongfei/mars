DROP TABLE  `record_platform_tx`;
CREATE TABLE `record_platform_tx` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL DEFAULT '' COMMENT '项目唯一标识',
  `project_token` VARCHAR(126) NOT NULL DEFAULT '' COMMENT '项目token',
  `tx_hash` VARCHAR(68) NOT NULL COMMENT '交易号',
  `tx_type` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '交易类型(0--->未定义,1-->项目方至平台,2-->平台至项目方,3-->其他)',
  `from_name` VARCHAR(126) NOT NULL DEFAULT '' COMMENT '发起方',
  `from_address` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '发起地址',
  `to_name` VARCHAR(126) NOT NULL DEFAULT '' COMMENT '接受方',
  `to_address` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '接受地址',
  `tx_token_type` VARCHAR(126) NOT NULL DEFAULT '' COMMENT '交易token类别',
  `tx_amount` DECIMAL(30,10) NOT NULL DEFAULT '0.0000000000' COMMENT '交易数量',
  `eth_fee` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '消耗的ETH',
  `tx_status` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '交易状态(0-初始状态,1-打币中,2-成功,3-失败,4-交易作废)',
  `tx_verification_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '交易验证时间',
  `tx_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '实际交易时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tx_hash` (`tx_hash`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='平台交易流水表';