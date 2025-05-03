-- 服务更新记录表
CREATE TABLE `sys_version` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `sys_type` varchar(2) DEFAULT NULL COMMENT '服务类型(1:WEB 2:后台)',
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `remark` text COMMENT '更新说明',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务更新记录表';
