package com.wocai.platform.queue.handler;

import com.wocai.platform.common.queue.EventModel;
import com.wocai.platform.common.queue.EventType;
import com.wocai.platform.common.queue.handler.BaseEventHandler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @文件名: TestEventHandler
 * @包名 com.wocai.provider.robot.core.provider.queue.handler
 * @描述:
 * @创建者 linwq
 * @创建时间 2019/4/29 2:27 PM
 */
@Component
public class TestEventHandler extends BaseEventHandler {

    @Override
    public void doHandle(EventModel eventModel) {
        System.out.println("异步操作进行中.....");
        System.out.println(eventModel.getExtraInfo());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.doHandle(eventModel);
    }

    @Override
    public List<EventType> getSupportedEvents() {
        return Arrays.asList(EventType.EVENT_TEST);
    }
}
