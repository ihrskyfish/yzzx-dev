package com.wocai.platform.executor;

import com.wocai.platform.modules.business.demo.service.ITestDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 测试异步线程池
 * @Author: linwq
 * @CreateTime: 2021/9/27
 */
@Slf4j
@Component
public class TestExecutor {

    private int poolSize = 10;

    private ExecutorService threadPool;

    @Autowired
    private ITestDemoService testDemoService;

    @PostConstruct
    public void initialize() {
        threadPool = Executors.newFixedThreadPool(poolSize, r -> {
            Thread thread = new Thread(r);
            thread.setName(String.format("SyncFsuExecutor thread-id=%d", thread.getId()));
            return thread;
        });
    }

    public void execute(String id) {
        threadPool.execute(() -> {
            try {
                testDemoService.getMainById(id);
            } catch (Exception e) {
                log.error("FSU设备同步失败:{}", e);
            }
        });
    }

//    private int poolSize = 10;
//
//    private ExecutorService threadPool;
//
//    @Autowired
//    private ITestDemoService testDemoService;
//
//    @PostConstruct
//    public void initialize() {
//        threadPool = Executors.newFixedThreadPool(poolSize, r -> {
//            Thread thread = new Thread(r);
//            thread.setName(String.format("SyncFsuExecutor thread-id=%d", thread.getId()));
//            return thread;
//        });
//    }
//
//    public void execute(String id) {
//        threadPool.execute(() -> {
//            try {
//                testDemoService.getMainById(id);
//            } catch (Exception e) {
//                log.error("FSU设备同步失败:{}", e);
//            }
//        });
//    }
}
