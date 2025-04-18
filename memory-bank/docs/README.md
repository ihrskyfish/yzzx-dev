# 记忆库使用说明

## 功能概述
本记忆库用于存储和管理系统运行时数据，提供缓存和持久化能力。

## 配置说明
配置文件位于 `config/memory-config.json`，主要参数：
- `storageType`: 存储类型(local/remote)
- `cachePolicy`: 缓存策略
- `retentionDays`: 数据保留天数

## 使用示例
```java
// 初始化记忆库
MemoryBank memoryBank = new MemoryBank("memory-bank/config/memory-config.json");

// 存储数据
memoryBank.store("user:123", userData);

// 读取数据
UserData data = memoryBank.retrieve("user:123");
```

## 注意事项
- 定期清理过期数据
- 生产环境建议启用加密
