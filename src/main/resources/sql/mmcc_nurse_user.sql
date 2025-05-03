-- 用户收藏表
CREATE TABLE `mmcc_nurse_user` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `detailes_id` varchar(32) DEFAULT NULL COMMENT '类别名称',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户收藏表';
