-- 预定房间信息表
CREATE TABLE `yz_pre_room` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `time` datetime DEFAULT NULL COMMENT '预定时间',
  `img` varchar(255) DEFAULT NULL COMMENT '房间图片',
  `evaluate` text DEFAULT NULL COMMENT '评价内容',
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间id',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预定房间信息';
