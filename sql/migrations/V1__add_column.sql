ALTER TABLE `sys_project` ADD COLUMN `token_decimal` INT(11) UNSIGNED NOT NULL COMMENT '项目token的精度，小数点后的位数';

ALTER TABLE `record_user_tx`
DROP COLUMN `pay_tx_id`,
CHANGE `distribution_time`  `distribution_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '打币时间',
ADD COLUMN `tx_verification_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '交易验证时间' AFTER  actual_get_amount,
ADD COLUMN `actual_tx_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '实际交易时间'AFTER actual_get_amount,
ADD COLUMN  actual_receiving_address VARCHAR(50) NOT NULL DEFAULT '' COMMENT '实际接受地址'AFTER actual_get_amount,
ADD COLUMN  actual_sending_address VARCHAR(50) NOT NULL DEFAULT '' COMMENT '实际发送地址' AFTER actual_get_amount;
