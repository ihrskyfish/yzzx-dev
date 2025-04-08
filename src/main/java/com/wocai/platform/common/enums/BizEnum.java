package com.wocai.platform.common.enums;

/**
 * @Description: 业务常量枚举
 * @Author: linwenqiang
 * @Date: 2021-05-11 09:49
 * @Version: V1.0
 */
public class    BizEnum {

    public static enum DelStatus {
        NORMAL("0", "正常"),
        DELETED("1", "删除"),
        STOPED("2", "停用");

        private final String value;
        private final String desrc;

        private DelStatus(String value, String desrc) {
            this.value = value;
            this.desrc = desrc;
        }

        public String getValue() {
            return this.value;
        }

        public String getDesrc() {
            return this.desrc;
        }

        public boolean equals(String equalValue) {
            return this.value.equals(equalValue);
        }

    }


}
