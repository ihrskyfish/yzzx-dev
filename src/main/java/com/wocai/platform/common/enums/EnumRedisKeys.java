package com.wocai.platform.common.enums;

/**
 * redis 枚举
 */
public enum EnumRedisKeys {

    //rediskey   统一配置
    LOGIN_ERRORTIMES("WASTE:LOGIN_ERRORTIMES", "登陆错误次数"),
    //账号是否被锁定
    LOGIN_ERRORLOCK("WASTE:LOGIN_ERRORLOCK", "账号是否被锁定"),
    //登录token的key
    LOGIN_TOKEN("WASTE:LOGINTOKEN", "登录token的key"),
    //账号是否被锁定
    SYS_LOGIN_TOKEN("WASTE:SYS_LOGINTOKEN", "后台登录token key"),

    //设备访问TOKEN
    DEVICE_TOKEN("WASTE:DEVICETOKEN", "设备访问TOKEN key"),


    //短信验证码
    SMS_CODE("WASTE:SMS_CODE", "短信验证码"),
    //短信验证码
    SMS_CODE_REPEAT("WASTE:SMS_CODE_REPEAT", "重复发送短信验证码"),
    //短信验证码数次
    SMS_VERIFICATION_CODE("WASTE:SMS_VERIFICATION_CODE", "短信验证码数次"),
    APP_NORMAL_USER_TOKEN("mmcc:app_user:", "普通APP用户Token的key"),
    WX_SESSION_KEY("wx:session_key:", "wx小程序登录微信方会话key"),

    //端口信息
    EQUIP_PORT_STATUS("hycdz:equip:port_status:", "充电桩设备端口状态"),
    EQUIP_PORT_REMAINING_MINUTES("hycdz:equip:port_remaining_minutes:", "充电桩设备端口状态"),

    //充电桩离线在线状态
    EQUIP_ONLINE_STATUS("hycdz:equip:online_status", "充电桩离线在线状态"),

    //结算时根据代理商id上锁
    settle_lock("hycdz:lock:settle:agent_id:", "结算代理商锁key");

    /**
     * TemplateCode
     */
    private String code;
    /**
     * 内容
     */
    private String value;

    private EnumRedisKeys(String code, String value) {
        this.code = code;
        this.value
                = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
