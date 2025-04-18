-- 创建数据库
CREATE DATABASE IF NOT EXISTS mmcc DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE mmcc;

-- 系统配置表
CREATE TABLE IF NOT EXISTS `sys_config` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `key_name` varchar(100) NOT NULL COMMENT '配置键',
  `key_value` varchar(500) DEFAULT NULL COMMENT '配置值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(2) DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_key_name` (`key_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- 用户信息表
CREATE TABLE IF NOT EXISTS `app_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别(1:男 2:女)',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `openid` varchar(50) DEFAULT NULL COMMENT '用户openid',
  `unionid` varchar(50) DEFAULT NULL COMMENT '用户unionid',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `gestational_week` int DEFAULT NULL COMMENT '当前孕周',
  `gestational_time` datetime DEFAULT NULL COMMENT '填写时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '离馆时间',
  `mom_birthday` date DEFAULT NULL COMMENT '妈妈生日',
  `baby_birthday` date DEFAULT NULL COMMENT '宝宝生日',
  `home_address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `occupancy_type` varchar(50) DEFAULT NULL COMMENT '入住房型',
  `room_num` varchar(20) DEFAULT NULL COMMENT '房号',
  `wedding_anniversary` datetime DEFAULT NULL COMMENT '结婚纪念日',
  `parity` bigint DEFAULT NULL COMMENT '胎次',
  `allergen` varchar(255) DEFAULT NULL COMMENT '致敏源',
  `special_medical_history` text COMMENT '特殊病史',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_phone` (`phone`),
  KEY `idx_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 宝宝B超记录表
CREATE TABLE IF NOT EXISTS `mmcc_baby_ultrasound` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `year` int DEFAULT NULL COMMENT '年',
  `month` int DEFAULT NULL COMMENT '月',
  `day` int DEFAULT NULL COMMENT '日',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `continuous_days` int DEFAULT NULL COMMENT '连续打卡天数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宝宝B超记录表';

-- 孕期圈活动表
CREATE TABLE IF NOT EXISTS `mmcc_activity` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '活动名称',
  `activity_user_name` varchar(50) DEFAULT NULL COMMENT '活动联系人姓名',
  `activity_user_phone` varchar(20) DEFAULT NULL COMMENT '联系人手机号',
  `start_time` datetime DEFAULT NULL COMMENT '报名开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '报名截止时间',
  `introduce` text COMMENT '活动介绍',
  `end_state` varchar(2) DEFAULT '0' COMMENT '结束状态（0：未结束 1已结束）',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `achieve` varchar(255) DEFAULT NULL COMMENT '活动成就',
  `sort` bigint DEFAULT '0' COMMENT '排序',
  `status` varchar(2) DEFAULT '0' COMMENT '状态(0 :草稿  10 发布  20 截止)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_end_state` (`end_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='孕期圈活动表';

-- 活动图片表
CREATE TABLE IF NOT EXISTS `mmcc_activity_file` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `activity_id` varchar(32) NOT NULL COMMENT '活动id',
  `file_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动图片表';

-- 用户活动表
CREATE TABLE IF NOT EXISTS `mmcc_activity_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `activity_id` varchar(32) NOT NULL COMMENT '活动id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_activity_user` (`activity_id`,`user_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户活动表';

-- 交流圈表
CREATE TABLE IF NOT EXISTS `mmcc_communication_circle` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `title` varchar(100) NOT NULL COMMENT '活动标题',
  `content` text COMMENT '内容',
  `sort` varchar(10) DEFAULT '0' COMMENT '排序',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交流圈表';

-- 话题留言表
CREATE TABLE IF NOT EXISTS `mmcc_communication_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `circle_id` varchar(32) NOT NULL COMMENT '交流圈id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `evaluate` text COMMENT '用户评价内容',
  `essence` varchar(2) DEFAULT '0' COMMENT '是否精华（0否 1是）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_circle_id` (`circle_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_essence` (`essence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='话题留言表';

-- 插入一些基础配置数据
INSERT INTO `sys_config` (`id`, `key_name`, `key_value`, `description`, `create_time`, `update_time`, `del_flag`) VALUES
('1', 'system.name', '母婴管理系统', '系统名称', NOW(), NOW(), '0'),
('2', 'system.version', '1.0.0', '系统版本', NOW(), NOW(), '0'),
('3', 'system.copyright', 'Copyright © 2024', '系统版权', NOW(), NOW(), '0'); 