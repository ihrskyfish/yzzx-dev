package com.wocai.platform.modules.app.confinement.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.activity.vo.AppActivityQueryByIdVo;
import com.wocai.platform.modules.app.confinement.service.AppConfinementService;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementPageVo;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementQueryByIdVo;
import com.wocai.platform.modules.business.confinement.dto.MmccConfinementReq;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import com.wocai.platform.modules.business.confinement.vo.MmccConfinementRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 14:21
 **/
@Slf4j
@Api(tags = "产期圈")
@RestController
@RequestMapping("/app/confinement")
public class AppConfinementController {

    @Autowired
    private AppConfinementService appConfinementService;

    @AutoLog(value = "产期圈-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "产期圈-分页列表查询", notes = "产期圈-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<AppConfinementPageVo>> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                             ReqUser reqUser,
                                                             @RequestParam(name = "id",required = false) String id) {
        Page<MmccConfinement> page = new Page<>(pageNo, pageSize);
        IPage<AppConfinementPageVo> pageList = appConfinementService.queryPageList(page, reqUser,id);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping(value = "/queryById")
    public Result<AppConfinementQueryByIdVo> queryById(@RequestParam(name = "id", required = true) String id) {
        AppConfinementQueryByIdVo result = appConfinementService.getMainById(id);
        return Result.toSuccess(result);
    }


    @AutoLog(value = "点赞", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "点赞", notes = "点赞")
    @GetMapping(value = "/praise")
    public Result praise(@RequestParam(name = "id", required = true) String id, ReqUser reqUser) {
        appConfinementService.praise(id, reqUser);
        return Result.toSuccess("点赞成功");
    }

}
