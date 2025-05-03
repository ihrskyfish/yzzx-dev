-- 孕期圈活动表
CREATE TABLE `mmcc_activity` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `activity_user_name` varchar(50) DEFAULT NULL COMMENT '活动联系人姓名',
  `activity_user_phone` varchar(20) DEFAULT NULL COMMENT '联系人手机号',
  `start_time` datetime DEFAULT NULL COMMENT '报名开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '报名截止时间',
  `introduce` text DEFAULT NULL COMMENT '活动介绍',
  `end_state` varchar(2) DEFAULT NULL COMMENT '结束状态（0：未结束 1已结束）',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `achieve` text DEFAULT NULL COMMENT '活动成就',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序',
  `status` varchar(10) DEFAULT NULL COMMENT '状态(0 :草稿  10 发布  20 截止)',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='孕期圈活动';
