-- 用户表
CREATE TABLE `app_user` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` varchar(1) DEFAULT NULL COMMENT '性别(1:男 2:女)',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `openid` varchar(50) DEFAULT NULL COMMENT '用户openid',
  `unionid` varchar(50) DEFAULT NULL COMMENT '用户unionid',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `gestational_week` int(11) DEFAULT NULL COMMENT '当前孕周',
  `gestational_time` datetime DEFAULT NULL COMMENT '填写时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '离馆时间',
  `mom_birthday` date DEFAULT NULL COMMENT '妈妈生日',
  `baby_birthday` date DEFAULT NULL COMMENT '宝宝生日',
  `home_address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `occupancy_type` varchar(50) DEFAULT NULL COMMENT '入住房型',
  `room_num` varchar(20) DEFAULT NULL COMMENT '房号',
  `wedding_anniversary` datetime DEFAULT NULL COMMENT '结婚纪念日',
  `parity` bigint(20) DEFAULT NULL COMMENT '胎次',
  `allergen` varchar(255) DEFAULT NULL COMMENT '致敏源',
  `special_medical_history` varchar(500) DEFAULT NULL COMMENT '特殊病史',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0-正常,1-删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
