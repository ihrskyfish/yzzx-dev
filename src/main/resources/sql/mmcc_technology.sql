-- mmcc-工艺栏表
CREATE TABLE `mmcc_technology` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `subtitle` varchar(100) DEFAULT NULL COMMENT '副标题',
  `content` text DEFAULT NULL COMMENT '内容',
  `url` varchar(255) DEFAULT NULL COMMENT '列表展示图片url',
  `sort` varchar(10) DEFAULT NULL COMMENT '排序',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（1启用 -1停用）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='mmcc-工艺栏';
