package com.wocai.platform.modules.app.nurse.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.app.nurse.dto.AppNurseCollectDto;
import com.wocai.platform.modules.app.nurse.dto.AppNurseDetailsDto;
import com.wocai.platform.modules.app.nurse.service.AppNurseService;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsQueryByIdVo;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsVo;
import com.wocai.platform.modules.app.nurse.vo.AppNurseTypeVo;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetailsFile;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseType;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseUser;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseDetailsFileMapper;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseDetailsMapper;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseTypeMapper;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseUserMapper;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 15:55
 **/
@Service
public class AppNurseServiceImpl implements AppNurseService {

    @Resource
    private MmccNurseTypeMapper mmccNurseTypeMapper;

    @Resource
    private MmccNurseDetailsMapper mmccNurseDetailsMapper;

    @Resource
    private MmccNurseUserMapper mmccNurseUserMapper;

    @Resource
    private MmccNurseDetailsFileMapper mmccNurseDetailsFileMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public List<AppNurseTypeVo> typeList() {
        List<AppNurseTypeVo> result = mmccNurseTypeMapper.selectList(
                Wrappers.<MmccNurseType>lambdaQuery()
                        .eq(MmccNurseType::getDelFlag, 0)
                        .eq(MmccNurseType::getStatus,1)
                        .orderByDesc(MmccNurseType::getSort)
                        .orderByDesc(MmccNurseType::getCreateTime))
                .stream()
                .map(item -> {
                    AppNurseTypeVo appNurseTypeVo = new AppNurseTypeVo();
                    BeanUtils.copyProperties(item, appNurseTypeVo);
                    return appNurseTypeVo;
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<AppNurseDetailsVo> getDetailsByTypeId(AppNurseDetailsDto appNurseDetailsDto, ReqUser reqUser) {
        List<AppNurseDetailsVo> result=new ArrayList<>();
        User user = userMapper.selectById(reqUser.getUserId());
        if (user==null || !"0".equalsIgnoreCase(user.getDelFlag().toString())) {
            throw new BizException("该用户不存在");
        }

        if (user.getGestationalWeek()==null){
            throw new BizException("该用户孕周未填写");
        }

        //非推荐
        if ("0".equalsIgnoreCase(appNurseDetailsDto.getRecommend())){
            result = mmccNurseDetailsMapper.selectList(
                    Wrappers.<MmccNurseDetails>lambdaQuery()
                            .eq(MmccNurseDetails::getDelFlag, 0)
                            .eq(MmccNurseDetails::getTypeId, appNurseDetailsDto.getId())
                            .le(MmccNurseDetails::getStateWeek,user.getGestationalWeek())
                            .ge(MmccNurseDetails::getEndWeek, user.getGestationalWeek())
                            .orderByDesc(MmccNurseDetails::getSort)
                            .orderByDesc(MmccNurseDetails::getCreateTime))
                    .stream()
                    .map(item -> {
                        AppNurseDetailsVo appNurseDetailsVo = new AppNurseDetailsVo();
                        BeanUtils.copyProperties(item, appNurseDetailsVo);
                        Integer count = mmccNurseUserMapper.selectCount(
                                Wrappers.<MmccNurseUser>lambdaQuery()
                                        .eq(MmccNurseUser::getDetailesId, item.getId())
                                        .eq(MmccNurseUser::getUserId, reqUser.getUserId()));
                        if (count>0){
                            appNurseDetailsVo.setCollect(true);
                        }else {
                            appNurseDetailsVo.setCollect(false);
                        }
                        return appNurseDetailsVo;
                    })
                    .collect(Collectors.toList());
        }else {
            //推荐
//            result=mmccNurseDetailsMapper.selectList(
//                    Wrappers.<MmccNurseDetails>lambdaQuery()
//                            .eq(MmccNurseDetails::getDelFlag, 0)
//                            .eq(MmccNurseDetails::getRecommend,1)
//                            .orderByDesc(MmccNurseDetails::getSort)
//                            .orderByDesc(MmccNurseDetails::getCreateTime))
            result=mmccNurseDetailsMapper.getRecommendDetails(user.getGestationalWeek())
                    .stream()
                    .map(item -> {
                        AppNurseDetailsVo appNurseDetailsVo = new AppNurseDetailsVo();
                        BeanUtils.copyProperties(item, appNurseDetailsVo);

                        Integer count = mmccNurseUserMapper.selectCount(
                                Wrappers.<MmccNurseUser>lambdaQuery()
                                        .eq(MmccNurseUser::getDetailesId, item.getId())
                                        .eq(MmccNurseUser::getUserId, reqUser.getUserId()));
                        if (count>0){
                            appNurseDetailsVo.setCollect(true);
                        }else {
                            appNurseDetailsVo.setCollect(false);
                        }
                        return appNurseDetailsVo;
                    })
                    .collect(Collectors.toList());
        }

        return result;
    }

    @Override
    public void collect(AppNurseCollectDto appNurseCollectDto, ReqUser reqUser) {
        MmccNurseDetails mmccNurseDetails = mmccNurseDetailsMapper.selectById(appNurseCollectDto.getId());
        if (mmccNurseDetails==null || !"0".equalsIgnoreCase(mmccNurseDetails.getDelFlag().toString())){
            throw new BizException("该护理知识不存在");
        }
        MmccNurseUser mmccNurseUse = mmccNurseUserMapper.selectOne(Wrappers.<MmccNurseUser>lambdaQuery()
                .eq(MmccNurseUser::getDetailesId, appNurseCollectDto.getId())
                .eq(MmccNurseUser::getUserId, reqUser.getUserId()));
        if (mmccNurseUse==null){
            mmccNurseUse=new MmccNurseUser();
            mmccNurseUse.setUserId(reqUser.getUserId());
            mmccNurseUse.setDetailesId(appNurseCollectDto.getId());
            mmccNurseUserMapper.insert(mmccNurseUse);
        }else {
            mmccNurseUserMapper.deleteById(mmccNurseUse);
        }
    }

    @Override
    public AppNurseDetailsQueryByIdVo queryById(String id,ReqUser reqUser) {
        AppNurseDetailsQueryByIdVo result=new AppNurseDetailsQueryByIdVo();
        MmccNurseDetails mmccNurseDetails = mmccNurseDetailsMapper.selectById(id);
        if (mmccNurseDetails==null || !"0".equalsIgnoreCase(mmccNurseDetails.getDelFlag().toString())){
            throw new BizException("该护理知识不存在");
        }
        BeanUtils.copyProperties(mmccNurseDetails,result);
        Integer count = mmccNurseUserMapper.selectCount(
                Wrappers.<MmccNurseUser>lambdaQuery()
                        .eq(MmccNurseUser::getDetailesId, id)
                        .eq(MmccNurseUser::getUserId, reqUser.getUserId()));
        if (count>0){
            result.setCollect(true);
        }else {
            result.setCollect(false);
        }
        List<String> fileUrls=new ArrayList<>();
        if (StringUtils.isNotEmpty(mmccNurseDetails.getUrl())){
            fileUrls.add(ServerConfig.plat_upload_head_path+"/" + mmccNurseDetails.getUrl());
        }
//        List<String> fileUrls = mmccNurseDetailsFileMapper.selectList(
//                Wrappers.<MmccNurseDetailsFile>lambdaQuery()
//                        .eq(MmccNurseDetailsFile::getDelFlag, 0)
//                        .eq(MmccNurseDetailsFile::getDetailesId, id))
//                .stream()
//                .map(item -> {
//                    item.setUrl(ServerConfig.plat_upload_head_path+"/" + item.getUrl());
//                    return item;
//                })
//                .map(MmccNurseDetailsFile::getUrl)
//                .collect(Collectors.toList());
        result.setFileUrls(fileUrls);
        return result;
    }
}
