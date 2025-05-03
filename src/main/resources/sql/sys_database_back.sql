-- 数据库备份表
CREATE TABLE `sys_database_back` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `back_name` varchar(100) DEFAULT NULL COMMENT '备份名称',
  `back_path` varchar(255) DEFAULT NULL COMMENT '备份位置',
  `description` text COMMENT '描述',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据库备份表';
