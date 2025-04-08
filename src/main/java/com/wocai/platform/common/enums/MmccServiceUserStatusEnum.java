package com.wocai.platform.common.enums;

public enum MmccServiceUserStatusEnum {

//    状态（-1：未到店 0：未评价 1：已评价 2：已取消）'
    SERVICE_NOT("-1", "未到店"),
    SERVICE_NOT_EVALUATE("0","未评价"),
    SERVICE_EVALUATE("1","已经评价"),
    SERVICE_CANCELLATION("2","未评价");

    /**
     * TemplateCode
     */
    private String code;
    /**
     * 内容
     */
    private String value;

    private MmccServiceUserStatusEnum(String code, String value) {
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
