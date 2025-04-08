package com.wocai.platform.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wocai.platform.common.system.base.entity.BaseEntity;
import com.wocai.platform.common.util.SpringContextUtils;
import com.wocai.platform.modules.app.vx.dto.VxSendUserMessageDto;
import com.wocai.platform.modules.app.vx.service.wxServiceImpl;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import com.wocai.platform.modules.business.display.mapper.MmccServiceDisplayMapper;
import com.wocai.platform.modules.business.display.mapper.MmccServiceUserMapper;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Slf4j
public class VxSendMessageJob implements Job {

    private UserMapper userMapper =  SpringContextUtils.getBean(UserMapper.class);
    private MmccServiceUserMapper mmccServiceUserMapper = SpringContextUtils.getBean(MmccServiceUserMapper.class);
    private wxServiceImpl wxService  = SpringContextUtils.getBean(wxServiceImpl.class);
    private MmccServiceDisplayMapper mmccServiceDisplayMapper  = SpringContextUtils.getBean(MmccServiceDisplayMapper.class);


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        log.info("定时任务开启--------------------");

        LambdaQueryWrapper<MmccServiceUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(MmccServiceUser::getDelFlag,0);
        userLambdaQueryWrapper.isNotNull(MmccServiceUser::getUserId);
        userLambdaQueryWrapper.eq(MmccServiceUser::getSendMessage,"0");

        List<MmccServiceUser> mmccServiceUsers = mmccServiceUserMapper.selectList(userLambdaQueryWrapper);
        for (MmccServiceUser mmccServiceUser : mmccServiceUsers) {
            Date visitingTime = mmccServiceUser.getVisitingTime();
            LocalDateTime localVitstingTime = visitingTime.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            long minutesDiff = ChronoUnit.MINUTES.between(LocalDateTime.now(),localVitstingTime);

            if (minutesDiff >= 0 && minutesDiff <= 30){
                System.out.println("当前时间在指定时间的30分钟内");

                String userId = mmccServiceUser.getUserId();
                LambdaQueryWrapper<User> userMapperLambdaQueryWrapper = new LambdaQueryWrapper<>();
                userMapperLambdaQueryWrapper.eq(BaseEntity::getId,userId);
                User user = userMapper.selectOne(userMapperLambdaQueryWrapper);

                VxSendUserMessageDto vxSendUserMessageDto = new VxSendUserMessageDto();
                vxSendUserMessageDto.setOpenid(user.getOpenid());
                vxSendUserMessageDto.setTemplateId("JBJ3DSkxabYCw1idcDsIOx4vPlsgK4PoMmr80Pljt6w");
                vxSendUserMessageDto.setType("3");
                String[] keywords = new String[4];
                String pattern="yyyy-MM-dd HH:mm";
                SimpleDateFormat sdf= new SimpleDateFormat(pattern);
                String datestr = sdf.format(visitingTime);// format  为格式化方法
                MmccServiceDisplay mmccServiceDisplay = mmccServiceDisplayMapper.selectById(mmccServiceUser.getDisplayId());
                keywords[0] = datestr;
                keywords[1] = "距离您的预约还有30分钟~";
                keywords[2] = mmccServiceDisplay.getName();
                vxSendUserMessageDto.setKeywords(keywords);

                String access_token = wxService.getAccess_token("wx25ec10509ca5f5b4", "f204a8dff887ccaa940be992431e9b45");
                wxService.pushOneUser(access_token,vxSendUserMessageDto);
                mmccServiceUser.setSendMessage("1");
                mmccServiceUserMapper.updateById(mmccServiceUser);
            }
        }

    }
}
