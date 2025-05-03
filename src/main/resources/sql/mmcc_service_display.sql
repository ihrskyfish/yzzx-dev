-- 服务展示表
CREATE TABLE `mmcc_service_display` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `cover_image_url` varchar(255) DEFAULT NULL COMMENT '封面图url',
  `details_url` varchar(255) DEFAULT NULL COMMENT '详情图url',
  `name` varchar(100) DEFAULT NULL COMMENT '房间名称',
  `room_model` text DEFAULT NULL COMMENT '房型介绍',
  `service_introduce` text DEFAULT NULL COMMENT '服务介绍',
  `room_label` varchar(255) DEFAULT NULL COMMENT '服务标签（,分割开）',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `business_hours` varchar(100) DEFAULT NULL COMMENT '营业时间',
  `phone_code` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `store_address` varchar(255) DEFAULT NULL COMMENT '门店地址',
  `longitude` varchar(50) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(50) DEFAULT NULL COMMENT '纬度',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务展示表';
