ALTER TABLE `sys_project` ADD COLUMN `token_decimal` INT(11) UNSIGNED NOT NULL COMMENT '项目token的精度，小数点后的位数';

ALTER TABLE `record_user_tx`
DROP COLUMN `pay_tx_id`,
CHANGE `distribution_time`  `distribution_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '打币时间',
ADD COLUMN `tx_verification_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '交易验证时间' AFTER  actual_get_amount,
ADD COLUMN `actual_tx_time` TIMESTAMP NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '实际交易时间'AFTER actual_get_amount,
ADD COLUMN  actual_receiving_address VARCHAR(50) NOT NULL DEFAULT '' COMMENT '实际接受地址'AFTER actual_get_amount,
ADD COLUMN  actual_sending_address VARCHAR(50) NOT NULL DEFAULT '' COMMENT '实际发送地址' AFTER actual_get_amount;

ALTER TABLE `record_user_tx`
MODIFY COLUMN `user_tx_status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0-初始状态,1-交易还未被打包,2-验证成功,21-验证失败（to不是平台地址）,22-验证失败（from不匹配）,23-验证失败（金额不匹配）,3-交易失败,4-交易不存在',
MODIFY COLUMN `platform_tx_status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '打币交易状态(0-初始状态,1-打币中,2-成功,3-失败,4-交易作废)';

ALTER TAble record_user_tx
ADD COLUMN `distribution_batch_id` varchar(20) NOT NULL DEFAULT '' COMMENT '打币的批次id';