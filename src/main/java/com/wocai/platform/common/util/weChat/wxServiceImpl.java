package com.wocai.platform.common.util.weChat;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class wxServiceImpl {

    private static RestTemplate restTemplate;

    final Boolean flag = false;


    /**
     * description 1.获取用户的临时code
     * param [appid, redirectUrl]
     * return java.lang.String
     * author
     **/
    public static String getUserUathUrl(String appid, String redirectUrl) throws UnsupportedEncodingException {
        StringBuffer getcodeUrl = new StringBuffer()
                .append("https://open.weixin.qq.com/connect/oauth2/authorize")
                .append("?appid=" + appid)
                .append("&redirect_uri=" + URLEncoder.encode(redirectUrl, "utf-8"))
                .append("&response_type=code")
                .append("&scope=snsapi_userinfo")
                .append("&state=" + System.currentTimeMillis())
                .append("#wechat_redirect");

        return getcodeUrl.toString();
    }

    /**
     * description  2.获取用户的openid和access_token
     * param [appid, appSecret, code]
     * return java.lang.String
     * author
     **/
    public static String getBaseAccessTokenUrl(String appid, String appSecret, String code) throws UnsupportedEncodingException {
        StringBuffer baseAccessTokenUrl = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=" + appid)
                .append("&secret=" + appSecret)
                .append("&code=" + code)
                .append("&grant_type=authorization_code");

        return baseAccessTokenUrl.toString();
    }

    /**
     * description  3.根据openid 获取用户的信息
     * param [accessToken, openid]
     * return java.lang.String
     * author
     **/
    public static String getBaseUserInfoUrl(String accessToken, String openid) {
        StringBuffer baseUserInfoUrl = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/userinfo")
                .append("?access_token=" + accessToken)
                .append("&openid=" + openid)
                .append("&lang=zh_CN");
        return baseUserInfoUrl.toString();
    }

    /**
     * description 4检验授权凭证（access_token）是否有效
     * param [openid, accessToken]
     * return java.lang.String
     **/
    public static String checkAccessToken(String openid, String accessToken) {
        StringBuffer stringBuffer = new StringBuffer().append(" https://api.weixin.qq.com/sns/auth")
                .append("?access_token=" + accessToken)
                .append("&openid=" + openid);
        return stringBuffer.toString();
    }

    /**
     * description 微信小程序登录，通过code获取session_key和openid
     * param [appid, secret, code]
     * return java.lang.String
     * author
     **/
    public static String getCode2Session(String appid, String secret, String code) {
        StringBuffer code2Session = new StringBuffer()
                .append("ttps://api.weixin.qq.com/sns/jscode2session")
                .append("?appid=" + appid)
                .append("&secret=" + secret)
                .append("&js_code=" + code)
                .append("&grant_type=authorization_code");
        return code2Session.toString();
    }


    /**
     *    推送消息给指定的用户
     * @param access_token  app的token
     * @param openid 用户openid
     * @param type 类型 1派发模板 2.反馈提醒 3审核模板 4日期截至提醒模板
     * @param templateId 模板ID
     * @param keywords {与模板字段一一对应}
     * @return
     */
    public String pushOneUser(String access_token, String openid, String type, String templateId, String[] keywords) {

        //如果access_token为空则从新获取
        if(StringUtils.isEmpty(access_token)){
            access_token = getAccess_token();
        }

        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send" +
                "?access_token=" + access_token;

        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(openid);//用户openid
        /*wxMssVo.setForm_id(formId);//formId*/
        wxMssVo.setTemplate_id(templateId);//模版id
        Map<String, TemplateDataVo> m = new HashMap<>();
        if (type.equals("3")) {
            m.put("time1", new TemplateDataVo(keywords[0]));
            m.put("thing4", new TemplateDataVo(keywords[1]));
            m.put("thing7", new TemplateDataVo(keywords[2]));
            wxMssVo.setData(m);
        } else if (type.equals("2")) {
            m.put("date2",new TemplateDataVo(keywords[0]));
            m.put("phone_number9",new TemplateDataVo(keywords[1]));
            m.put("thing4",new TemplateDataVo(keywords[2]));
            m.put("thing5",new TemplateDataVo(keywords[3]));
            m.put("thing6",new TemplateDataVo(keywords[4]));
            wxMssVo.setData(m);
        }

        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, JSONObject.toJSONString(wxMssVo), String.class);
        log.error("小程序推送结果={}", responseEntity.getBody());
        return responseEntity.getBody();
    }


    /*
     * 获取access_token
     * appid和appsecret到小程序后台获取，当然也可以让小程序开发人员给你传过来
     * */
    public String getAccess_token() {
        //获取access_token
        String appid = "wx25ec10509ca5f5b4";
        String appsecret = "f204a8dff887ccaa940be992431e9b45";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                "&appid=" + appid + "&secret=" + appsecret;
        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }
        String json = restTemplate.getForObject(url, String.class);
        JSONObject myJson = JSONObject.parseObject(json);
        return myJson.get("access_token").toString();
    }
}
