ALTER TABLE `sys_project`
ADD COLUMN `max_purchase_amount` DECIMAL(20,10) UNSIGNED NOT NULL  COMMENT '单笔最大购买数量(ETH数量)' AFTER min_purchase_amount;