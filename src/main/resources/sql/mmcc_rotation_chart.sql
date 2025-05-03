-- 轮播图表
CREATE TABLE `mmcc_rotation_chart` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `file_name` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `content` text COMMENT '图片内容',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图表';
