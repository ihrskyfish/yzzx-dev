package com.wocai.platform.mqtt.server;

import com.wocai.platform.mqtt.client.MqttGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class EventDataHandler {

    @Resource
    private MqttGateway mqttGateway;

    /**
     * 处理订阅事件
     */
    public void doEvent(String info) {
        log.info("start----------------------------");
        log.info("收到消息:{}", info);
        log.info("end----------------------------");
    }

}
