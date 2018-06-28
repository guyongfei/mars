ALTER TABLE `sys_project` ADD COLUMN `token_decimal` INT(11) UNSIGNED NOT NULL COMMENT '项目token的精度，小数点后的位数';

ALTER TABLE `record_user_tx`
DROP COLUMN `pay_tx_id`,
CHANGE `distribution_time`  `distribution_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '打币时间';