package com.wocai.platform.modules.app.display.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.aspect.annotation.NoLogin;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.display.dto.AppCancellationDto;
import com.wocai.platform.modules.app.display.dto.AppDisplayBookDto;
import com.wocai.platform.modules.app.display.dto.AppDisplayEvaluateVo;
import com.wocai.platform.modules.app.display.service.AppDisplayService;
import com.wocai.platform.modules.app.display.vo.AppDisplayBookQueryByIdVo;
import com.wocai.platform.modules.app.display.vo.AppDisplayListVo;
import com.wocai.platform.modules.app.display.vo.AppDisplayMyBookListVo;
import com.wocai.platform.modules.app.display.vo.AppDisplayQueryByIdVo;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayRes;
import com.wocai.platform.modules.business.display.vo.MmccServocePersonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 16:54
 **/

@Slf4j
@Api(tags="宣传预定")
@RestController
@RequestMapping("/app/display")
public class AppDisplayController {

    @Autowired
    private AppDisplayService appDisplayService;



    @AutoLog(value = "服务展示列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "服务展示列表", notes = "服务展示列表")
    @GetMapping(value = "/list")
    @NoLogin
    public Result<List<AppDisplayListVo>> queryPageList(ReqUser reqUser) {
//    public Result<List<AppDisplayListVo>> queryPageList() {
        List<AppDisplayListVo> pageList = appDisplayService.queryPageList(reqUser);
//        List<AppDisplayListVo> pageList = appDisplayService.queryPageList();
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "根据id查看详情", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "根据id查看详情", notes = "根据id查看详情")
    @GetMapping(value = "/queyById")
    public Result<AppDisplayQueryByIdVo> queyById(@RequestParam(value = "id", required = true) String id,ReqUser reqUser) {
        AppDisplayQueryByIdVo result = appDisplayService.queyById(id,reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "取消预订", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "取消预订", notes = "取消预订")
    @PostMapping(value = "/cancellation")
    public Result cancellation(@RequestBody AppCancellationDto appCancellationDto, ReqUser reqUser) {
        appDisplayService.cancellation(appCancellationDto,reqUser);
        return Result.toSuccess("取消预订成功");
    }

    @AutoLog(value = "预订", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "预订", notes = "预订")
    @PostMapping(value = "/book")
    public Result book(@RequestBody AppDisplayBookDto appDisplayBookDto,ReqUser reqUser) {
        appDisplayService.book(appDisplayBookDto,reqUser);
        return Result.toSuccess("预订成功");
    }


    @AutoLog(value = "我的预订", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "我的预订", notes = "我的预订")
    @PostMapping(value = "/mybookList")
    public Result<List<AppDisplayMyBookListVo>> mybookList(ReqUser reqUser) {
        List<AppDisplayMyBookListVo> result=appDisplayService.mybookList(reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "评价", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "评价", notes = "评价")
    @PostMapping(value = "/evaluate")
    public Result evaluate(@RequestBody AppDisplayEvaluateVo appDisplayEvaluateVo) {
        appDisplayService.evaluate(appDisplayEvaluateVo);
        return Result.ok("评价成功");
    }


    @AutoLog(value = "查看预定信息", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "查看预定信息", notes = "查看预定信息")
    @GetMapping(value = "/queyByServiceUserId")
    public Result<AppDisplayBookQueryByIdVo> queyByServiceUserId(@RequestParam(value = "serviceUserId", required = true) String serviceUserId) {
        AppDisplayBookQueryByIdVo result = appDisplayService.queyByServiceUserId(serviceUserId);
        return Result.toSuccess(result);
    }

    /**
      * 经纬度，到店位置计算
      */
     @AutoLog(value = "服务展示-到店位置计算", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "服务展示-到店位置计算", notes = "服务展示-到店位置计算")
     @GetMapping(value = "/distance")
     @NoLogin
     public Result queryDistanceByDisplayId (@RequestParam(name = "displayId") String displayId ,
                                             @RequestParam(name = "longitude") String longitude,
                                             @RequestParam(name = "latitude") String latitude) {
         String distance = appDisplayService.queryDistanceByDisplayId(longitude,latitude,displayId);
         return Result.toSuccess(distance);
     }

    @AutoLog(value = "预约接待人员下拉列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "预约接待人员下拉列表", notes = "预约接待人员下拉列表")
    @GetMapping(value = "/personList")
    public Result getServicePersonList() {
        List<MmccServocePersonVO> personList = appDisplayService.getServicePersonList();
        return Result.ok(personList);
    }

}
