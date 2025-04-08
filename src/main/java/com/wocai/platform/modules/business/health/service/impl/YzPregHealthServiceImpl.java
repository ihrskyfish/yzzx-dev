package com.wocai.platform.modules.business.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.health.entity.YzPregHealth;
import com.wocai.platform.modules.business.health.dto.YzPregHealthReq;
import com.wocai.platform.modules.business.health.vo.YzPregHealthRes;
import com.wocai.platform.modules.business.health.mapper.YzPregHealthMapper;
import com.wocai.platform.modules.business.health.service.IYzPregHealthService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 孕期健康
 * @Author: why
 * @Date: 2023-06-16
 * @Version: V1.0
 */
@Service
public class YzPregHealthServiceImpl extends BaseServiceImpl<YzPregHealthMapper, YzPregHealth> implements IYzPregHealthService {
    
    @Override
    public IPage<YzPregHealthRes> queryPageList(IPage<YzPregHealthRes> page, YzPregHealthReq yzPregHealthReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzPregHealthRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(YzPregHealthReq yzPregHealthReq) {
        return false;
    }

    @Override
    public void saveMain(YzPregHealthReq yzPregHealthReq) {
        this.doDuplicateCheck(yzPregHealthReq);
        YzPregHealth yzPregHealth = new YzPregHealth();
        BeanUtils.copyProperties(yzPregHealthReq, yzPregHealth);
        this.save(yzPregHealth);
    }

    @Override
    public void updateMain(YzPregHealthReq yzPregHealthReq) {
        YzPregHealth yzPregHealth = this.getById(yzPregHealthReq.getId());
        if (yzPregHealth == null || CommonConstant.DEL_FLAG_1.equals(yzPregHealth.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(yzPregHealthReq);
        BeanUtils.copyProperties(yzPregHealthReq, yzPregHealth);
        this.updateById(yzPregHealth);
    }

    @Override
    public YzPregHealthRes getMainById(String id) {
        YzPregHealthRes result = new YzPregHealthRes();
        YzPregHealth yzPregHealth = this.getById(id);
        if (yzPregHealth == null || CommonConstant.DEL_FLAG_1.equals(yzPregHealth.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(yzPregHealth, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            YzPregHealth yzPregHealth = this.getById(id);
            if (yzPregHealth == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<YzPregHealth> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(YzPregHealth::getId, idList);
        YzPregHealth yzPregHealth = new YzPregHealth();
        yzPregHealth.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(yzPregHealth, queryWrapper);
    }
    
    
}