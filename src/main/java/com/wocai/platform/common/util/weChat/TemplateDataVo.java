package com.wocai.platform.common.util.weChat;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class TemplateDataVo {
    /**微信文档中要求的格式 "data":
     *                              { "name01":{"value": "某某"},
     *                              "thing01": {"value": "广州至北京"},
     *                              "date01": {"value": "2018-01-01"}}
     */
    private String value;
}
