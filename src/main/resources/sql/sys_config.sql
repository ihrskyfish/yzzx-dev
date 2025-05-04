-- 创建数据库（如果尚未创建）
CREATE DATABASE IF NOT EXISTS mmcc;

-- 切换到 mmcc 数据库
USE mmcc;

-- 创建 sys_config 表
CREATE TABLE IF NOT EXISTS sys_config (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- 主键
    key_name VARCHAR(255) NOT NULL UNIQUE,       -- 配置键名，唯一
    key_value VARCHAR(255) NOT NULL,            -- 配置键值
    description TEXT,                           -- 配置描述
    create_by VARCHAR(50),                      -- 创建人
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_by VARCHAR(50),                      -- 更新人
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
    del_flag TINYINT DEFAULT 0                  -- 删除标志（0：未删除，1：已删除）
);



INSERT INTO sys_config (key_name, key_value )
VALUES
('redis_event_queue_key', 'default_redis_event_queue_key'),
('oss_access_key', 'your_oss_access_key'),
('oss_access_secret', 'your_oss_access_secret'),
('oss_bucket_name', 'your_oss_bucket_name'),
('oss_endpoint', 'your_oss_endpoint'),
('oss_endpoint_internal', 'your_oss_endpoint_internal'),
('oss_upload_url', 'your_oss_upload_url'),
('uploadpath', 'default_upload_path'),
('plat_upload_path', 'default_plat_upload_path'),
('plat_webapp_path', 'default_plat_webapp_path'),
('plat_env', 'development'),
('plat_interface_address', 'http://your_plat_interface_address'),
('plat_name', 'default_platform_name'),
('plat_upload_head_path', 'default_plat_upload_head_path');

-- 验证表结构和数据
SELECT * FROM sys_config;