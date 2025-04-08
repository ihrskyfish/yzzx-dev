package com.wocai.platform.modules.app.health.controller;

import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.health.dto.AppHealthBabyDto;
import com.wocai.platform.modules.app.health.dto.AppHealthClockDto;
import com.wocai.platform.modules.app.health.service.AppHealthService;
import com.wocai.platform.modules.app.health.vo.AppHealthVo;
import com.wocai.platform.modules.business.health.dto.MmccHealthCheckReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 09:21
 **/
@Slf4j
@Api(tags="孕期健康")
@RestController
@RequestMapping("/app/health")
public class AppHealthController {

    @Autowired
    private AppHealthService appHealthService;

    @AutoLog(value = "打卡", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "打卡", notes = "打卡")
    @PostMapping(value = "/clock")
    public Result clock(@RequestBody AppHealthClockDto appHealthClockDto, ReqUser reqUser) {
        appHealthService.clock(appHealthClockDto,reqUser);
        return Result.toSuccess("打卡成功!");
    }

//    @AutoLog(value = "宝宝B超上传", recordFlag = CommonConstant.STATUS_1)
//    @ApiOperation(value = "宝宝B超上传", notes = "宝宝B超上传")
//    @PostMapping(value = "/babyUpload")
    public Result babyUpload(@RequestBody AppHealthBabyDto appHealthBabyDto, ReqUser reqUser) {
        appHealthService.babyUpload(appHealthBabyDto,reqUser);
        return Result.toSuccess("上传成功!");
    }


    @AutoLog(value = "今日打卡详情", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "今日打卡详情", notes = "今日打卡详情")
    @PostMapping(value = "/dayClockDetails")
    public Result<AppHealthVo> dayClockDetails(ReqUser reqUser) {
        AppHealthVo result=appHealthService.dayClockDetails(reqUser);
        return Result.toSuccess(result);
    }



}
