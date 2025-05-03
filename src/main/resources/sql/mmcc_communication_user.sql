-- 话题留言表
CREATE TABLE `mmcc_communication_user` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `circle_id` varchar(32) DEFAULT NULL COMMENT '交流圈id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `evaluate` text DEFAULT NULL COMMENT '用户评价内容',
  `essence` varchar(2) DEFAULT NULL COMMENT '是否精华（0否 1是）',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='话题留言表';
