ALTER TABLE `sys_project`
ADD COLUMN `default_project` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否是默认项目(供首页展示项目详情)' AFTER is_available;
