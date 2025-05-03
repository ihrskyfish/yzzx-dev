-- 设备信息表
CREATE TABLE `device_info` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `device_no` varchar(50) NOT NULL COMMENT '设备编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `location` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `type` int(2) DEFAULT NULL COMMENT '设备类型1:智能厨余设备2:智能垃圾分类设备4:传统厨余设备',
  `status` int(2) DEFAULT NULL COMMENT '状态1:在线2:离线3:维修中4:维修完成',
  `agent_name` varchar(50) DEFAULT NULL COMMENT '代理商姓名',
  `agent_phone` varchar(20) DEFAULT NULL COMMENT '代理商电话',
  `date_created` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_device_no` (`device_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备信息表';
