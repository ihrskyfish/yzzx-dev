package com.wocai.platform.modules.app.nurse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.nurse.dto.AppNurseCollectDto;
import com.wocai.platform.modules.app.nurse.dto.AppNurseDetailsDto;
import com.wocai.platform.modules.app.nurse.service.AppNurseService;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsQueryByIdVo;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsVo;
import com.wocai.platform.modules.app.nurse.vo.AppNurseTypeVo;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseDetailsReq;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseDetailsRes;
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
 * @create: 2023-05-23 15:55
 **/
@Slf4j
@Api(tags="护理知识")
@RestController
@RequestMapping("/app/nurse")
public class AppNurseController {

    @Autowired
    private AppNurseService appNurseService;

    @AutoLog(value = "类别列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "类别列表", notes = "类别列表")
    @GetMapping(value = "/typeList")
    public Result<List<AppNurseTypeVo>> typeList() {
        List<AppNurseTypeVo> pageList = appNurseService.typeList();
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "根据类别查询护理知识列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "根据类别查询护理知识列表", notes = "根据类别查询护理知识列表")
    @PostMapping(value = "/getDetailsByTypeId")
    public Result<List<AppNurseDetailsVo>> getDetailsByTypeId(@RequestBody AppNurseDetailsDto appNurseDetailsDto, ReqUser reqUser) {
        List<AppNurseDetailsVo> pageList = appNurseService.getDetailsByTypeId(appNurseDetailsDto,reqUser);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "护理知识收藏", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "护理知识收藏", notes = "护理知识收藏")
    @PostMapping(value = "/collect")
    public Result collect(@RequestBody AppNurseCollectDto appNurseCollectDto, ReqUser reqUser) {
        appNurseService.collect(appNurseCollectDto,reqUser);
        return Result.toSuccess();
    }

    @AutoLog(value = "通过id查询知识内容详情", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "通过id查询知识内容详情", notes = "通过id查询知识内容详情")
    @GetMapping(value = "/queryById")
    public Result<AppNurseDetailsQueryByIdVo> queryById(@RequestParam(name = "id") String id,ReqUser reqUser) {
        AppNurseDetailsQueryByIdVo result = appNurseService.queryById(id,reqUser);
        return Result.toSuccess(result);
    }



}
