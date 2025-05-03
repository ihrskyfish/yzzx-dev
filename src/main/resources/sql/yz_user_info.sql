-- 房间预定用户信息表
CREATE TABLE `yz_user_info` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话',
  `visit_time` datetime DEFAULT NULL COMMENT '参观时间',
  `gest_week` int(11) DEFAULT NULL COMMENT '孕周',
  `residence` varchar(100) DEFAULT NULL COMMENT '住宅',
  `number` int(11) DEFAULT NULL COMMENT '参观人数',
  `hospital` varchar(100) DEFAULT NULL COMMENT '产检医院',
  `relation` varchar(50) DEFAULT NULL COMMENT '陪同人员关系',
  `child` varchar(10) DEFAULT NULL COMMENT '是否带孩子',
  `age` varchar(20) DEFAULT NULL COMMENT '年龄段',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房间预定用户信息';
