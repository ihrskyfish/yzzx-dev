package com.wocai.platform.modules.app.health.service.impl;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.modules.app.health.dto.AppHealthBabyDto;
import com.wocai.platform.modules.app.health.dto.AppHealthClockDto;
import com.wocai.platform.modules.app.health.service.AppHealthService;
import com.wocai.platform.modules.app.health.vo.AppHealthVo;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.baby.mapper.MmccBabyUltrasoundMapper;
import com.wocai.platform.modules.business.health.entity.MmccHealthCheck;
import com.wocai.platform.modules.business.health.mapper.MmccHealthCheckMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 09:21
 **/
@Service
public class AppHealthServiceImpl implements AppHealthService {

    @Resource
    private MmccHealthCheckMapper mmccHealthCheckMapper;

    @Resource
    private MmccBabyUltrasoundMapper mmccBabyUltrasoundMapper;


    @Override
    public void clock(AppHealthClockDto appHealthClockDto, ReqUser reqUser) {
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();

        MmccHealthCheck mmccHealthCheck = mmccHealthCheckMapper.selectOne(
                Wrappers.<MmccHealthCheck>lambdaQuery()
                        .eq(MmccHealthCheck::getDelFlag, 0)
                        .eq(MmccHealthCheck::getUserId, reqUser.getUserId())
                        .eq(MmccHealthCheck::getYear, year)
                        .eq(MmccHealthCheck::getMonth, month)
                        .eq(MmccHealthCheck::getDay, day)
                        .last("limit 1"));
        if (mmccHealthCheck != null) {
            throw new BizException("今日已打卡，请勿重复打卡");
        }

        mmccHealthCheck = new MmccHealthCheck();
        BeanUtils.copyProperties(appHealthClockDto, mmccHealthCheck);
        mmccHealthCheck.setUserId(reqUser.getUserId());
        mmccHealthCheck.setYear(year);
        mmccHealthCheck.setMonth(month);
        mmccHealthCheck.setDay(day);


        //获取上一天的年月日 查看连续天数
        LocalDateTime yesterday = localDateTime.minusDays(1); //获取前一天的日期
        int yestYear = yesterday.getYear(); //年
        int yestMonth = yesterday.getMonthValue(); //月
        int yestDay = yesterday.getDayOfMonth(); //日
        MmccHealthCheck yestMmccHealthCheck = mmccHealthCheckMapper.selectOne(
                Wrappers.<MmccHealthCheck>lambdaQuery()
                        .eq(MmccHealthCheck::getDelFlag, 0)
                        .eq(MmccHealthCheck::getUserId, reqUser.getUserId())
                        .eq(MmccHealthCheck::getYear, yestYear)
                        .eq(MmccHealthCheck::getMonth, yestMonth)
                        .eq(MmccHealthCheck::getDay, yestDay)
                        .last("limit 1"));
        if (yestMmccHealthCheck == null) {
            yestMmccHealthCheck=new MmccHealthCheck();
            yestMmccHealthCheck.setMoodContinuousDays(0);
            yestMmccHealthCheck.setContinuousDays(0);
            mmccHealthCheck.setMoodContinuousDays(0);
            mmccHealthCheck.setContinuousDays(0);
        }
        if (StringUtils.isNotEmpty(appHealthClockDto.getMood())){
            if (yestMmccHealthCheck.getMoodContinuousDays()==null){
                mmccHealthCheck.setMoodContinuousDays(1);
            }else {
                mmccHealthCheck.setMoodContinuousDays(yestMmccHealthCheck.getMoodContinuousDays() + 1);
            }
        }
        if (appHealthClockDto.getBust()!=null
                || appHealthClockDto.getWaist()!=null
                || appHealthClockDto.getWeight()!=null
                || appHealthClockDto.getHip()!=null){
            mmccHealthCheck.setContinuousDays(yestMmccHealthCheck.getContinuousDays() + 1);
        }
        mmccHealthCheckMapper.insert(mmccHealthCheck);


        List<String> urls = appHealthClockDto.getUrls();
        MmccBabyUltrasound yesterMmccBabyUltrasound = mmccBabyUltrasoundMapper.selectOne(
                Wrappers.<MmccBabyUltrasound>lambdaQuery()
                        .eq(MmccBabyUltrasound::getDelFlag, 0)
                        .eq(MmccBabyUltrasound::getUserId, reqUser.getUserId())
                        .eq(MmccBabyUltrasound::getYear, yestYear)
                        .eq(MmccBabyUltrasound::getMonth, yestMonth)
                        .eq(MmccBabyUltrasound::getDay, yestDay)
                        .orderByDesc(MmccBabyUltrasound::getCreateTime)
                        .last("limit 1"));
        for (String url : urls) {
            MmccBabyUltrasound mmccBabyUltrasound = new MmccBabyUltrasound();
            mmccBabyUltrasound.setFileUrl(url);
            mmccBabyUltrasound.setUserId(reqUser.getUserId());
            mmccBabyUltrasound.setYear(year);
            mmccBabyUltrasound.setMonth(month);
            mmccBabyUltrasound.setDay(day);
            if (yesterMmccBabyUltrasound == null) {
                mmccBabyUltrasound.setContinuousDays(1);
            } else {
                mmccBabyUltrasound.setContinuousDays(yesterMmccBabyUltrasound.getContinuousDays() + 1);
            }
            mmccBabyUltrasoundMapper.insert(mmccBabyUltrasound);
        }


    }

