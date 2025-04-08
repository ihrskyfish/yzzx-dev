package com.wocai.platform.modules.app.user.controller;

import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.aspect.annotation.NoLogin;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.user.dto.VxUserGestationalWeekDto;
import com.wocai.platform.modules.app.user.dto.WxPhoneCodeDTO;
import com.wocai.platform.modules.app.user.service.VxUserService;
import com.wocai.platform.modules.app.user.vo.AppUserLoginByCodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 11:13
 **/
@Slf4j
@Api(tags="Vx用户")
@RestController
@RequestMapping("/app/vx/user")
public class VxUserController {

    @Autowired
    private VxUserService vxUserService;

    @AutoLog(value = "wx用户登录")
    @ApiOperation(value = "wx用户登录", notes = "wx用户登录")
    @GetMapping("/loginByCode")
    @NoLogin
    public Result<AppUserLoginByCodeVo> loginByCode(@RequestParam(value = "code") String code) {
        AppUserLoginByCodeVo result= vxUserService.loginByCode(code);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "绑定wx授权手机号")
    @ApiOperation(value = "绑定wx授权手机号", notes = "绑定wx授权手机号")
    @PostMapping("/bindPhone")
    public Result bindPhone(@RequestBody @Validated WxPhoneCodeDTO wxPhoneCodeDTO, ReqUser reqUser) {
        vxUserService.bindPhone(wxPhoneCodeDTO,reqUser.getUserId());
        return Result.toSuccess();
    }


    @AutoLog(value = "填写当前孕周")
    @ApiOperation(value = "填写当前孕周", notes = "填写当前孕周")
    @PostMapping("/gestationalWeek")
    public Result gestationalWeek(@RequestBody VxUserGestationalWeekDto vxUserGestationalWeekDto, ReqUser reqUser) {
        vxUserService.gestationalWeek(vxUserGestationalWeekDto,reqUser.getUserId());
        return Result.toSuccess();
    }

    @AutoLog(value = "wx用户登录test")
    @ApiOperation(value = "wx用户登录test", notes = "wx用户登录test")
    @GetMapping("/loginByCodeTest")
    @NoLogin
    public Result<AppUserLoginByCodeVo> loginByCodeTest(@RequestParam(value = "code") String code) {
        AppUserLoginByCodeVo result= vxUserService.loginByCodeTest(code);
        return Result.toSuccess(result);
    }
}
