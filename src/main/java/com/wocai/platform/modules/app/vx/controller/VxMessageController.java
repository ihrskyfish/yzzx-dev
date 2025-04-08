package com.wocai.platform.modules.app.vx.controller;


import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.activity.dto.AppAcitvityRegisterDto;
import com.wocai.platform.modules.app.vx.dto.VxSendUserMessageDto;
import com.wocai.platform.modules.app.vx.service.wxServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags="微信消息推送")
@RestController
@RequestMapping("/app/vxMessage")
public class VxMessageController {

    @Autowired
    private wxServiceImpl wxService;


    @Value("${wx.miniapp.appid}")
    private String appid;

    @Value("${wx.miniapp.secret}")
    private String secret;


    @AutoLog(value = "订阅后发送消息", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "订阅后发送消息", notes = "订阅后发送消息")
    @PostMapping(value = "/send")
    public Result register(@RequestBody VxSendUserMessageDto vxSendUserMessageDto, ReqUser reqUser) {
        String access_token = wxService.getAccess_token(appid, secret);

        wxService.pushOneUser(access_token,vxSendUserMessageDto);
        return Result.toSuccess("消息发送成功");
    }
}
