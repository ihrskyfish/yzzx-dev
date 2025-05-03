-- 预定房间服务信息表
CREATE TABLE `yz_server_info` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_info_id` bigint(20) DEFAULT NULL COMMENT '用户信息id',
  `bed` varchar(50) DEFAULT NULL COMMENT '婴儿床',
  `food` varchar(50) DEFAULT NULL COMMENT '点心、饮品',
  `yz_meal_appraise` varchar(50) DEFAULT NULL COMMENT '月子餐品鉴',
  `yz_meal_menu` varchar(50) DEFAULT NULL COMMENT '月子餐菜单',
  `meal_number` int(11) DEFAULT NULL COMMENT '陪护餐份数',
  `umbrella` varchar(50) DEFAULT NULL COMMENT '雨伞',
  `park` varchar(50) DEFAULT NULL COMMENT '保安泊车',
  `pado` varchar(50) DEFAULT NULL COMMENT '接送',
  `safe_seat` varchar(50) DEFAULT NULL COMMENT '儿童安全座椅',
  `other` varchar(255) DEFAULT NULL COMMENT '其他需求',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预定房间服务信息';
