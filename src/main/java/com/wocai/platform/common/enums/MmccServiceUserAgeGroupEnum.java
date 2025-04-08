package com.wocai.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-07-11 14:57
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MmccServiceUserAgeGroupEnum {

    //年龄段配置
    AgeGroup_0("0", "0-5岁"),
    AgeGroup_1("1", "5-10岁"),
    AgeGroup_2("2", "10-15岁"),
    AgeGroup_3("3", "15-20岁"),
    AgeGroup_4("1", "20-25岁"),
    AgeGroup_5("5", "25-30岁"),
    AgeGroup_6("6", "30-35岁"),
    AgeGroup_7("7", "35-40岁"),
    AgeGroup_8("8", "40-45岁"),
    AgeGroup_9("9", "45岁以上");

    /**
     * TemplateCode
     */
    private String code;
    /**
     * 内容
     */
    private String value;

    public static String getMmccServiceUserAgeGroupEnum(String code) {
        for (MmccServiceUserAgeGroupEnum mmccServiceUserAgeGroupEnum : MmccServiceUserAgeGroupEnum.values()) {
            if (mmccServiceUserAgeGroupEnum.getCode().equals(code)) {
                return mmccServiceUserAgeGroupEnum.getValue();
            }
        }
        return null;
    }

}
