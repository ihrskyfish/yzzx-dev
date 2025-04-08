package com.wocai.platform.modules.quartz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.system.query.QueryGenerator;
import com.wocai.platform.modules.quartz.entity.QuartzJob;
import com.wocai.platform.modules.quartz.service.IQuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Description: 定时任务在线管理
 * @Date: 2019-01-02
 * @Version:V1.0
 */
@RestController
@RequestMapping("/sys/quartzJob")
@Slf4j
@Api(tags = "定时任务接口")
@ApiIgnore
public class QuartzJobController extends BaseController<QuartzJob, IQuartzJobService> {
    @Autowired
    private IQuartzJobService quartzJobService;
    @Autowired
    private Scheduler scheduler;

    /**
     * 分页列表查询
     *
     * @param quartzJob
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<QuartzJob>> queryPageList(QuartzJob quartzJob, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<QuartzJob>> result = new Result<IPage<QuartzJob>>();
        QueryWrapper<QuartzJob> queryWrapper = QueryGenerator.initQueryWrapper(quartzJob, req.getParameterMap());
        Page<QuartzJob> page = new Page<QuartzJob>(pageNo, pageSize);
        IPage<QuartzJob> pageList = quartzJobService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加定时任务
     *
     * @param quartzJob
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody QuartzJob quartzJob) {
        Result<QuartzJob> result = new Result<QuartzJob>();

        List<QuartzJob> list = quartzJobService.findByJobClassName(quartzJob.getJobClassName());
        if (list != null && list.size() > 0) {
            return Result.error("该定时任务类名已存在");
        }
        try {
            boolean ok = quartzJobService.saveAndScheduleJob(quartzJob);
            if (ok) {
                result.success("创建定时任务成功");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("创建定时任务失败，" + e.getMessage());
        }
        return result;
    }

    /**
     * 更新定时任务
     *
     * @param quartzJob
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result<?> eidt(@RequestBody QuartzJob quartzJob) {
        Result<QuartzJob> result = new Result<QuartzJob>();
        QuartzJob quartzJobEntity = quartzJobService.getById(quartzJob.getId());
        if (quartzJobEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = true;
            try {
                ok = quartzJobService.editAndScheduleJob(quartzJob);
            } catch (SchedulerException e) {
                log.error(e.getMessage(), e);
                return Result.error("更新定时任务失败!");
            }
            if (ok) {
                result.success("更新定时任务成功!");
            }
        }
        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<QuartzJob> delete(@RequestParam(name = "id", required = true) String id) {
        Result<QuartzJob> result = new Result<QuartzJob>();
        QuartzJob quartzJob = quartzJobService.getById(id);
        if (quartzJob == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = quartzJobService.deleteAndStopJob(quartzJob);
            if (ok) {
                result.success("删除成功!");
            }
        }

        return result;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    public Result<QuartzJob> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<QuartzJob> result = new Result<QuartzJob>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            for (String id : Arrays.asList(ids.split(","))) {
                QuartzJob job = quartzJobService.getById(id);
                quartzJobService.deleteAndStopJob(job);
            }
            result.success("删除定时任务成功!");
        }
        return result;
    }

    /**
     * 暂停定时任务
     *
     * @return
     */
    @GetMapping(value = "/pause")
    @ApiOperation(value = "暂停定时任务")
    public Result<Object> pauseJob(@RequestParam(name = "jobClassName", required = true) String jobClassName) {
        QuartzJob job = null;
        try {
            job = quartzJobService.getOne(new LambdaQueryWrapper<QuartzJob>().eq(QuartzJob::getJobClassName, jobClassName));
            if (job == null) {
                return Result.error("定时任务不存在！");
            }
            scheduler.pauseJob(JobKey.jobKey(jobClassName.trim()));
        } catch (SchedulerException e) {
            throw new BizException("暂停定时任务失败");
        }
        job.setStatus(CommonConstant.STATUS_DISABLE);
        quartzJobService.updateById(job);
        return Result.ok("暂停定时任务成功");
    }

    /**
     * 启动定时任务
     *
     * @param jobClassName
     * @return
     */
    @GetMapping(value = "/resume")
    @ApiOperation(value = "恢复定时任务")
    public Result<Object> resumeJob(@RequestParam(name = "jobClassName", required = true) String jobClassName) {
        QuartzJob job = quartzJobService.getOne(new LambdaQueryWrapper<QuartzJob>().eq(QuartzJob::getJobClassName, jobClassName));
        if (job == null) {
            return Result.error("定时任务不存在！");
        }
        quartzJobService.resumeJob(job);
        //scheduler.resumeJob(JobKey.jobKey(job.getJobClassName().trim()));
        return Result.ok("恢复定时任务成功");
    }

    /**
     * 执行一次任务
     *
     * @return
     */
    @GetMapping(value = "/execOne")
    @ApiOperation(value = "执行单次任务")
    public Result<Object> execOne(@RequestParam(name = "jobClassName", required = true) String jobClassName) {
        QuartzJob job;
        try {
            job = quartzJobService.getOne(new LambdaQueryWrapper<QuartzJob>().eq(QuartzJob::getJobClassName, jobClassName));
            if (job == null) {
                return Result.error("定时任务不存在！");
            }
            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobClassName)
                    .startAt(new Date())
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(3)
                                    .withRepeatCount(0))//重复执行的次数，因为加入任务的时候马上执行了，所以不需要重复，否则会多一次。
                    .build();
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName).usingJobData("parameter", job.getParameter()).build();

            StdSchedulerFactory sf =new StdSchedulerFactory();
            Properties props = new Properties();
            props.put("org.quartz.scheduler.instanceName","MyOneScheduler");
            props.put("org.quartz.threadPool.threadCount","20");
            sf.initialize(props);
            Scheduler scheduler1 = sf.getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(jobDetail,simpleTrigger);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("执行单次任务失败");
        }
        return Result.ok("执行单次任务");
    }
    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Result<QuartzJob> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<QuartzJob> result = new Result<QuartzJob>();
        QuartzJob quartzJob = quartzJobService.getById(id);
        if (quartzJob == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(quartzJob);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response, QuartzJob quartzJob) {
        return super.exportXls(request, quartzJob, QuartzJob.class, "定时任务列表");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, QuartzJob.class);
    }

    private static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }
}
