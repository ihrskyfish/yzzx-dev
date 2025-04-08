package com.wocai.platform.common.feign.config;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.wocai.platform.common.feign.callback.HandleShellCallBack;
import com.wocai.platform.common.feign.client.HandleShellClient;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: HandleShellConfig
 * @Author: wzw
 * @Description:
 * @Date: 2021/11/8 10:10 下午
 * @Version: 1.0
 */
@Configuration
public class HandleShellConfig {
    public static final String apiAddress = "http://127.0.0.1:9999/handle-shell";

    @Bean
    HandleShellClient handleShellClient() {
        HandleShellClient client = (HandleShellClient) HystrixFeign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder()).setterFactory((target, method) -> {
            return (new SetterFactory.Default()).create(target, method).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter().withCoreSize(500)).andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter().withExecutionTimeoutInMilliseconds(10000));
        }).logLevel(feign.Logger.Level.FULL).logger(new Slf4jLogger(HandleShellClient.class)).target(HandleShellClient.class, apiAddress, new HandleShellCallBack());
        return client;
    }

}
