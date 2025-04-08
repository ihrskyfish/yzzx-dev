package com.wocai.platform.modules.app.my.controller;

import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.home.vo.AppProcessColumnVo;
import com.wocai.platform.modules.app.my.dto.MyImagesDto;
import com.wocai.platform.modules.app.my.dto.MyUpdateUserDto;
import com.wocai.platform.modules.app.my.service.MyService;
import com.wocai.platform.modules.app.my.vo.*;
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
 * @create: 2023-05-25 09:55
 **/
@Slf4j
@Api(tags="我的")
@RestController
@RequestMapping("/app/my")
public class MyController {

    @Autowired
    private MyService myService;

    @AutoLog(value = "修改个人信息", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "修改个人信息", notes = "修改个人信息")
    @PostMapping(value = "/updateMyInformation")
    public Result updateMyInformation(@RequestBody MyUpdateUserDto myUpdateUserDto, ReqUser reqUser){
        myService.updateMyInformation(myUpdateUserDto,reqUser);
        return Result.toSuccess("修改成功");
    }

    @AutoLog(value = "我的个人信息", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "我的个人信息", notes = "我的个人信息")
    @PostMapping(value = "/myPersonalInformation")
    public Result<MyPersonalInformationVo> myPersonalInformation( ReqUser reqUser){
        MyPersonalInformationVo result=myService.myPersonalInformation(reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "关闭生日贺礼", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "关闭生日贺礼", notes = "关闭生日贺礼")
    @PostMapping(value = "/closeBirthdayGifts")
    public Result closeBirthdayGifts( ReqUser reqUser){
        myService.closeBirthdayGifts(reqUser);
        return Result.toSuccess("");
    }


    @AutoLog(value = "孕期档案", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "孕期档案", notes = "孕期档案")
    @PostMapping(value = "/MyPregnancyArchives")
    public Result<MyPregnancyArchivesVo> MyPregnancyArchives(ReqUser reqUser){
        MyPregnancyArchivesVo result=myService.MyPregnancyArchivesV2(reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "宝宝档案", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "宝宝档案", notes = "宝宝档案")
    @PostMapping(value = "/babyProfile")
    public Result<List<MyBabyProfileVo>> babyProfile(ReqUser reqUser){
        List<MyBabyProfileVo> result=myService.babyProfile(reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "我的点赞", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "我的点赞", notes = "我的点赞")
    @PostMapping(value = "/myLikes")
    public Result<List<MyLikesVo>> myLikes(ReqUser reqUser){
        List<MyLikesVo> result=myService.myLikes(reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "我的收藏", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "我的收藏", notes = "我的收藏")
    @PostMapping(value = "/myCollection")
    public Result<List<MyCollectionVo>> myCollection(ReqUser reqUser){
        List<MyCollectionVo> result=myService.myCollection(reqUser);
        return Result.toSuccess(result);
    }

    @AutoLog(value = "图片合成一张", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "图片合成一张", notes = "图片合成一张")
    @PostMapping(value = "/imageSynthesis")
    public Result<String> imageSynthesis(@RequestBody MyImagesDto myImagesDto){
        String result=myService.imageSynthesis(myImagesDto);
        return Result.toSuccess(result);
    }



}
