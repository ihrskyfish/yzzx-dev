-- 宣传预定表
CREATE TABLE `yz_publicize_room` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `img_small` varchar(255) DEFAULT NULL COMMENT '头像',
  `imgs` text DEFAULT NULL COMMENT '相册',
  `name` varchar(100) DEFAULT NULL COMMENT '房间名称',
  `room_intr` text DEFAULT NULL COMMENT '房间介绍',
  `service_intr` text DEFAULT NULL COMMENT '服务介绍',
  `service_show` text DEFAULT NULL COMMENT '服务展示',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宣传预定';
