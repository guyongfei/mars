ALTER TABLE `record_user_tx`
ADD COLUMN `free_give_rate` DECIMAL (6,4) NOT NULL DEFAULT 0 COMMENT '交易赠送率' AFTER price_rate;
