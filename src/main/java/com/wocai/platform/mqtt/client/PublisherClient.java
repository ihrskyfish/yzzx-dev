package com.wocai.platform.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PublisherClient{
    private static volatile PublisherClient instance;
    private static MqttClient client;
    private static MqttConnectOptions connOpts;
    private PublisherClient() {
    	try {
    		client = new MqttClient("tcp://47.114.133.102:1883", String.valueOf(System.currentTimeMillis()),new MemoryPersistence());
    		// 创建链接参数
            connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            //设置连接的用户名
            connOpts.setUserName("wocai");
            // 设置连接的密码
            connOpts.setPassword("admin".toCharArray());
//            connOpts.setAutomaticReconnect(true);
            // 建立连接
            client.connect(connOpts);
		} catch (Exception e) {
			
		}
    }
    
    public static PublisherClient getInstance() {
        if (instance == null) {
            synchronized (PublisherClient.class) {
                if (instance == null) {
                    instance = new PublisherClient();
                }
            }
        }
        return instance;
    }
    
    public void publish(final String topic, final byte[] payload){
    	// 内存存储
        try {
            // 创建消息
            MqttMessage message = new MqttMessage(payload);
            // 设置消息的服务质量
            message.setQos(0);
            // 发布消息
            if (client.isConnected()) {
            	 client.publish(topic, message);
			}else {
				client.connect(connOpts);
				client.publish(topic, message);
			}
           
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}