package com.wocai.platform.modules.app.display.service.impl;


import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.enums.MmccServiceUserStatusEnum;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.PositionUtil;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.modules.app.display.dto.AppCancellationDto;
import com.wocai.platform.modules.app.display.dto.AppDisplayBookDto;
import com.wocai.platform.modules.app.display.dto.AppDisplayEvaluateVo;
import com.wocai.platform.modules.app.display.service.AppDisplayService;
import com.wocai.platform.modules.app.display.vo.*;
import com.wocai.platform.modules.app.vx.dto.VxSendUserMessageDto;
import com.wocai.platform.modules.app.vx.service.wxServiceImpl;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplayFiles;
import com.wocai.platform.modules.business.display.entity.MmccServicePerson;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import com.wocai.platform.modules.business.display.mapper.MmccServiceDisplayFilesMapper;
import com.wocai.platform.modules.business.display.mapper.MmccServiceDisplayMapper;
import com.wocai.platform.modules.business.display.mapper.MmccServicePersonMapper;
import com.wocai.platform.modules.business.display.mapper.MmccServiceUserMapper;
import com.wocai.platform.modules.business.display.service.impl.MmccServicePersonServiceImpl;
import com.wocai.platform.modules.business.display.vo.MmccServocePersonVO;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 16:54
 **/
@Service
@Slf4j
public class AppDisplayServiceImpl implements AppDisplayService {

    @Resource
    private MmccServiceDisplayMapper mmccServiceDisplayMapper;

    @Resource
    private MmccServiceDisplayFilesMapper mmccServiceDisplayFilesMapper;

    @Resource
    private MmccServiceUserMapper mmccServiceUserMapper;

    @Resource
    private  MmccServicePersonMapper mmccServicePersonMapper;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private wxServiceImpl wxService;

    @Value("${wx.miniapp.appid}")
    private String appid;

    @Value("${wx.miniapp.secret}")
    private String secret;

    @Autowired
    private MmccServicePersonServiceImpl mmccServicePersonService;


    @Override
    public List<AppDisplayListVo> queryPageList(ReqUser reqUser) {
//    public List<AppDisplayListVo> queryPageList() {
        List<AppDisplayListVo> result = mmccServiceDisplayMapper.selectList(
                Wrappers.<MmccServiceDisplay>lambdaQuery()
                        .eq(MmccServiceDisplay::getDelFlag, 0)
                        .eq(MmccServiceDisplay::getStatus, 1)
                        .orderByDesc(MmccServiceDisplay::getCreateTime))
                .stream()
                .map(item -> {
                    AppDisplayListVo appDisplayListVo = new AppDisplayListVo();
                    BeanUtils.copyProperties(item, appDisplayListVo);
                    appDisplayListVo.setFileUrl(ServerConfig.plat_upload_head_path + "/" + item.getCoverImageUrl());
                    return appDisplayListVo;
                })
                .collect(Collectors.toList());
        return result;
    }
    @Override
    public AppDisplayQueryByIdVo queyById(String id, ReqUser reqUser) {
        AppDisplayQueryByIdVo result = new AppDisplayQueryByIdVo();
        MmccServiceDisplay mmccServiceDisplay = mmccServiceDisplayMapper.selectById(id);
        if (mmccServiceDisplay == null || !"0".equalsIgnoreCase(mmccServiceDisplay.getDelFlag().toString())) {
            throw new BizException("该房间不存在");
        }
        BeanUtils.copyProperties(mmccServiceDisplay, result);
        if (StringUtils.isNotEmpty(mmccServiceDisplay.getCoverImageUrl())) {
            result.setCoverImageUrl(ServerConfig.plat_upload_head_path + "/" + mmccServiceDisplay.getCoverImageUrl());
        }
        if (StringUtils.isNotEmpty(mmccServiceDisplay.getDetailsUrl())) {
            result.setDetailsUrl(ServerConfig.plat_upload_head_path + "/" + mmccServiceDisplay.getDetailsUrl());
        }
        List<AppDisplayFileVo> appDisplayFileVos = mmccServiceDisplayFilesMapper.selectList(
                Wrappers.<MmccServiceDisplayFiles>lambdaQuery()
                        .eq(MmccServiceDisplayFiles::getDelFlag, 0)
                        .eq(MmccServiceDisplayFiles::getDisplayId, id))
                .stream()
                .map(item -> {
                    AppDisplayFileVo appDisplayFileVo = new AppDisplayFileVo
();
                    appDisplayFileVo.setUrl(ServerConfig.plat_upload_head_path + "/" + item.getFileUrl());
                    appDisplayFileVo.setIntroduce(item.getIntroduce());
                    return appDisplayFileVo;
                })
                .collect(Collectors.toList());
        result.setAppDisplayFileVos(appDisplayFileVos);

        MmccServiceUser mmccServiceUser = mmccServiceUserMapper.selectOne(
                Wrappers.<MmccServiceUser>lambdaQuery()
                        .eq(MmccServiceUser::getDelFlag, 0)
                        .eq(MmccServiceUser::getDisplayId, id)
                        .eq(MmccServiceUser::getUserId, reqUser.getUserId())
                        .last("limit 1"));
        if (mmccServiceUser == null) {
            result.setPredetermine(false);
        } else {
            result.setPredetermine(true);
        }

        return result;
    }

