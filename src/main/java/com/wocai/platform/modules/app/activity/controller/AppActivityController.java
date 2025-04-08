package com.wocai.platform.modules.app.activity.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.activity.dto.AppAcitvityRegisterDto;
import com.wocai.platform.modules.app.activity.service.AppActivityService;
import com.wocai.platform.modules.app.activity.vo.AppActivityPageVo;
import com.wocai.platform.modules.app.activity.vo.AppActivityQueryByIdVo;
import com.wocai.platform.modules.business.activity.dto.MmccActivityReq;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import com.wocai.platform.modules.business.activity.vo.MmccActivityRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 11:54
 **/

@Slf4j
@Api(tags="孕期圈")
@RestController
@RequestMapping("/app/activity")
public class AppActivityController {

    @Autowired
    private AppActivityService appActivityService;

    @AutoLog(value = "分页列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping(value = "/list")
    public Result<IPage<AppActivityPageVo>> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccActivity> page = new Page<>(pageNo, pageSize);
        IPage<AppActivityPageVo> pageList = appActivityService.queryPageList(page);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping(value = "/queryById")
    public Result<AppActivityQueryByIdVo> queryById(@RequestParam(name = "id", required = true) String id,ReqUser reqUser) {
        AppActivityQueryByIdVo result = appActivityService.getMainById(id,reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "立即报名", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "立即报名", notes = "立即报名")
    @PostMapping(value = "/register")
    public Result register(@RequestBody AppAcitvityRegisterDto appAcitvityRegisterDto, ReqUser reqUser) {
        appActivityService.register(appAcitvityRegisterDto,reqUser);
        return Result.toSuccess("报名成功");
    }



}
