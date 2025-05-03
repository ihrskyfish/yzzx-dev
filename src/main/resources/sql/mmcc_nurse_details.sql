-- 护理内容表
CREATE TABLE `mmcc_nurse_details` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `type_id` varchar(32) DEFAULT NULL COMMENT '类型id',
  `title` varchar(100) DEFAULT NULL COMMENT '知识标题',
  `state_week` varchar(10) DEFAULT NULL COMMENT '孕期开始周数',
  `end_week` varchar(10) DEFAULT NULL COMMENT '孕期结束周数',
  `exhibit` text DEFAULT NULL COMMENT '知识介绍',
  `content` text DEFAULT NULL COMMENT '内容',
  `sort` varchar(10) DEFAULT NULL COMMENT '排序',
  `recommend` varchar(2) DEFAULT NULL COMMENT '是否推荐(0:否 1:是)',
  `url` varchar(255) DEFAULT NULL COMMENT '知识图片',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='护理内容';
