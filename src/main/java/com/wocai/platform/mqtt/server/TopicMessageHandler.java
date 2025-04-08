package com.wocai.platform.mqtt.server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

/**
 *
 * @author maojl
 *
 */
@Service
@Slf4j
public class TopicMessageHandler implements MessageHandler {
	@Autowired
	private EventDataHandler eventDataHandler;
	@Autowired
	private HeartDataHander heartDataHander;
	@ServiceActivator(inputChannel = "messageChannel")
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		if (message != null) {
            String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
            String info = message.getPayload().toString();
            log.info("topic:{}", topic);
            if (topic.startsWith("data")) {
				eventDataHandler.doEvent(info);
			}
            if (topic.startsWith("status")) {
				heartDataHander.doLogHealth(info, topic);
			} else {
				//log.info(info);
			}
        }
	}

}
