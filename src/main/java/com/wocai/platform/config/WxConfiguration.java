package com.wocai.platform.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.wocai.platform.config.properties.WxMaProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hhx
 * @Date 2023/4/18-1:39
 * @Description
 */
@SpringBootConfiguration(proxyBeanMethods = false)
@EnableConfigurationProperties({WxMaProperties.class})
@Configuration
public class WxConfiguration {
    @Bean
    public WxMaService wxMaService(WxMaProperties wxMaProperties) {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(wxMaProperties.getAppid());
        config.setSecret(wxMaProperties.getSecret());
        config.setMsgDataFormat(wxMaProperties.getMsgDataFormat());

        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }

//    @Value("${wx.miniapp.appid}")
//    private String appid;
//
//    @Value("${wx.miniapp.secret}")
//    private String secret;
//
//    @Bean
//    public WxMaService wxMaService() {
//        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
//        config.setAppid(appid);
//        config.setSecret(secret);
//
//        WxMaServiceImpl wxMaService = new WxMaServiceImpl();
//        wxMaService.setWxMaConfig(config);
//
//        return wxMaService;
//    }

}
