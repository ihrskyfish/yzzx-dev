-- 创建数据库（如果尚未创建）
CREATE DATABASE IF NOT EXISTS mmcc;
USE mmcc;


DROP TABLE IF EXISTS sys_user ;

-- 创建 sys_user 表
CREATE TABLE IF NOT EXISTS sys_user (
    id INT AUTO_INCREMENT PRIMARY KEY, -- 主键，自动递增
    username VARCHAR(50) NOT NULL UNIQUE, -- 用户名，唯一
    realname VARCHAR(50), -- 真实姓名
    password VARCHAR(100) NOT NULL, -- 密码
    salt VARCHAR(50), -- 密码盐值
    avatar VARCHAR(255), -- 头像路径
    birthday DATE, -- 生日
    sex CHAR(1), -- 性别，M/F/O（男/女/其他）
    email VARCHAR(100), -- 邮箱
    phone VARCHAR(20), -- 手机号
    org_code VARCHAR(50), -- 组织代码
    super_flag CHAR(1) DEFAULT '0', -- 超级管理员标志，0 表示普通用户，1 表示超级管理员
    status CHAR(1) DEFAULT '1', -- 状态，1 表示正常，0 表示禁用
    activiti_sync CHAR(1) DEFAULT '0', -- 流程同步标志，0 表示不同步，1 表示同步
    create_by VARCHAR(50), -- 创建人
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_by VARCHAR(50), -- 更新人
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
    del_flag CHAR(1) DEFAULT '0' -- 删除标志，0 表示未删除，1 表示已删除
);

-- 插入示例数据
INSERT INTO sys_user (
    username, realname, password, salt, avatar, birthday, sex, email, phone, org_code, super_flag, status, activiti_sync, create_by, update_by
) VALUES
('admin', '管理员', 'admin123', 'salt123', 'avatar1.jpg', '1990-01-01', 'M', 'admin@example.com', '1234567890', 'org001', '1', '1', '1', 'admin', 'admin'),
('user1', '用户1', 'password1', 'salt456', 'avatar2.jpg', '1995-05-15', 'F', 'user1@example.com', '0987654321', 'org002', '0', '1', '0', 'admin', 'admin'),
('user2', '用户2', 'password2', 'salt789', 'avatar3.jpg', '1985-12-25', 'O', 'user2@example.com', '1122334455', 'org003', '0', '1', '0', 'admin', 'admin');

-- 查询表结构
DESC sys_user;

-- 查询表数据
SELECT * FROM sys_user;