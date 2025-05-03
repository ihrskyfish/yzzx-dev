-- 公益栏信息表
CREATE TABLE `test_pwc` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `pwc_table` varchar(255) DEFAULT NULL COMMENT 'pwc_table',
  `pwc_table_img` varchar(255) DEFAULT NULL COMMENT 'pwc_table_img',
  `pwc_intr` varchar(500) DEFAULT NULL COMMENT 'pwc_intr',
  `pwc_text` text COMMENT 'pwc_text',
  `pwc_table_img_big` varchar(255) DEFAULT NULL COMMENT 'pwc_table_img_big',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公益栏信息表';