    @Override
    public void babyUpload(AppHealthBabyDto appHealthBabyDto, ReqUser reqUser) {
        List<String> urls = appHealthBabyDto.getUrls();
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();


        //获取上一天的连续时间
        LocalDateTime yesterday = localDateTime.minusDays(1); //获取前一天的日期
        int yestYear = yesterday.getYear(); //年
        int yestMonth = yesterday.getMonthValue(); //月
        int yestDay = yesterday.getDayOfMonth(); //日
        MmccBabyUltrasound yesterMmccBabyUltrasound = mmccBabyUltrasoundMapper.selectOne(
                Wrappers.<MmccBabyUltrasound>lambdaQuery()
                        .eq(MmccBabyUltrasound::getDelFlag, 0)
                        .eq(MmccBabyUltrasound::getUserId, reqUser.getUserId())
                        .eq(MmccBabyUltrasound::getYear, yestYear)
                        .eq(MmccBabyUltrasound::getMonth, yestMonth)
                        .eq(MmccBabyUltrasound::getDay, yestDay)
                        .orderByDesc(MmccBabyUltrasound::getCreateTime)
                        .last("limit 1"));

        for (String url : urls) {
            MmccBabyUltrasound mmccBabyUltrasound = new MmccBabyUltrasound();
            mmccBabyUltrasound.setFileUrl(url);
            mmccBabyUltrasound.setUserId(reqUser.getUserId());
            mmccBabyUltrasound.setYear(year);
            mmccBabyUltrasound.setMonth(month);
            mmccBabyUltrasound.setDay(day);
            if (yesterMmccBabyUltrasound == null) {
                mmccBabyUltrasound.setContinuousDays(0);
            } else {
                mmccBabyUltrasound.setContinuousDays(yesterMmccBabyUltrasound.getContinuousDays() + 1);
            }
            mmccBabyUltrasoundMapper.insert(mmccBabyUltrasound);
        }

    }

