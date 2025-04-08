package com.wocai.platform.modules.quartz.job;

import com.wocai.platform.common.util.SpringContextUtils;
import com.wocai.platform.modules.system.service.ISysQueueLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 重试队列
 */
@Slf4j
public class AutoRetryQueueJob implements Job {

    private ISysQueueLogService sysQueueLogService = SpringContextUtils.getBean(ISysQueueLogService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        sysQueueLogService.autoRetryQueueLogService();
    }
}
