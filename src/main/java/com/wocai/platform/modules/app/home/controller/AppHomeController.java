package com.wocai.platform.modules.app.home.controller;

import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.home.service.AppHomeService;
import com.wocai.platform.modules.app.home.vo.AppProcessColumnVo;
import com.wocai.platform.modules.app.home.vo.AppRotationChartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 16:53
 **/

@Slf4j
@Api(tags="首页")
@RestController
@RequestMapping("/app/home")
public class AppHomeController {

    @Autowired
    private AppHomeService appHomeService;

    @AutoLog(value = "工艺栏列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "工艺栏列表", notes = "工艺栏列表")
    @GetMapping(value = "/processColumnList")
    public Result<List<AppProcessColumnVo>> processColumnList(){
        List<AppProcessColumnVo> result=appHomeService.processColumnList();
        return Result.toSuccess(result);
    }

    @AutoLog(value = "轮播图列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "轮播图列表", notes = "轮播图列表")
    @GetMapping(value = "/rotationChartList")
    public Result<List<AppRotationChartVo>> rotationChartList(){
        List<AppRotationChartVo> result=appHomeService.rotationChartList();
        return Result.toSuccess(result);
    }


    @AutoLog(value = "工艺栏详情", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "工艺栏详情", notes = "工艺栏详情")
    @GetMapping(value = "/processColumnQueryById")
    public Result<AppProcessColumnVo> processColumnQueryById(@RequestParam(name = "id", required = true) String id ){
       AppProcessColumnVo result=appHomeService.processColumnQueryById(id);
        return Result.toSuccess(result);
    }
}
