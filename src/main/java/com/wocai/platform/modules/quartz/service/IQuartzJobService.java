package com.wocai.platform.modules.quartz.service;

import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.quartz.entity.QuartzJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @Description: 定时任务在线管理
 * @Date: 2019-04-28
 * @Version: V1.1
 */
public interface IQuartzJobService extends BaseService<QuartzJob> {

    List<QuartzJob> findByJobClassName(String jobClassName);

    boolean saveAndScheduleJob(QuartzJob quartzJob);

    boolean editAndScheduleJob(QuartzJob quartzJob) throws SchedulerException;

    boolean deleteAndStopJob(QuartzJob quartzJob);

    boolean resumeJob(QuartzJob quartzJob);
}
