package com.wocai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableSwagger2
@SpringBootApplication
//@EnableScheduling
public class RestApiApplication {

    public static void main(String[] args) throws UnknownHostException {
        //System.setProperty("spring.devtools.restart.enabled", "true");

        ConfigurableApplicationContext application = SpringApplication.run(RestApiApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Spring Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "swagger-ui: \t http://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");

    }

//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setKeepAliveInterval(4);
//        options.setConnectionTimeout(5000);
//        options.setServerURIs(new String[]{"tcp://47.114.133.102:1883"});
//        options.setUserName("wocai");
//        options.setPassword("admin".toCharArray());
//        factory.setConnectionOptions(options);
//        return factory;
//    }
//
//    @Bean
//    public MessageChannel messageChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageProducer inbound() {
//        //String[] topic = {"event/#","status/#","data/#"};
//        String[] topic = {"status/#","data/#"};
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(String.valueOf(System.currentTimeMillis()), mqttClientFactory(),topic);
//        adapter.setCompletionTimeout(5000);//超时
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(2);
//        adapter.setOutputChannel(messageChannel());//设置通道
//        return adapter;
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "messageChannel")
//    public MessageHandler mqttOutbound() {
//        MqttPahoMessageHandler messageHandler =  new MqttPahoMessageHandler(String.valueOf(System.currentTimeMillis()), mqttClientFactory());
//        messageHandler.setAsync(true);
//        return messageHandler;
//    }

}