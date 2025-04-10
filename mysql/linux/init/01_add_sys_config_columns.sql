-- 如果表不存在则创建表
CREATE TABLE IF NOT EXISTS sys_config (
    id VARCHAR(32) NOT NULL COMMENT '主键',
    key_name VARCHAR(100) COMMENT '配置键名',
    key_value VARCHAR(500) COMMENT '配置键值',
    description TEXT COMMENT '描述',
    del_flag TINYINT(1) DEFAULT 0 COMMENT '删除状态(0-正常,1-已删除)',
    create_by VARCHAR(50) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- 检查并添加key_name列
SET @dbname = 'mmcc';
SET @tablename = 'sys_config';
SET @columnname = 'key_name';
SET @preparedStatement = (SELECT IF(
  (
    SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
    WHERE
      TABLE_SCHEMA = @dbname
      AND TABLE_NAME = @tablename
      AND COLUMN_NAME = @columnname
  ) > 0,
  'SELECT 1',
  'ALTER TABLE sys_config ADD COLUMN key_name VARCHAR(100) COMMENT "配置键名"'
));
PREPARE alterIfNotExists FROM @preparedStatement;
EXECUTE alterIfNotExists;
DEALLOCATE PREPARE alterIfNotExists;

-- 检查并添加key_value列
SET @columnname = 'key_value';
SET @preparedStatement = (SELECT IF(
  (
    SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
    WHERE
      TABLE_SCHEMA = @dbname
      AND TABLE_NAME = @tablename
      AND COLUMN_NAME = @columnname
  ) > 0,
  'SELECT 1',
  'ALTER TABLE sys_config ADD COLUMN key_value VARCHAR(500) COMMENT "配置键值"'
));
PREPARE alterIfNotExists FROM @preparedStatement;
EXECUTE alterIfNotExists;
DEALLOCATE PREPARE alterIfNotExists;

-- 检查并添加description列
SET @columnname = 'description';
SET @preparedStatement = (SELECT IF(
  (
    SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
    WHERE
      TABLE_SCHEMA = @dbname
      AND TABLE_NAME = @tablename
      AND COLUMN_NAME = @columnname
  ) > 0,
  'SELECT 1',
  'ALTER TABLE sys_config ADD COLUMN description TEXT COMMENT "描述"'
));
PREPARE alterIfNotExists FROM @preparedStatement;
EXECUTE alterIfNotExists;
DEALLOCATE PREPARE alterIfNotExists;

-- 检查并添加del_flag列
SET @columnname = 'del_flag';
SET @preparedStatement = (SELECT IF(
  (
    SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
    WHERE
      TABLE_SCHEMA = @dbname
      AND TABLE_NAME = @tablename
      AND COLUMN_NAME = @columnname
  ) > 0,
  'SELECT 1',
  'ALTER TABLE sys_config ADD COLUMN del_flag TINYINT(1) DEFAULT 0 COMMENT "删除状态(0-正常,1-已删除)"'
));
PREPARE alterIfNotExists FROM @preparedStatement;
EXECUTE alterIfNotExists;
DEALLOCATE PREPARE alterIfNotExists; 