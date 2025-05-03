-- 宝宝B超表
CREATE TABLE `mmcc_baby_ultrasound` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `year` int(4) DEFAULT NULL COMMENT '年',
  `month` int(2) DEFAULT NULL COMMENT '月',
  `day` int(2) DEFAULT NULL COMMENT '日',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `continuous_days` int(11) DEFAULT NULL COMMENT '连续打卡天数',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宝宝B超表';
