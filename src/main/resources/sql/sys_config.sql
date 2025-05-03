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

-- 插入一些示例数据（可选）
-- INSERT INTO sys_config (key_name, key_value, description, create_by, del_flag)
-- VALUES
-- ('example_key1', 'example_value1', 'This is an example configuration', 'admin', 0),
-- ('example_key2', 'example_value2', 'Another example configuration', 'admin', 0);

-- 验证表结构和数据
SELECT * FROM sys_config;