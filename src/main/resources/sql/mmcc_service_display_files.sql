-- 服务展示图片表
CREATE TABLE `mmcc_service_display_files` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `display_id` varchar(32) DEFAULT NULL COMMENT '服务展示id',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `introduce` text DEFAULT NULL COMMENT '介绍',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务展示图片表';
