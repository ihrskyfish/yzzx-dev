package com.wocai.platform.common.util.weChat;

import lombok.Data;

import java.util.Map;

/*
 * 小程序推送所需数据
 * */
@Data
public class WxMssVo {
    private String touser;//用户openid
    private String template_id;//模版id
    private String page = "pages/tabBar/home";//默认跳到小程序首页
    //    private String emphasis_keyword = "keyword1.DATA";//放大那个推送字段
    private Map<String, TemplateDataVo> data;//推送文字
}
