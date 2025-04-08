package com.wocai.platform.mqtt.client;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 配置MqttGateway消息推送接口类，在sendToMqtt方法中，data为发送的数据，
 * topic为主题，指定topic时想不同的主题发送消息，否则为默认配置主题
 *
 * @author linwq
 */
@MessagingGateway(defaultRequestChannel = "messageChannel")
public interface MqttGateway {

    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

}