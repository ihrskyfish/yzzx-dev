package com.wocai.platform.modules.app.activity.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.app.activity.dto.AppAcitvityRegisterDto;
import com.wocai.platform.modules.app.activity.service.AppActivityService;
import com.wocai.platform.modules.app.activity.vo.AppActivityPageVo;
import com.wocai.platform.modules.app.activity.vo.AppActivityQueryByIdVo;
import com.wocai.platform.modules.app.activity.vo.AppActivityUserVo;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import com.wocai.platform.modules.business.activity.entity.MmccActivityFile;
import com.wocai.platform.modules.business.activity.entity.MmccActivityUser;
import com.wocai.platform.modules.business.activity.mapper.MmccActivityFileMapper;
import com.wocai.platform.modules.business.activity.mapper.MmccActivityMapper;
import com.wocai.platform.modules.business.activity.mapper.MmccActivityUserMapper;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 11:54
 **/
@Service
public class AppActivityServiceImpl implements AppActivityService {
    @Resource
    private MmccActivityMapper mmccActivityMapper;

    @Resource
    private MmccActivityUserMapper mmccActivityUserMapper;

    @Resource
    private MmccActivityFileMapper mmccActivityFileMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public IPage<AppActivityPageVo> queryPageList(Page<MmccActivity> page) {
        IPage<AppActivityPageVo> result = new Page<>();
        IPage<MmccActivity> mmccActivityIPage = mmccActivityMapper.selectPage(page,
                Wrappers.<MmccActivity>lambdaQuery()
                        .eq(MmccActivity::getDelFlag, 0)
                        .ge(MmccActivity::getStatus, 10)
//                        .le(MmccActivity::getStartTime, LocalDateTime.now())
                        .orderByDesc(MmccActivity::getSort)
                        .orderByDesc(MmccActivity::getCreateTime));
        BeanUtils.copyProperties(mmccActivityIPage, result);
        List<AppActivityPageVo> list = mmccActivityIPage.getRecords()
                .stream()
                .map(item -> {
                    AppActivityPageVo appActivityPageVo = new AppActivityPageVo();
                    BeanUtils.copyProperties(item, appActivityPageVo);
                    if (StringUtils.isNotEmpty(item.getFileUrl())){
                        appActivityPageVo.setFileUrl(ServerConfig.plat_upload_head_path+"/"+item.getFileUrl());
                    }

                    Integer count = mmccActivityUserMapper.selectCount(
                            Wrappers.<MmccActivityUser>lambdaQuery()
                                    .eq(MmccActivityUser::getDelFlag, 0).eq(MmccActivityUser::getActivityId, item.getId()));

                    appActivityPageVo.setCount(count);
                    return appActivityPageVo;
                })
                .collect(Collectors.toList());
        result.setRecords(list);
        return result;
    }

    @Override
    public AppActivityQueryByIdVo getMainById(String id,ReqUser reqUser) {
        AppActivityQueryByIdVo result = new AppActivityQueryByIdVo();
        MmccActivity mmccActivity = mmccActivityMapper.selectById(id);
        if (mmccActivity == null || !"0".equalsIgnoreCase(mmccActivity.getDelFlag().toString())) {
            throw new BizException("改活动不存在");
        }
        BeanUtils.copyProperties(mmccActivity, result);
        result.setFileUrl(ServerConfig.plat_upload_head_path+"/" + mmccActivity.getFileUrl());

        List<AppActivityUserVo> appActivityUserVos = mmccActivityUserMapper.selectList(
                Wrappers.<MmccActivityUser>lambdaQuery()
                        .eq(MmccActivityUser::getDelFlag, 0)
                        .eq(MmccActivityUser::getActivityId, id))
                .stream()
                .map(item -> {
                    AppActivityUserVo appActivityUserVo = new AppActivityUserVo();
                    User user = userMapper.selectById(item.getUserId());
                    if (user != null && "0".equalsIgnoreCase(user.getDelFlag().toString())) {
                        appActivityUserVo.setId(user.getId());
                        appActivityUserVo.setNickName(user.getNickName());
                    }
                    appActivityUserVo.setCreateTime(item.getCreateTime());
                    return appActivityUserVo;
                })
                .collect(Collectors.toList());
        result.setAppActivityUserVos(appActivityUserVos);


        List<String> achieveUrls = mmccActivityFileMapper.selectList(
                Wrappers.<MmccActivityFile>lambdaQuery()
                        .eq(MmccActivityFile::getDelFlag, 0)
                        .eq(MmccActivityFile::getActivityId, id))
                .stream()
                .map(item -> {
                    item.setFileUrl(ServerConfig.plat_upload_head_path+"/" + item.getFileUrl());
                    return item;
                })
                .map(MmccActivityFile::getFileUrl)
                .collect(Collectors.toList());
        result.setAchieveUrls(achieveUrls);

        Integer count = mmccActivityUserMapper.selectCount(
                Wrappers.<MmccActivityUser>lambdaQuery()
                        .eq(MmccActivityUser::getDelFlag, 0)
                        .eq(MmccActivityUser::getActivityId,id)
                        .eq(MmccActivityUser::getUserId,reqUser.getUserId()));
        if (count>0){
            result.setApplication(true);
        }else {
            result.setApplication(false);
        }

        return result;
    }

    @Override
    public void register(AppAcitvityRegisterDto appAcitvityRegisterDto, ReqUser reqUser) {
        Integer count = mmccActivityUserMapper.selectCount(
                Wrappers.<MmccActivityUser>lambdaQuery()
                        .eq(MmccActivityUser::getDelFlag, 0)
                        .eq(MmccActivityUser::getUserId, reqUser.getUserId())
                        .eq(MmccActivityUser::getActivityId, appAcitvityRegisterDto.getId()));
        if (count>0){
            throw new BizException("该活动已报名，请勿重复报名");
        }
        MmccActivityUser mmccActivityUser=new MmccActivityUser();
        mmccActivityUser.setUserId(reqUser.getUserId());
        mmccActivityUser.setActivityId(appAcitvityRegisterDto.getId());
        mmccActivityUserMapper.insert(mmccActivityUser);
    }
}
