package com.wocai.platform.modules.business.nurse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseDetailsReq;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetailsFile;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseType;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseDetailsFileMapper;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseTypeMapper;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseDetailsRes;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseDetailsMapper;
import com.wocai.platform.modules.business.nurse.service.IMmccNurseDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 护理内容
 * @Author: lq
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Service
public class MmccNurseDetailsServiceImpl extends BaseServiceImpl<MmccNurseDetailsMapper, MmccNurseDetails> implements IMmccNurseDetailsService {

    @Resource
    private MmccNurseDetailsFileMapper mmccNurseDetailsFileMapper;

    @Resource
    private MmccNurseTypeMapper mmccNurseTypeMapper;

    @Override
    public IPage<MmccNurseDetailsRes> queryPageList(IPage<MmccNurseDetailsRes> page, MmccNurseDetailsReq mmccNurseDetailsReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<MmccNurseDetailsRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords().forEach(item->{

            if (StringUtils.isNotEmpty(item.getUrl())){
                item.setUrl(ServerConfig.plat_upload_head_path + "/" +item.getUrl());
            }

            MmccNurseType mmccNurseType = mmccNurseTypeMapper.selectById(item.getTypeId());
            if (mmccNurseType!=null && "0".equalsIgnoreCase(mmccNurseType.getDelFlag().toString())) {
                item.setTypeName(mmccNurseType.getName());
            }
            List<String> urls = mmccNurseDetailsFileMapper.selectList(
                    Wrappers.<MmccNurseDetailsFile>lambdaQuery()
                            .eq(MmccNurseDetailsFile::getDetailesId, item.getId())
                            .eq(MmccNurseDetailsFile::getDelFlag, 0))
                    .stream()
                    .map(vo -> {
                        return ServerConfig.plat_upload_head_path + "/" + vo.getUrl();
                    })
                    .collect(Collectors.toList());
            item.setUrls(urls);
        });
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccNurseDetailsReq mmccNurseDetailsReq) {
        return false;
    }

    @Override
    public void saveMain(MmccNurseDetailsReq mmccNurseDetailsReq) {
        this.doDuplicateCheck(mmccNurseDetailsReq);
        MmccNurseDetails mmccNurseDetails = new MmccNurseDetails();
        BeanUtils.copyProperties(mmccNurseDetailsReq, mmccNurseDetails);
        this.save(mmccNurseDetails);
    }

    @Override
    public void updateMain(MmccNurseDetailsReq mmccNurseDetailsReq) {
        MmccNurseDetails mmccNurseDetails = this.getById(mmccNurseDetailsReq.getId());
        if (mmccNurseDetails == null || CommonConstant.DEL_FLAG_1.equals(mmccNurseDetails.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccNurseDetailsReq);
        BeanUtils.copyProperties(mmccNurseDetailsReq, mmccNurseDetails);
        this.updateById(mmccNurseDetails);
    }

    @Override
    public MmccNurseDetailsRes getMainById(String id) {
        MmccNurseDetailsRes result = new MmccNurseDetailsRes();
        MmccNurseDetails mmccNurseDetails = this.getById(id);
        if (mmccNurseDetails == null || CommonConstant.DEL_FLAG_1.equals(mmccNurseDetails.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccNurseDetails, result);

        if (StringUtils.isNotEmpty(mmccNurseDetails.getUrl())){
            result.setUrlKey(mmccNurseDetails.getUrl());
            result.setUrl(ServerConfig.plat_upload_head_path + "/" +mmccNurseDetails.getUrl());
        }


        MmccNurseType mmccNurseType = mmccNurseTypeMapper.selectById(result.getTypeId());
        if (mmccNurseType!=null && "0".equalsIgnoreCase(mmccNurseType.getDelFlag().toString())) {
            result.setTypeName(mmccNurseType.getName());
        }

        List<String> urls = mmccNurseDetailsFileMapper.selectList(
                Wrappers.<MmccNurseDetailsFile>lambdaQuery()
                        .eq(MmccNurseDetailsFile::getDetailesId, result.getId())
                        .eq(MmccNurseDetailsFile::getDelFlag, 0))
                .stream()
                .map(vo -> {
                    return ServerConfig.plat_upload_head_path + "/" + vo.getUrl();
                })
                .collect(Collectors.toList());
        result.setUrls(urls);

        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccNurseDetails mmccNurseDetails = this.getById(id);
            if (mmccNurseDetails == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccNurseDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccNurseDetails::getId, idList);
        MmccNurseDetails mmccNurseDetails = new MmccNurseDetails();
        mmccNurseDetails.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccNurseDetails, queryWrapper);
    }

    @Override
    public void essenceById(String id) {
        MmccNurseDetails mmccNurseDetails = this.getById(id);
        if (mmccNurseDetails == null || CommonConstant.DEL_FLAG_1.equals(mmccNurseDetails.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        if ("1".equalsIgnoreCase(mmccNurseDetails.getRecommend())){
            mmccNurseDetails.setRecommend("0");
        }else {
            mmccNurseDetails.setRecommend("1");
        }
        baseMapper.updateById(mmccNurseDetails);
    }



}