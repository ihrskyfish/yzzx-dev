package com.wocai.platform.modules.quartz.service.impl;

import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;
import com.wocai.platform.modules.quartz.entity.QuartzJob;
import com.wocai.platform.modules.quartz.mapper.QuartzJobMapper;
import com.wocai.platform.modules.quartz.service.IQuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 定时任务在线管理
 * @Date: 2019-04-28
 * @Version: V1.1
 */
@Slf4j
@Service
public class QuartzJobServiceImpl extends BaseServiceImpl<QuartzJobMapper, QuartzJob> implements IQuartzJobService {
    @Resource
    private QuartzJobMapper quartzJobMapper;
    @Autowired
    private Scheduler scheduler;

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {
        return quartzJobMapper.findByJobClassName(jobClassName);
    }

    /**
     * 保存&启动定时任务
     */
    @Override
    public boolean saveAndScheduleJob(QuartzJob quartzJob) {
        if (CommonConstant.STATUS_NORMAL.equals(quartzJob.getStatus())) {
            // 定时器添加
            this.schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter());
        }
        // DB设置修改
        quartzJob.setDelFlag(CommonConstant.DEL_FLAG_0);
        return this.save(quartzJob);
    }

    /**
     * 恢复定时任务
     */
    @Override
    public boolean resumeJob(QuartzJob quartzJob) {
        schedulerDelete(quartzJob.getJobClassName().trim());
        schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter());
        quartzJob.setStatus(CommonConstant.STATUS_NORMAL);
        return this.updateById(quartzJob);
    }

    /**
     * 编辑&启停定时任务
     * @throws SchedulerException
     */
    @Override
    public boolean editAndScheduleJob(QuartzJob quartzJob) throws SchedulerException {
        if (CommonConstant.STATUS_NORMAL.equals(quartzJob.getStatus())) {
            schedulerDelete(quartzJob.getJobClassName().trim());
            schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter());
        }else{
            scheduler.pauseJob(JobKey.jobKey(quartzJob.getJobClassName().trim()));
        }
        return this.updateById(quartzJob);
    }

    /**
     * 删除&停止删除定时任务
     */
    @Override
    public boolean deleteAndStopJob(QuartzJob job) {
        schedulerDelete(job.getJobClassName().trim());
        boolean ok = this.removeById(job.getId());
        return ok;
    }

    /**
     * 添加定时任务
     *
     * @param jobClassName
     * @param cronExpression
     * @param parameter
     */
    private void schedulerAdd(String jobClassName, String cronExpression, String parameter) {
        try {
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName).usingJobData("parameter", parameter).build();

            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new BizException("创建定时任务失败", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException("后台找不到该类名：" + jobClassName, e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobClassName
     */
    private void schedulerDelete(String jobClassName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException("删除定时任务失败");
        }
    }

    private static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }

}
