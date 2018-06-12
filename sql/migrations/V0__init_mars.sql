

CREATE TABLE `project_daily_info` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_token` VARCHAR(126) NOT NULL COMMENT '项目token',
  `price` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '今日单价(ETH)',
  `subscription_amount` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '当日认购数量',
  `actual_subscription_amount` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '当日实际认购数量',
  `distribute_amount` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '当日应分发token数量',
  `actual_distribute_amount` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '当日实际应分发token数量',
  `tx_user_amount` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '当日认购用户数量',
  `actual_tx_user_amount` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '当日实际认购用户数量',
  `current_day` DATE NOT NULL DEFAULT '0000-00-00' COMMENT '当前日期',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_multi` (`project_gid`,`current_day`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='统计表';

/*Table structure for table `project_description_cn` */

CREATE TABLE `project_description_cn` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_name` VARCHAR(126) NOT NULL COMMENT '项目标题',
  `project_instruction` VARCHAR(256) NOT NULL COMMENT '项目简介',
  `project_content` TEXT COMMENT '项目描述正文',
  `grade_report_link` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '评级报告链接',
  `white_paper_link` VARCHAR(255) NOT NULL COMMENT '项目白皮书链接',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_gid` (`project_gid`),
  UNIQUE KEY `uk_project_name` (`project_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目详情中文表';

/*Table structure for table `project_description_en` */

CREATE TABLE `project_description_en` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_name` VARCHAR(126) NOT NULL COMMENT '项目标题',
  `project_instruction` VARCHAR(256) NOT NULL COMMENT '项目简介',
  `project_content` TEXT COMMENT '项目描述正文',
  `grade_report_link` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '评级报告链接',
  `white_paper_link` VARCHAR(255) NOT NULL COMMENT '项目白皮书链接',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_gid` (`project_gid`),
  UNIQUE KEY `uk_project_name` (`project_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目详情英文表';

/*Table structure for table `project_description_ja` */

CREATE TABLE `project_description_ja` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_name` VARCHAR(126) NOT NULL COMMENT '项目标题',
  `project_instruction` VARCHAR(256) NOT NULL COMMENT '项目简介',
  `project_content` TEXT COMMENT '项目描述正文',
  `grade_report_link` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '评级报告链接',
  `white_paper_link` VARCHAR(255) NOT NULL COMMENT '项目白皮书链接',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_gid` (`project_gid`),
  UNIQUE KEY `uk_project_name` (`project_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目详情日文表';

/*Table structure for table `project_description_ko` */

CREATE TABLE `project_description_ko` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_name` VARCHAR(126) NOT NULL COMMENT '项目标题',
  `project_instruction` VARCHAR(256) NOT NULL COMMENT '项目简介',
  `project_content` TEXT COMMENT '项目描述正文',
  `grade_report_link` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '评级报告链接',
  `white_paper_link` VARCHAR(255) NOT NULL COMMENT '项目白皮书链接',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_gid` (`project_gid`),
  UNIQUE KEY `uk_project_name` (`project_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目详情韩文表';

/*Table structure for table `project_token_distribution` */

CREATE TABLE `project_token_distribution` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_token` VARCHAR(128) NOT NULL COMMENT '项目token',
  `distribute_object` VARCHAR(224) NOT NULL COMMENT '分发对象',
  `distribute_percent` DECIMAL(10,5) NOT NULL DEFAULT '0.00000' COMMENT '分发比例',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_multi` (`project_gid`,`distribute_object`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目token分发比例表';

/*Table structure for table `project_website` */

CREATE TABLE `project_website` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `website_type` INT(11) UNSIGNED NOT NULL COMMENT '网站类型(官网、facebook、twitter等)',
  `link_url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '链接地址',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_multi` (`project_gid`,`website_type`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目网站中间表';

/*Table structure for table `record_platform_tx` */

CREATE TABLE `record_platform_tx` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `tx_hash` VARCHAR(68) NOT NULL COMMENT '交易号',
  `tx_type` TINYINT(1) UNSIGNED NOT NULL COMMENT '交易类型(1-->来自项目,2-->转回项目)',
  `from_name` VARCHAR(126) NOT NULL COMMENT '发起方',
  `from_address` VARCHAR(50) NOT NULL COMMENT '发起地址',
  `to_name` VARCHAR(126) NOT NULL COMMENT '接受方',
  `to_address` VARCHAR(50) NOT NULL COMMENT '接受地址',
  `tx_token_type` VARCHAR(126) NOT NULL COMMENT '交易token类别(0-ETH,1-token)',
  `tx_amount` VARCHAR(126) NOT NULL COMMENT '交易数量',
  `eth_fee` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '消耗的ETH',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tx_hash` (`tx_hash`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='平台交易流水表';

/*Table structure for table `record_user_tx` */

CREATE TABLE `record_user_tx` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_gid` CHAR(32) NOT NULL COMMENT '用户唯一标识',
  `user_email` VARCHAR(126) NOT NULL COMMENT '邮箱',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_token` VARCHAR(126) NOT NULL COMMENT '项目token',
  `user_address` VARCHAR(126) NOT NULL COMMENT '购买发起地址',
  `pay_coin_type` TINYINT(1) NOT NULL COMMENT '购买交易币种(0-ETH,1-BTC)',
  `pay_tx` VARCHAR(68) NOT NULL COMMENT '购买交易号',
  `pay_amount` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '购买支付币种数量',
  `price` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '购买时token价格',
  `hope_get_amount` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '期望得到的token数量',
  `actual_pay_amount` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '实际支付数量',
  `actual_get_amount` DECIMAL(30,10) NOT NULL DEFAULT '0.0000000000' COMMENT '实际得到数量',
  `user_tx_status` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '状态(0-->初始状态,1-成功,2-成功（金额不符）,3-失败（交易号不存在）,4-失败（转账失败）等)',
  `platform_tx` VARCHAR(68) NOT NULL COMMENT '打币交易号',
  `eth_fee` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '手续费',
  `platform_tx_status` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '状态(0-->初始状态,1-校验成功，2-校验失败)',
  `distrubition_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '打币时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_pay_tx` (`pay_tx`),
  UNIQUE KEY `uk_platform_tx` (`platform_tx`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户交易流水表';

/*Table structure for table `sys_project` */

CREATE TABLE `sys_project` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_gid` CHAR(32) NOT NULL COMMENT '项目唯一标识',
  `project_token` VARCHAR(126) NOT NULL COMMENT '项目token',
  `token_address` VARCHAR(126) NOT NULL COMMENT 'token地址',
  `platform_address` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '平台地址',
  `project_address` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '项目方地址',
  `project_logo_link` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '项目logo',
  `project_img_link` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '项目大图',
  `soft_cap` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '软顶数量(ETH数量)',
  `hard_cap` DECIMAL(30,10) UNSIGNED NOT NULL COMMENT '硬顶数量(ETH数量)',
  `min_purchase_amount` DECIMAL(20,10) UNSIGNED NOT NULL COMMENT '最低购买数量(token数量)',
  `start_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
  `end_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `start_price` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '开始单价(ETH)',
  `end_price` DECIMAL(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '结束单价(ETH)',
  `project_status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '项目状态(0-未开始 1-开始认筹还未到数量 2-认筹完成且成功 3-认筹完成但是失败)',
  `is_available` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否有效(0-->无效,1-->有效)',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_gid` (`project_gid`),
  UNIQUE KEY `uk_project_token` (`project_token`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目详情表';

/*Table structure for table `sys_user` */

CREATE TABLE `sys_user` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_gid` CHAR(32) NOT NULL COMMENT '用户唯一标识',
  `email` VARCHAR(126) NOT NULL COMMENT '邮箱',
  `nickname` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '昵称',
  `head_img_url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头像链接',
  `user_password` CHAR(32) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` CHAR(32) NOT NULL DEFAULT '' COMMENT '密码盐',
  `project_num` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '认购项目数量',
  `user_status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '用户状态，0-不可用，1-可用',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_gid` (`user_gid`),
  UNIQUE KEY `uk_email` (`email`),
  UNIQUE KEY `uk_nickname` (`nickname`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';

