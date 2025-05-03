-- 每日打卡表
CREATE TABLE `mmcc_health_check` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `mood` varchar(2) DEFAULT NULL COMMENT '每天心情（0：不开心 1：普通 2：开心）',
  `weight` decimal(10,2) DEFAULT NULL COMMENT '体重kg',
  `bust` decimal(10,2) DEFAULT NULL COMMENT '胸围cm',
  `waist` decimal(10,2) DEFAULT NULL COMMENT '腰围cm',
  `hip` decimal(10,2) DEFAULT NULL COMMENT '臀围cm',
  `year` int(4) DEFAULT NULL COMMENT '年',
  `month` int(2) DEFAULT NULL COMMENT '月',
  `day` int(2) DEFAULT NULL COMMENT '日',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `mood_continuous_days` int(11) DEFAULT NULL COMMENT '连续打卡天数',
  `continuous_days` int(11) DEFAULT NULL COMMENT '连续打卡天数',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每日打卡';
