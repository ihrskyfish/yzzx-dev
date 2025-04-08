package com.wocai.platform.modules.business.baby.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.baby.dto.MmccBabyUltrasoundReq;
import com.wocai.platform.modules.business.baby.vo.MmccBabyUltrasoundRes;
import com.wocai.platform.modules.business.baby.mapper.MmccBabyUltrasoundMapper;
import com.wocai.platform.modules.business.baby.service.IMmccBabyUltrasoundService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 宝宝B超
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Service
public class MmccBabyUltrasoundServiceImpl extends BaseServiceImpl<MmccBabyUltrasoundMapper, MmccBabyUltrasound> implements IMmccBabyUltrasoundService {
    
    @Override
    public IPage<MmccBabyUltrasoundRes> queryPageList(IPage<MmccBabyUltrasoundRes> page, MmccBabyUltrasoundReq mmccBabyUltrasoundReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<MmccBabyUltrasoundRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccBabyUltrasoundReq mmccBabyUltrasoundReq) {
        return false;
    }

    @Override
    public void saveMain(MmccBabyUltrasoundReq mmccBabyUltrasoundReq) {
        this.doDuplicateCheck(mmccBabyUltrasoundReq);
        MmccBabyUltrasound mmccBabyUltrasound = new MmccBabyUltrasound();
        BeanUtils.copyProperties(mmccBabyUltrasoundReq, mmccBabyUltrasound);
        this.save(mmccBabyUltrasound);
    }

    @Override
    public void updateMain(MmccBabyUltrasoundReq mmccBabyUltrasoundReq) {
        MmccBabyUltrasound mmccBabyUltrasound = this.getById(mmccBabyUltrasoundReq.getId());
        if (mmccBabyUltrasound == null || CommonConstant.DEL_FLAG_1.equals(mmccBabyUltrasound.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccBabyUltrasoundReq);
        BeanUtils.copyProperties(mmccBabyUltrasoundReq, mmccBabyUltrasound);
        this.updateById(mmccBabyUltrasound);
    }

    @Override
    public MmccBabyUltrasoundRes getMainById(String id) {
        MmccBabyUltrasoundRes result = new MmccBabyUltrasoundRes();
        MmccBabyUltrasound mmccBabyUltrasound = this.getById(id);
        if (mmccBabyUltrasound == null || CommonConstant.DEL_FLAG_1.equals(mmccBabyUltrasound.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccBabyUltrasound, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccBabyUltrasound mmccBabyUltrasound = this.getById(id);
            if (mmccBabyUltrasound == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccBabyUltrasound> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccBabyUltrasound::getId, idList);
        MmccBabyUltrasound mmccBabyUltrasound = new MmccBabyUltrasound();
        mmccBabyUltrasound.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccBabyUltrasound, queryWrapper);
    }
    
    
}