    @Override
    public AppHealthVo dayClockDetails(ReqUser reqUser) {
        AppHealthVo result = new AppHealthVo();
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        MmccHealthCheck mmccHealthCheck = mmccHealthCheckMapper.selectOne(
                Wrappers.<MmccHealthCheck>lambdaQuery()
                        .eq(MmccHealthCheck::getDelFlag, 0)
                        .eq(MmccHealthCheck::getUserId, reqUser.getUserId())
                        .eq(MmccHealthCheck::getYear, year)
                        .eq(MmccHealthCheck::getMonth, month)
                        .eq(MmccHealthCheck::getDay, day)
                        .last("limit 1"));
        if (mmccHealthCheck == null) {
            result.setHealthClock(false);

            LocalDateTime yesterday = localDateTime.minusDays(1); //获取前一天的日期
            int yestYear = yesterday.getYear(); //年
            int yestMonth = yesterday.getMonthValue(); //月
            int yestDay = yesterday.getDayOfMonth(); //日
            MmccHealthCheck yestMmccHealthCheck = mmccHealthCheckMapper.selectOne(
                    Wrappers.<MmccHealthCheck>lambdaQuery()
                            .eq(MmccHealthCheck::getDelFlag, 0)
                            .eq(MmccHealthCheck::getUserId, reqUser.getUserId())
                            .eq(MmccHealthCheck::getYear, yestYear)
                            .eq(MmccHealthCheck::getMonth, yestMonth)
                            .eq(MmccHealthCheck::getDay, yestDay)
                            .last("limit 1"));
            if (yestMmccHealthCheck == null) {
                result.setMoodContinuousDays(0);
                result.setHealthContinuousDays(0);
            } else {
                result.setMoodContinuousDays(yestMmccHealthCheck.getMoodContinuousDays());
                result.setHealthContinuousDays(yestMmccHealthCheck.getContinuousDays());
            }
        } else {
            BeanUtils.copyProperties(mmccHealthCheck, result);
            result.setHealthContinuousDays(mmccHealthCheck.getContinuousDays());
            result.setHealthClock(true);
        }

        //查询宝宝B超是否上传
        MmccBabyUltrasound mmccBabyUltrasound = mmccBabyUltrasoundMapper.selectOne(
                Wrappers.<MmccBabyUltrasound>lambdaQuery()
                        .eq(MmccBabyUltrasound::getDelFlag, 0)
                        .eq(MmccBabyUltrasound::getUserId, reqUser.getUserId())
                        .eq(MmccBabyUltrasound::getYear, year)
                        .eq(MmccBabyUltrasound::getMonth, month)
                        .eq(MmccBabyUltrasound::getDay, day)
                        .orderByDesc(MmccBabyUltrasound::getCreateTime)
                        .last("limit 1"));
        if (mmccBabyUltrasound == null) {
            result.setBabyClock(false);

            LocalDateTime yesterday = localDateTime.minusDays(1); //获取前一天的日期
            int yestYear = yesterday.getYear(); //年
            int yestMonth = yesterday.getMonthValue(); //月
            int yestDay = yesterday.getDayOfMonth(); //日
            MmccBabyUltrasound yesterMmccBabyUltrasound = mmccBabyUltrasoundMapper.selectOne(
                    Wrappers.<MmccBabyUltrasound>lambdaQuery()
                            .eq(MmccBabyUltrasound::getDelFlag, 0)
                            .eq(MmccBabyUltrasound::getUserId, reqUser.getUserId())
                            .eq(MmccBabyUltrasound::getYear, yestYear)
                            .eq(MmccBabyUltrasound::getMonth, yestMonth)
                            .eq(MmccBabyUltrasound::getDay, yestDay)
                            .orderByDesc(MmccBabyUltrasound::getCreateTime)
                            .last("limit 1"));
            if (yesterMmccBabyUltrasound == null) {
                result.setBabyContinuousDays(0);
            } else {
                result.setBabyContinuousDays(yesterMmccBabyUltrasound.getContinuousDays());
            }
        } else {
            List<String> urls = mmccBabyUltrasoundMapper.selectList(
                    Wrappers.<MmccBabyUltrasound>lambdaQuery()
                            .eq(MmccBabyUltrasound::getDelFlag, 0)
                            .eq(MmccBabyUltrasound::getUserId, reqUser.getUserId())
                            .eq(MmccBabyUltrasound::getYear, year)
                            .eq(MmccBabyUltrasound::getMonth, month)
                            .eq(MmccBabyUltrasound::getDay, day))
                    .stream()
                    .map(item -> {
                        String url = "";
                        if (StringUtils.isNotEmpty(item.getFileUrl())) {
                            url = ServerConfig.plat_upload_head_path + "/" + item.getFileUrl();
                        }
                        return url;
                    }).collect(Collectors.toList());
            //总上传天数
            Integer babyContinuousDays=mmccBabyUltrasoundMapper.getAllDays(reqUser.getUserId());
            result.setBabyContinuousDays(babyContinuousDays);

            result.setUrls(urls);
            result.setBabyClock(true);
            result.setBabyContinuousDays(mmccBabyUltrasound.getContinuousDays());
        }
        return result;
    }
}
