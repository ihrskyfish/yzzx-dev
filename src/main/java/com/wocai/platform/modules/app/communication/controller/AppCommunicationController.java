package com.wocai.platform.modules.app.communication.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.communication.dto.AppCommunicationUserDto;
import com.wocai.platform.modules.app.communication.service.AppCommunicationService;
import com.wocai.platform.modules.app.communication.vo.AppCommunicationPageVo;
import com.wocai.platform.modules.app.communication.vo.AppCommunicationQueryByIdVo;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementPageVo;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementQueryByIdVo;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 15:51
 **/
@Slf4j
@Api(tags = "交流圈")
@RestController
@RequestMapping("/app/communication")
public class AppCommunicationController {

    @Autowired
    private AppCommunicationService appCommunicationService;

    @AutoLog(value = "交流圈-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "交流圈-分页列表查询", notes = "交流圈-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<AppCommunicationPageVo>> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccCommunicationCircle> page = new Page<>(pageNo, pageSize);
        IPage<AppCommunicationPageVo> pageList = appCommunicationService.queryPageList(page);
        return Result.toSuccess(pageList);
    }


    @AutoLog(value = "通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping(value = "/queryById")
    public Result<AppCommunicationQueryByIdVo> queryById(@RequestParam(name = "id", required = true) String id) {
        AppCommunicationQueryByIdVo result = appCommunicationService.getMainById(id);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "评论", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "评论", notes = "评论")
    @PostMapping(value = "/evaluate")
    public Result evaluate(@RequestBody AppCommunicationUserDto appCommunicationUserDto, ReqUser reqUser) {
        appCommunicationService.evaluate(appCommunicationUserDto, reqUser);
        return Result.toSuccess("评论成功");
    }
}
