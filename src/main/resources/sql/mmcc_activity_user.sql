-- 用户活动表
CREATE TABLE `mmcc_activity_user` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `activity_id` varchar(32) DEFAULT NULL COMMENT '活动id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户活动表';
