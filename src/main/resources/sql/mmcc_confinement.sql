-- mmcc-产期圈表
CREATE TABLE `mmcc_confinement` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `type` varchar(20) DEFAULT NULL COMMENT '活动类型',
  `title` varchar(100) DEFAULT NULL COMMENT '活动标题',
  `start_content` text DEFAULT NULL COMMENT '故事开头',
  `content` text DEFAULT NULL COMMENT '内容',
  `number` bigint(20) DEFAULT NULL COMMENT '查看次数',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `sort` varchar(10) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='mmcc-产期圈';
