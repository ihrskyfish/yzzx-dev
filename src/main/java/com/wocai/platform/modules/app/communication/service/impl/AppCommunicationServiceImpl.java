package com.wocai.platform.modules.app.communication.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.modules.app.communication.dto.AppCommunicationUserDto;
import com.wocai.platform.modules.app.communication.service.AppCommunicationService;
import com.wocai.platform.modules.app.communication.vo.AppCommunicationPageVo;
import com.wocai.platform.modules.app.communication.vo.AppCommunicationQueryByIdVo;
import com.wocai.platform.modules.app.communication.vo.AppCommunicationUserVo;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationUser;
import com.wocai.platform.modules.business.communication.mapper.MmccCommunicationCircleMapper;
import com.wocai.platform.modules.business.communication.mapper.MmccCommunicationUserMapper;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 15:51
 **/
@Service
public class AppCommunicationServiceImpl implements AppCommunicationService {

    @Resource
    private MmccCommunicationCircleMapper mmccCommunicationCircleMapper;

    @Resource
    private MmccCommunicationUserMapper mmccCommunicationUserMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public IPage<AppCommunicationPageVo> queryPageList(Page<MmccCommunicationCircle> page) {
        IPage<AppCommunicationPageVo> result = new Page<>();
        IPage<MmccCommunicationCircle> mmccCommunicationCircleIPage = mmccCommunicationCircleMapper.selectPage(page,
                Wrappers.<MmccCommunicationCircle>lambdaQuery()
                        .eq(MmccCommunicationCircle::getDelFlag, 0)
                        .orderByDesc(MmccCommunicationCircle::getCreateTime));
        BeanUtils.copyProperties(mmccCommunicationCircleIPage, result);
        List<AppCommunicationPageVo> collect = mmccCommunicationCircleIPage.getRecords()
                .stream()
                .map(item -> {
                    AppCommunicationPageVo appCommunicationPageVo = new AppCommunicationPageVo();
                    BeanUtils.copyProperties(item, appCommunicationPageVo);
                    Integer count = mmccCommunicationUserMapper.selectCount(
                            Wrappers.<MmccCommunicationUser>lambdaQuery()
                                    .eq(MmccCommunicationUser::getCircleId, item.getId())
                                    .eq(MmccCommunicationUser::getEssence,1)
                                    .eq(MmccCommunicationUser::getDelFlag, 0));
                    appCommunicationPageVo.setAnswerCount(count);
                    return appCommunicationPageVo;
                })
                .collect(Collectors.toList());
        result.setRecords(collect);
        return result;
    }

    @Override
    public AppCommunicationQueryByIdVo getMainById(String id) {
        AppCommunicationQueryByIdVo result = new AppCommunicationQueryByIdVo();
        MmccCommunicationCircle mmccCommunicationCircle = mmccCommunicationCircleMapper.selectById(id);
        if (mmccCommunicationCircle == null || !"0".equalsIgnoreCase(mmccCommunicationCircle.getDelFlag().toString())) {
            throw new BizException("该话题不存在");
        }
        BeanUtils.copyProperties(mmccCommunicationCircle, result);

        List<AppCommunicationUserVo> list = mmccCommunicationUserMapper.selectList(Wrappers.<MmccCommunicationUser>lambdaQuery()
                .eq(MmccCommunicationUser::getCircleId, id)
                .eq(MmccCommunicationUser::getDelFlag, 0)
                .eq(MmccCommunicationUser::getEssence, 1)
                .orderByDesc(MmccCommunicationUser::getCreateTime))
                .stream()
                .map(item -> {
                    AppCommunicationUserVo appCommunicationUserVo = new AppCommunicationUserVo();
                    BeanUtils.copyProperties(item, appCommunicationUserVo);
                    User user = userMapper.selectById(item.getUserId());
                    if (user != null && "0".equalsIgnoreCase(user.getDelFlag().toString())) {
                        appCommunicationUserVo.setNickName(user.getNickName());
                        if (StringUtils.isNotEmpty(user.getAvatarUrl())){
                            appCommunicationUserVo.setAvatarUrl(ServerConfig.plat_upload_head_path+"/"+user.getAvatarUrl());
                        }
                    }
                    return appCommunicationUserVo;
                })
                .collect(Collectors.toList());

        result.setAppCommunicationUserVos(list);
        return result;
    }

    @Override
    public void evaluate(AppCommunicationUserDto appCommunicationUserDto, ReqUser reqUser) {
        MmccCommunicationCircle mmccCommunicationCircle = mmccCommunicationCircleMapper.selectById(appCommunicationUserDto.getId());
        if (mmccCommunicationCircle == null || !"0".equalsIgnoreCase(mmccCommunicationCircle.getDelFlag().toString())) {
            throw new BizException("该话题不存在");
        }
        MmccCommunicationUser mmccCommunicationUser=new MmccCommunicationUser();
        mmccCommunicationUser.setUserId(reqUser.getUserId());
        mmccCommunicationUser.setCircleId(appCommunicationUserDto.getId());
        mmccCommunicationUser.setEvaluate(appCommunicationUserDto.getEvaluate());
        mmccCommunicationUser.setEssence("0");
        mmccCommunicationUserMapper.insert(mmccCommunicationUser);
    }
}
