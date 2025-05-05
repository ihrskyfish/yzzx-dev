

-- 创建数据库（如果尚未创建）
CREATE DATABASE IF NOT EXISTS mmcc;

-- 切换到 mmcc 数据库
USE mmcc;

-- 创建表：sys_config
-- CREATE TABLE sys_config (
--     id VARCHAR(36) NOT NULL COMMENT '主键ID',
--     key_name VARCHAR(255) NOT NULL COMMENT '参数Key',
--     key_value VARCHAR(255) NOT NULL COMMENT '参数值',
--     description VARCHAR(255) COMMENT '描述',
--     create_by VARCHAR(36) COMMENT '创建人',
--     create_time DATETIME COMMENT '创建时间',
--     update_by VARCHAR(36) COMMENT '更新人',
--     update_time DATETIME COMMENT '更新时间',
--     del_flag INT COMMENT '删除状态 0正常 1已删除',
--     PRIMARY KEY (id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数设置';

-- 创建表：sys_queue_log
CREATE TABLE sys_queue_log (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    req_data TEXT COMMENT '请求参数',
    queue_key VARCHAR(255) COMMENT '队列key',
    retry_times INT COMMENT '队列重试次数',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- 创建表：sys_permission_data_rule
CREATE TABLE sys_permission_data_rule (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    permission_id VARCHAR(36) COMMENT '对应的菜单id',
    rule_name VARCHAR(255) COMMENT '规则名称',
    rule_column VARCHAR(255) COMMENT '字段',
    rule_conditions VARCHAR(255) COMMENT '条件',
    rule_value VARCHAR(255) COMMENT '规则值',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限规则表';

-- 创建表：sys_dict
CREATE TABLE sys_dict (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    type INT COMMENT '字典类型',
    dict_name VARCHAR(255) COMMENT '字典名称',
    dict_code VARCHAR(255) COMMENT '字典编码',
    description VARCHAR(255) COMMENT '描述',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- 创建表：sys_permission
CREATE TABLE sys_permission (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    parent_id VARCHAR(36) COMMENT '父id',
    name VARCHAR(255) COMMENT '菜单名称',
    perms VARCHAR(255) COMMENT '菜单权限编码',
    perms_type VARCHAR(255) COMMENT '权限策略',
    icon VARCHAR(255) COMMENT '菜单图标',
    component VARCHAR(255) COMMENT '组件',
    component_name VARCHAR(255) COMMENT '组件名字',
    url VARCHAR(255) COMMENT '路径',
    menu_auth VARCHAR(255) COMMENT '菜单类型',
    jump_type VARCHAR(255) COMMENT '菜单跳转类型',
    redirect VARCHAR(255) COMMENT '一级菜单跳转地址',
    sort_no INT COMMENT '菜单排序',
    menu_type INT COMMENT '类型',
    is_leaf BOOLEAN COMMENT '是否叶子节点',
    is_route BOOLEAN COMMENT '是否路由菜单',
    keep_alive BOOLEAN COMMENT '是否缓存页面',
    description VARCHAR(255) COMMENT '描述',
    rule_flag INT COMMENT '是否配置菜单的数据权限',
    hidden BOOLEAN COMMENT '是否隐藏路由菜单',
    status VARCHAR(255) COMMENT '按钮权限状态',
    always_show BOOLEAN COMMENT 'alwaysShow',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- 创建表：b_common_help
CREATE TABLE b_common_help (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    help_id VARCHAR(255) COMMENT '帮助选择ID',
    help_table VARCHAR(255) COMMENT '查询表名',
    help_title VARCHAR(255) COMMENT '帮助选择',
    code_field VARCHAR(255) COMMENT 'CODE字段',
    id_field VARCHAR(255) COMMENT 'ID字段',
    name_field VARCHAR(255) COMMENT '显示名称字段',
    show_field VARCHAR(255) COMMENT '显示列表字段',
    query_field VARCHAR(255) COMMENT '查询字段',
    return_field VARCHAR(255) COMMENT '返回字段',
    help_where VARCHAR(255) COMMENT 'where语句',
    show_name VARCHAR(255) COMMENT '页面显示内容',
    order_by VARCHAR(255) COMMENT '关键值类型',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通用选择帮助类';

-- 创建表：sys_announcement_send
CREATE TABLE sys_announcement_send (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    annt_id VARCHAR(36) COMMENT '通告id',
    user_id VARCHAR(36) COMMENT '用户id',
    read_flag VARCHAR(255) COMMENT '阅读状态',
    read_time DATETIME COMMENT '阅读时间',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户通告阅读标记表';

-- 创建表：sys_area
CREATE TABLE sys_area (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    parent_id VARCHAR(36) COMMENT '父级编号',
    parent_ids VARCHAR(255) COMMENT '所有父级编号',
    name VARCHAR(255) COMMENT '名称',
    sort BIGINT COMMENT '排序',
    code VARCHAR(255) COMMENT '区域编码',
    type VARCHAR(255) COMMENT '区域类型',
    remarks VARCHAR(255) COMMENT '备注',
    disable_flag VARCHAR(255) COMMENT '停用标识',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中国区域';

-- 创建表：sys_data_log
CREATE TABLE sys_data_log (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    data_table VARCHAR(255) COMMENT '表名',
    data_id VARCHAR(255) COMMENT '数据ID',
    data_content TEXT COMMENT '数据内容',
    data_version VARCHAR(255) COMMENT '版本号',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据日志表';

-- 创建表：sys_depart
CREATE TABLE sys_depart (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    parent_id VARCHAR(36) COMMENT '父机构ID',
    depart_name VARCHAR(255) COMMENT '机构/部门名称',
    depart_name_en VARCHAR(255) COMMENT '英文名',
    depart_name_abbr VARCHAR(255) COMMENT '缩写',
    depart_order INT COMMENT '排序',
    description VARCHAR(255) COMMENT '描述',
    org_type VARCHAR(255) COMMENT '机构类型',
    org_code VARCHAR(255) COMMENT '机构编码',
    mobile VARCHAR(255) COMMENT '手机号',
    fax VARCHAR(255) COMMENT '传真',
    address VARCHAR(255) COMMENT '地址',
    memo VARCHAR(255) COMMENT '备注',
    status VARCHAR(255) COMMENT '状态',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 创建表：gen_scheme
CREATE TABLE gen_scheme (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    name VARCHAR(255) COMMENT '名称',
    category VARCHAR(255) COMMENT '分类',
    package_name VARCHAR(255) COMMENT '生成包路径',
    link_url VARCHAR(255) COMMENT '访问链接',
    module_name VARCHAR(255) COMMENT '生成模块名',
    sub_module_name VARCHAR(255) COMMENT '生成子模块名',
    function_name VARCHAR(255) COMMENT '生成功能名',
    function_name_simple VARCHAR(255) COMMENT '生成功能名（简写）',
    function_author VARCHAR(255) COMMENT '生成功能作者',
    gen_table_id VARCHAR(36) COMMENT '生成表编号',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生成方案表';

-- 创建表：sys_log
CREATE TABLE sys_log (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    cost_time BIGINT COMMENT '耗时',
    ip VARCHAR(255) COMMENT 'IP',
    request_param TEXT COMMENT '请求参数',
    request_type VARCHAR(255) COMMENT '请求类型',
    request_url VARCHAR(255) COMMENT '请求路径',
    method VARCHAR(255) COMMENT '请求方法',
    username VARCHAR(255) COMMENT '操作人用户名称',
    userid VARCHAR(36) COMMENT '操作人用户账户',
    log_content TEXT COMMENT '操作详细日志',
    log_type INT COMMENT '日志类型',
    operate_type INT COMMENT '操作类型',
    success_flag VARCHAR(255) COMMENT '是否请求成功',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- 创建表：sys_user_role
CREATE TABLE sys_user_role (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    user_id VARCHAR(36) COMMENT '用户id',
    role_id VARCHAR(36) COMMENT '角色id',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- 创建表：sys_role_permission
CREATE TABLE sys_role_permission (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    role_id VARCHAR(36) COMMENT '角色id',
    permission_id VARCHAR(36) COMMENT '权限id',
    data_rule_ids VARCHAR(255) COMMENT '数据权限',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- 创建表：app_version
CREATE TABLE app_version (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    version_code VARCHAR(255) COMMENT '版本号',
    version_name VARCHAR(255) COMMENT '版本名称',
    app_type VARCHAR(255) COMMENT '设备类型',
    download_url VARCHAR(255) COMMENT '下载地址',
    upload_type VARCHAR(255) COMMENT '上传类型',
    md5 VARCHAR(255) COMMENT '文件MD5',
    status_flag VARCHAR(255) COMMENT '状态标志',
    force_flag VARCHAR(255) COMMENT '是否强制更新',
    remark TEXT COMMENT '更新说明',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备版本表';

-- 创建表：gen_table
CREATE TABLE gen_table (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    name VARCHAR(255) COMMENT '名称',
    comments VARCHAR(255) COMMENT '描述',
    class_name VARCHAR(255) COMMENT '实体类名称',
    entity_package_name VARCHAR(255) COMMENT '实体包路径',
    remarks VARCHAR(255) COMMENT '备注信息',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据表';

-- 创建表：sys_role
CREATE TABLE sys_role (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    role_name VARCHAR(255) COMMENT '角色名称',
    role_code VARCHAR(255) COMMENT '角色编码',
    role_auth VARCHAR(255) COMMENT '角色类型',
    description VARCHAR(255) COMMENT '描述',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 创建表：sys_user
CREATE TABLE sys_user (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    username VARCHAR(255) COMMENT '登录账号',
    realname VARCHAR(255) COMMENT '真实姓名',
    password VARCHAR(255) COMMENT '密码',
    salt VARCHAR(255) COMMENT 'md5密码盐',
    avatar VARCHAR(255) COMMENT '头像',
    birthday DATETIME COMMENT '生日',
    sex INT COMMENT '性别',
    email VARCHAR(255) COMMENT '电子邮件',
    phone VARCHAR(255) COMMENT '电话',
    org_code VARCHAR(255) COMMENT '部门code',
    super_flag VARCHAR(255) COMMENT '是否超级管理员',
    status INT COMMENT '状态',
    activiti_sync VARCHAR(255) COMMENT '同步工作流引擎',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 创建表：sys_permission_data_rule
CREATE TABLE sys_permission_data_rule (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    permission_id VARCHAR(36) COMMENT '对应的菜单id',
    rule_name VARCHAR(255) COMMENT '规则名称',
    rule_column VARCHAR(255) COMMENT '字段',
    rule_conditions VARCHAR(255) COMMENT '条件',
    rule_value VARCHAR(255) COMMENT '规则值',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限规则表';

-- 创建表：gen_table_column
CREATE TABLE gen_table_column (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    gen_table_id VARCHAR(36) COMMENT '关联表id',
    name VARCHAR(255) COMMENT '名称',
    comments VARCHAR(255) COMMENT '描述',
    jdbc_type VARCHAR(255) COMMENT '列的数据类型',
    java_type VARCHAR(255) COMMENT '实体包路径',
    java_field VARCHAR(255) COMMENT '备注信息',
    is_pk VARCHAR(255) COMMENT '是否主键',
    is_null VARCHAR(255) COMMENT '是否可为空',
    is_insert VARCHAR(255) COMMENT '是否为插入字段',
    is_edit VARCHAR(255) COMMENT '是否编辑字段',
    is_list VARCHAR(255) COMMENT '是否列表字段',
    is_query VARCHAR(255) COMMENT '是否查询字段',
    show_location INT COMMENT '显示位置',
    query_type VARCHAR(255) COMMENT '查询方式',
    show_type VARCHAR(255) COMMENT '字段生成方案',
    dict_type VARCHAR(255) COMMENT '字典类型',
    settings VARCHAR(255) COMMENT '其它设置',
    sort INT COMMENT '排序',
    data_length VARCHAR(255) COMMENT '数据长度',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字段表';

-- 创建表：sys_user_agent
CREATE TABLE sys_user_agent (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    user_name VARCHAR(255) COMMENT '用户名',
    agent_user_name VARCHAR(255) COMMENT '代理人用户名',
    start_time DATETIME COMMENT '代理开始时间',
    end_time DATETIME COMMENT '代理结束时间',
    status VARCHAR(255) COMMENT '状态',
    sys_org_code VARCHAR(255) COMMENT '所属部门',
    sys_company_code VARCHAR(255) COMMENT '所属公司',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户代理人设置';

-- 创建表：sys_category
CREATE TABLE sys_category (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    pid VARCHAR(36) COMMENT '父级节点',
    name VARCHAR(255) COMMENT '类型名称',
    code VARCHAR(255) COMMENT '类型编码',
    sys_org_code VARCHAR(255) COMMENT '所属部门',
    has_child VARCHAR(255) COMMENT '是否有子节点',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类字典';

-- 创建表：sys_dict_item
CREATE TABLE sys_dict_item (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    dict_id VARCHAR(36) COMMENT '字典id',
    item_text VARCHAR(255) COMMENT '字典项文本',
    item_value VARCHAR(255) COMMENT '字典项值',
    description VARCHAR(255) COMMENT '描述',
    sort_order INT COMMENT '排序',
    status INT COMMENT '状态',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典项表';

-- 创建表：sys_announcement
CREATE TABLE sys_announcement (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    titile VARCHAR(255) COMMENT '标题',
    msg_content TEXT COMMENT '内容',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    sender VARCHAR(255) COMMENT '发布人',
    priority VARCHAR(255) COMMENT '优先级',
    msg_category VARCHAR(255) COMMENT '消息类型',
    msg_type VARCHAR(255) COMMENT '通告对象类型',
    send_status VARCHAR(255) COMMENT '发布状态',
    send_time DATETIME COMMENT '发布时间',
    cancel_time DATETIME COMMENT '撤销时间',
    user_ids VARCHAR(255) COMMENT '指定用户',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统通告表';

-- 创建表：sys_user_depart
CREATE TABLE sys_user_depart (
    id VARCHAR(36) NOT NULL COMMENT '主键ID',
    user_id VARCHAR(36) COMMENT '用户id',
    dep_id VARCHAR(36) COMMENT '部门id',
    create_by VARCHAR(36) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(36) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    del_flag INT COMMENT '删除状态 0正常 1已删除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户部门关系表';