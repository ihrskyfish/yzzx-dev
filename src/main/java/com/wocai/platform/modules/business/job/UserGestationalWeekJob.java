package com.wocai.platform.modules.business.job;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wocai.platform.common.util.SpringContextUtils;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import com.wocai.platform.modules.business.activity.mapper.MmccActivityMapper;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseDetailsMapper;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import com.wocai.platform.modules.system.service.ISysQueueLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-07-12 10:35
 **/
@Slf4j
public class UserGestationalWeekJob implements Job {
    private UserMapper userMapper = SpringContextUtils.getBean(UserMapper.class);

    private MmccActivityMapper mmccActivityMapper=SpringContextUtils.getBean(MmccActivityMapper.class);

    //0 1 0 * * ? * 每天执行
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("定时任务开启--------------------");
        LocalDate now = LocalDate.now().minus(7, ChronoUnit.DAYS);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getDelFlag,0);
        userLambdaQueryWrapper.isNotNull(User::getGestationalWeek);
        userLambdaQueryWrapper.le(User::getGestationalTime,now);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        for (User user : users) {
            if (user.getGestationalWeek()!=null){
                //最多42周
                if (user.getGestationalWeek()<=42){
                    user.setGestationalWeek(user.getGestationalWeek()+1);
                    user.setGestationalTime(new Date());
                    userMapper.updateById(user);
                }
            }
        }

        //截止日期
        LambdaQueryWrapper<MmccActivity> mmccActivityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mmccActivityLambdaQueryWrapper.eq(MmccActivity::getDelFlag,0);
        mmccActivityLambdaQueryWrapper.le(MmccActivity::getEndTime,LocalDate.now());
        mmccActivityLambdaQueryWrapper.eq(MmccActivity::getStatus,10);
        List<MmccActivity> mmccActivities = mmccActivityMapper.selectList(mmccActivityLambdaQueryWrapper);
        if (mmccActivities!=null && mmccActivities.size()>0){
            for (MmccActivity mmccActivity : mmccActivities) {
                mmccActivity.setStatus("20");
                mmccActivity.setEndState("1");
                mmccActivityMapper.updateById(mmccActivity);
            }
        }

    }
}
