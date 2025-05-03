-- 孕期健康表
CREATE TABLE `yz_preg_health` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `mood` varchar(2) DEFAULT NULL COMMENT '1开心2普通3不开心',
  `days` int(11) DEFAULT NULL COMMENT '打卡天数',
  `weight` varchar(20) DEFAULT NULL COMMENT '体重',
  `bust` varchar(20) DEFAULT NULL COMMENT '胸围',
  `waist` varchar(20) DEFAULT NULL COMMENT '腰围',
  `arm` varchar(20) DEFAULT NULL COMMENT '臂围',
  `b_imgs` varchar(255) DEFAULT NULL COMMENT 'b超图',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='孕期健康';