    @Override
    public void book(AppDisplayBookDto appDisplayBookDto, ReqUser reqUser) {

        MmccServiceUser mmccServiceUser = new MmccServiceUser();
        BeanUtils.copyProperties(appDisplayBookDto, mmccServiceUser);

        String userId = reqUser.getUserId();

        mmccServiceUser.setUserId(userId);
        mmccServiceUser.setScheduledTime(new Date());
        mmccServiceUser.setStatus("-1");


        // 根据用户预定时填写的胎次 更改用户的胎次信息
//        userMapper
        User user = userMapper.selectOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getDelFlag, 0)
                        .eq(User::getId, userId)
                        .last("limit 1"));

        Long parity = mmccServiceUser.getParity();
        if (parity != null){
            user.setParity(parity);
            userMapper.updateById(user);
        }
        String nickName = user.getNickName();
        mmccServiceUser.setUserName(nickName);
        mmccServiceUserMapper.insert(mmccServiceUser);

        String access_token = wxService.getAccess_token(appid, secret);
        VxSendUserMessageDto vxSendUserMessageDto = new VxSendUserMessageDto();
        vxSendUserMessageDto.setOpenid(user.getOpenid());
        vxSendUserMessageDto.setType("2");
        vxSendUserMessageDto.setTemplateId("mQSwFneXjhqXV2EGvFpdhp_ghCbPhGFultyMmwz3btg");
        String[] keywords = new String[5];

        Date visitingTime = appDisplayBookDto.getVisitingTime();
        String pattern="yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf= new SimpleDateFormat(pattern);
        String datestr = sdf.format(visitingTime);// format  为格式化方法

        keywords[0] = datestr;
        log.info(appDisplayBookDto.getVisitingTime().toString());



        MmccServiceDisplay mmccServiceDisplay = mmccServiceDisplayMapper.selectById(appDisplayBookDto.getDisplayId());
        String phoneCode = mmccServiceDisplay.getPhoneCode();
        keywords[1] = phoneCode;
        keywords[2] = mmccServiceDisplay.getStoreAddress();
        keywords[3] = "恭喜您预约成功，欢迎到店参观";
        keywords[4] = mmccServiceDisplay.getName();
        vxSendUserMessageDto.setKeywords(keywords);
        wxService.pushOneUser(access_token,vxSendUserMessageDto);

    }

    @Override
    public List<AppDisplayMyBookListVo> mybookList(ReqUser reqUser) {
        List<AppDisplayMyBookListVo> result = mmccServiceUserMapper.selectList(
                Wrappers.<MmccServiceUser>lambdaQuery()
                        .eq(MmccServiceUser::getDelFlag, 0)
                        .eq(MmccServiceUser::getUserId, reqUser.getUserId())
                        .orderByDesc(MmccServiceUser::getCreateTime))
                .stream()
                .map(item -> {
                    AppDisplayMyBookListVo appDisplayMyBookListVo = new AppDisplayMyBookListVo();
                    BeanUtils.copyProperties(item, appDisplayMyBookListVo);
                    appDisplayMyBookListVo.setId(item.getDisplayId());
                    appDisplayMyBookListVo.setServiceUserId(item.getId());
                    appDisplayMyBookListVo.setScheduledTime(item.getVisitingTime());
                    String evaluateContent = item.getEvaluateContent();
                    Date evaluateTime = item.getEvaluateTime();
                    if (com.wocai.platform.common.util.StringUtils.isNotEmpty(evaluateContent)){
                        appDisplayMyBookListVo.setEvaluateContent(evaluateContent);
                    }
                    if (item.getEvaluateTime()!= null){
                        appDisplayMyBookListVo.setEvaluateTime(evaluateTime);
                    }

                    MmccServiceDisplay mmccServiceDisplay = mmccServiceDisplayMapper.selectById(item.getDisplayId());
                    if (mmccServiceDisplay != null && "0".equalsIgnoreCase(mmccServiceDisplay.getDelFlag().toString())) {
                        appDisplayMyBookListVo.setName(mmccServiceDisplay.getName());
                    }

//                    MmccServiceDisplayFiles mmccServiceDisplayFiles = mmccServiceDisplayFilesMapper.selectOne(
//                            Wrappers.<MmccServiceDisplayFiles>lambdaQuery()
//                                    .eq(MmccServiceDisplayFiles::getDelFlag, 0)
//                                    .eq(MmccServiceDisplayFiles::getDisplayId, item.getDisplayId())
//                                    .last("limit 1"));
                    if (StringUtils.isNotEmpty(mmccServiceDisplay.getCoverImageUrl())){
                        appDisplayMyBookListVo.setFileUrl(ServerConfig.plat_upload_head_path + "/" + mmccServiceDisplay.getCoverImageUrl());
                    }
                    return appDisplayMyBookListVo;
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void evaluate(AppDisplayEvaluateVo appDisplayEvaluateVo) {
        MmccServiceUser mmccServiceUser = mmccServiceUserMapper.selectById(appDisplayEvaluateVo.getServiceUserId());
        if (mmccServiceUser == null || !"0".equalsIgnoreCase(mmccServiceUser.getDelFlag().toString())) {
            throw new BizException("预订信息不存在");
        }
        if ("1".equalsIgnoreCase(mmccServiceUser.getStatus())) {
            throw new BizException("该预定已评论，请勿重复评论");
        }
        mmccServiceUser.setEvaluateTime(new Date());
        mmccServiceUser.setEvaluateContent(appDisplayEvaluateVo.getEvaluateContent());
        mmccServiceUser.setStatus("1");
        mmccServiceUserMapper.updateById(mmccServiceUser);
    }

    @Override
    public AppDisplayBookQueryByIdVo queyByServiceUserId(String serviceUserId) {
        AppDisplayBookQueryByIdVo result = new AppDisplayBookQueryByIdVo();
        MmccServiceUser mmccServiceUser = mmccServiceUserMapper.selectById(serviceUserId);
        if (mmccServiceUser == null || !"0".equalsIgnoreCase(mmccServiceUser.getDelFlag().toString())) {
            throw new BizException("预定信息不存在");
        }
        BeanUtils.copyProperties(mmccServiceUser, result);
        String servicePersonId = result.getServicePersonId();
        if(StringUtils.isNotEmpty(servicePersonId)){
            MmccServicePerson mmccServicePerson = mmccServicePersonMapper.selectById(result.getServicePersonId());
            String name = mmccServicePerson.getName();
            result.setServicePersonName(name);
        }

        return result;
    }


    @Override
    public String queryDistanceByDisplayId(String longitude, String latitude, String displayId ) {
        MmccServiceDisplay mmccServiceDisplay = mmccServiceDisplayMapper.selectById(displayId);

        if (mmccServiceDisplay == null || !"0".equalsIgnoreCase(mmccServiceDisplay.getDelFlag().toString())) {
            throw new BizException("该房间不存在");
        }

        // 获取门店经纬度
        String longitude1 = mmccServiceDisplay.getLongitude();
        String latitude1 = mmccServiceDisplay.getLatitude();

        double d_longitude1 = Double.parseDouble(longitude1);
        double d_latitude1 = Double.parseDouble(latitude1);
        double d_longitude = Double.parseDouble(longitude);
        double d_latitude = Double.parseDouble(latitude);
        double distance1 = PositionUtil.getDistance1(d_longitude1, d_latitude1, d_longitude, d_latitude);

        return "" + distance1;
    }

    @Override
    public void cancellation(AppCancellationDto appCancellationDto, ReqUser reqUser) {
        String serviceUserId = appCancellationDto.getServiceUserId();

        MmccServiceUser mmccServiceUser = mmccServiceUserMapper.selectById(serviceUserId);
        if (mmccServiceUser == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceUser.getDelFlag())) {
            throw new BizException("该预定信息不存在");
        }
        //
        mmccServiceUser.setStatus(MmccServiceUserStatusEnum.SERVICE_CANCELLATION.getCode());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        mmccServiceUser.setUpdateTime(date);
        mmccServiceUserMapper.updateById(mmccServiceUser);
    }

    @Override
    public List<MmccServocePersonVO> getServicePersonList() {
        List<MmccServocePersonVO> servicePersonList = mmccServicePersonService.getServicePersonList();
        return servicePersonList;
    }

}
