package com.wocai.platform.mqtt.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HeartDataHander {


    public void doLogHealth(String info,String deviceNo) {
        log.info("设备:{},心跳数据上报:{}", deviceNo, info);
    }


}
