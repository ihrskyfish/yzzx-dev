package com.wocai.platform.mqtt.test;

import com.alibaba.fastjson.JSONObject;
import com.wocai.platform.mqtt.client.MqttGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class PublishTopic {

    @Resource
    private MqttGateway mqttGateway;

    @Scheduled(cron = "*/20 * * * * ?")
    public void sendTopic() {
//        log.info("测试发送消息-9025--------");
//        mqttGateway.sendToMqtt("在线.....", "status/9025");
        JSONObject reqData = new JSONObject();
        reqData.put("cmd","LOG");
        reqData.put("line",200);
        mqttGateway.sendToMqtt(reqData.toJSONString(),"event/9025");
    }
}
