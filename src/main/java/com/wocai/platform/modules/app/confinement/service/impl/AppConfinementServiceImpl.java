package com.wocai.platform.modules.app.confinement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.app.confinement.service.AppConfinementService;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementPageVo;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementQueryByIdVo;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementFile;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementUser;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementFileMapper;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementMapper;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementUserMapper;
import com.wocai.platform.modules.system.entity.SysUser;
import com.wocai.platform.modules.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 14:23
 **/
@Service
public class AppConfinementServiceImpl implements AppConfinementService {

    @Resource
    private MmccConfinementMapper mmccConfinementMapper;

    @Resource
    private MmccConfinementUserMapper mmccConfinementUserMapper;

    @Resource
    private MmccConfinementFileMapper mmccConfinementFileMapper;

    @Resource
    private ISysUserService sysUserService;

    @Override
    public IPage<AppConfinementPageVo> queryPageList(Page<MmccConfinement> page,ReqUser reqUser, String id) {
        IPage<AppConfinementPageVo> result=new Page<>();
        IPage<MmccConfinement> mmccConfinementIPage = mmccConfinementMapper.selectPage(page,
                Wrappers.<MmccConfinement>lambdaQuery()
                        .eq(MmccConfinement::getDelFlag, 0)
                        .eq(StringUtils.isNotEmpty(id),MmccConfinement::getId,id)
                        .orderByDesc(MmccConfinement::getSort)
                        .orderByDesc(MmccConfinement::getCreateTime));
        BeanUtils.copyProperties(mmccConfinementIPage,result);
        List<AppConfinementPageVo> collect = mmccConfinementIPage.getRecords()
                .stream()
                .map(item -> {
                    AppConfinementPageVo appConfinementPageVo = new AppConfinementPageVo();
                    BeanUtils.copyProperties(item, appConfinementPageVo);
                    Long number = item.getNumber();
                    item.setNumber(number + 1);
                    mmccConfinementMapper.updateById(item);
                    Integer count = mmccConfinementUserMapper.selectCount(
                            Wrappers.<MmccConfinementUser>lambdaQuery()
                                    .eq(MmccConfinementUser::getDelFlag, 0)
                                    .eq(MmccConfinementUser::getConfinementId, item.getId()));
                    appConfinementPageVo.setLikeNumber(count);

                    Integer likeCount = mmccConfinementUserMapper.selectCount(
                            Wrappers.<MmccConfinementUser>lambdaQuery()
                                    .eq(MmccConfinementUser::getDelFlag, 0)
                                    .eq(MmccConfinementUser::getConfinementId, item.getId())
                                    .eq(MmccConfinementUser::getUserId, reqUser.getUserId()));
                    if (likeCount>0){
                        appConfinementPageVo.setIslike(true);
                    }else {
                        appConfinementPageVo.setIslike(false);
                    }

                    List<String> urls = mmccConfinementFileMapper.selectList(
                            Wrappers.<MmccConfinementFile>lambdaQuery()
                                    .eq(MmccConfinementFile::getDelFlag, 0)
                                    .eq(MmccConfinementFile::getConfinementId, item.getId()))
                            .stream()
                            .map(vo -> {
                                vo.setFileUrl(ServerConfig.plat_upload_head_path +"/"+ vo.getFileUrl());
                                return vo;
                            })
                            .map(MmccConfinementFile::getFileUrl)
                            .collect(Collectors.toList());
                    appConfinementPageVo.setUrls(urls);

                    SysUser user = sysUserService.getUserByName(item.getCreateBy());
                    if (user != null) {
                        if (StringUtils.isNotEmpty(user.getAvatar())) {
                            appConfinementPageVo.setAvatar(ServerConfig.plat_upload_head_path+"/"+user.getAvatar());
                        }
                    }
                    return appConfinementPageVo;
                })
                .collect(Collectors.toList());
        result.setRecords(collect);
        return result;
    }

    @Override
    public AppConfinementQueryByIdVo getMainById(String id) {
        AppConfinementQueryByIdVo result=new AppConfinementQueryByIdVo();
        MmccConfinement mmccConfinement = mmccConfinementMapper.selectById(id);
        if (mmccConfinement==null || !"0".equalsIgnoreCase(mmccConfinement.getDelFlag().toString())) {
            throw new BizException("该内容不存在");
        }
        BeanUtils.copyProperties(mmccConfinement,result);
//        Long number = mmccConfinement.getNumber();
//        mmccConfinement.setNumber(number+1);
//        mmccConfinementMapper.updateById(mmccConfinement);


        List<String> urls = mmccConfinementFileMapper.selectList(
                Wrappers.<MmccConfinementFile>lambdaQuery()
                        .eq(MmccConfinementFile::getDelFlag, 0)
                        .eq(MmccConfinementFile::getConfinementId, id))
                .stream()
                .map(item -> {
                    item.setFileUrl(ServerConfig.plat_upload_head_path + item.getFileUrl());
                    return item;
                })
                .map(MmccConfinementFile::getFileUrl)
                .collect(Collectors.toList());
        result.setFileUrls(urls);
        return result;
    }

    @Override
    public void praise(String id, ReqUser reqUser) {
        LambdaQueryWrapper<MmccConfinementUser> mmccConfinementUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mmccConfinementUserLambdaQueryWrapper.eq(MmccConfinementUser::getDelFlag,0);
        mmccConfinementUserLambdaQueryWrapper.eq(MmccConfinementUser::getConfinementId,id);
        mmccConfinementUserLambdaQueryWrapper.eq(MmccConfinementUser::getUserId,reqUser.getUserId());
        MmccConfinementUser mmccConfinementUser = mmccConfinementUserMapper.selectOne(mmccConfinementUserLambdaQueryWrapper);
        if (mmccConfinementUser==null){
            mmccConfinementUser=new MmccConfinementUser();
            mmccConfinementUser.setConfinementId(id);
            mmccConfinementUser.setUserId(reqUser.getUserId());
            mmccConfinementUserMapper.insert(mmccConfinementUser);
        }else {
            mmccConfinementUserMapper.deleteById(mmccConfinementUser);
        }
    }
}
