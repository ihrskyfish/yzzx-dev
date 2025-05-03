-- 产期圈图片表
CREATE TABLE `mmcc_confinement_file` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `confinement_id` varchar(32) DEFAULT NULL COMMENT '产期圈id',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产期圈图片表';
