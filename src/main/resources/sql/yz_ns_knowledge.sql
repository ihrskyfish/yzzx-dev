-- 护理知识表
CREATE TABLE `yz_ns_knowledge` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `knw_table` varchar(255) DEFAULT NULL COMMENT '标题',
  `intr` varchar(500) DEFAULT NULL COMMENT '简介', 
  `text` text COMMENT '正文',
  `img_big` varchar(255) DEFAULT NULL COMMENT '图片',
  `category_id` int(11) DEFAULT NULL COMMENT '分类id',
  `collect_flag` varchar(1) DEFAULT NULL COMMENT '0表示未收藏1表示收藏',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='护理知识表';
