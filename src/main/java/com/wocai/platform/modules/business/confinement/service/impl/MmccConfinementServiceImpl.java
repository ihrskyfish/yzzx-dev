package com.wocai.platform.modules.business.confinement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import com.wocai.platform.modules.business.confinement.dto.MmccConfinementReq;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementFile;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementUser;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementFileMapper;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementUserMapper;
import com.wocai.platform.modules.business.confinement.vo.MmccConfinementRes;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementMapper;
import com.wocai.platform.modules.business.confinement.service.IMmccConfinementService;
import com.wocai.platform.modules.business.confinement.vo.MmccUserLikesVo;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 产期圈
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Service
public class MmccConfinementServiceImpl extends BaseServiceImpl<MmccConfinementMapper, MmccConfinement> implements IMmccConfinementService {

    @Resource
    private MmccConfinementUserMapper mmccConfinementUserMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private MmccConfinementFileMapper mmccConfinementFileMapper;

    @Override
    public IPage<MmccConfinementRes> queryPageList(IPage<MmccConfinementRes> page, MmccConfinementReq mmccConfinementReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotEmpty(mmccConfinementReq.getTitle())){
            reqParam.put("title",mmccConfinementReq.getTitle());
        }
        IPage<MmccConfinementRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords().forEach(item->{
            List<String> urls = mmccConfinementFileMapper.selectList(
                    Wrappers.<MmccConfinementFile>lambdaQuery()
                            .eq(MmccConfinementFile::getDelFlag, 0)
                            .eq(MmccConfinementFile::getConfinementId, item.getId()))
                    .stream()
                    .map(vo -> {
                        return ServerConfig.plat_upload_head_path + "/" + vo.getFileUrl();
                    })
                    .collect(Collectors.toList());
            item.setUrls(urls);
        });
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccConfinementReq mmccConfinementReq) {
        return false;
    }

    @Override
    public void saveMain(MmccConfinementReq mmccConfinementReq) {
        this.doDuplicateCheck(mmccConfinementReq);
        MmccConfinement mmccConfinement = new MmccConfinement();
        BeanUtils.copyProperties(mmccConfinementReq, mmccConfinement);
        this.save(mmccConfinement);

        if (mmccConfinementReq.getUrls()!=null && mmccConfinementReq.getUrls().size()>0){
            List<String> urls = mmccConfinementReq.getUrls();
            for (String url : urls) {
                MmccConfinementFile mmccConfinementFile = new MmccConfinementFile();
                mmccConfinementFile.setFileUrl(url);
                mmccConfinementFile.setConfinementId(mmccConfinement.getId());
                mmccConfinementFileMapper.insert(mmccConfinementFile);
            }
        }

    }

    @Override
    public void updateMain(MmccConfinementReq mmccConfinementReq) {
        MmccConfinement mmccConfinement = this.getById(mmccConfinementReq.getId());
        if (mmccConfinement == null || CommonConstant.DEL_FLAG_1.equals(mmccConfinement.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccConfinementReq);
        BeanUtils.copyProperties(mmccConfinementReq, mmccConfinement);
        this.updateById(mmccConfinement);

        mmccConfinementFileMapper.delete(
                Wrappers.<MmccConfinementFile>lambdaQuery()
                .eq(MmccConfinementFile::getDelFlag, 0)
                .eq(MmccConfinementFile::getConfinementId, mmccConfinement.getId()));
        if (mmccConfinementReq.getUrls()!=null && mmccConfinementReq.getUrls().size()>0){
            List<String> urls = mmccConfinementReq.getUrls();
            for (String url : urls) {
                MmccConfinementFile mmccConfinementFile = new MmccConfinementFile();
                mmccConfinementFile.setFileUrl(url);
                mmccConfinementFile.setConfinementId(mmccConfinement.getId());
                mmccConfinementFileMapper.insert(mmccConfinementFile);
            }
        }


    }

    @Override
    public MmccConfinementRes getMainById(String id) {
        MmccConfinementRes result = new MmccConfinementRes();
        MmccConfinement mmccConfinement = this.getById(id);
        if (mmccConfinement == null || CommonConstant.DEL_FLAG_1.equals(mmccConfinement.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccConfinement, result);

        List<String> urls = mmccConfinementFileMapper.selectList(
                Wrappers.<MmccConfinementFile>lambdaQuery()
                        .eq(MmccConfinementFile::getDelFlag, 0)
                        .eq(MmccConfinementFile::getConfinementId, mmccConfinement.getId()))
                .stream()
                .map(vo -> {
                    return ServerConfig.plat_upload_head_path + "/" + vo.getFileUrl();
                })
                .collect(Collectors.toList());
        result.setUrls(urls);

        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccConfinement mmccConfinement = this.getById(id);
            if (mmccConfinement == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccConfinement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccConfinement::getId, idList);
        MmccConfinement mmccConfinement = new MmccConfinement();
        mmccConfinement.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccConfinement, queryWrapper);
    }

    @Override
    public IPage<MmccUserLikesVo> userLikes(Page<MmccConfinementUser> page, String id) {
        IPage<MmccUserLikesVo> result=new Page<>();
        IPage<MmccConfinementUser> mmccConfinementUserIPage = mmccConfinementUserMapper.selectPage(page,
                Wrappers.<MmccConfinementUser>lambdaQuery()
                        .eq(MmccConfinementUser::getDelFlag, 0)
                        .eq(MmccConfinementUser::getConfinementId, id));
        BeanUtils.copyProperties(mmccConfinementUserIPage,result);
        List<MmccUserLikesVo> list = mmccConfinementUserIPage.getRecords()
                .stream()
                .map(item -> {
                    MmccUserLikesVo mmccUserLikesVo = new MmccUserLikesVo();
                    BeanUtils.copyProperties(item, mmccUserLikesVo);
                    User user = userMapper.selectById(item.getUserId());
                    if (user != null && "0".equalsIgnoreCase(user.getDelFlag().toString())) {
                        mmccUserLikesVo.setNickName(user.getNickName());
                        mmccUserLikesVo.setPhone(user.getPhone());
                    }
                    return mmccUserLikesVo;
                })
                .collect(Collectors.toList());
        result.setRecords(list);
        return result;
    }


}