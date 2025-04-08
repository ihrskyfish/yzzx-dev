package com.wocai.platform.modules.business.nurse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.nurse.entity.YzNsCategory;
import com.wocai.platform.modules.business.nurse.dto.YzNsCategoryReq;
import com.wocai.platform.modules.business.nurse.vo.YzNsCategoryRes;
import com.wocai.platform.modules.business.nurse.mapper.YzNsCategoryMapper;
import com.wocai.platform.modules.business.nurse.service.IYzNsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 护理知识分类
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Service
public class YzNsCategoryServiceImpl extends BaseServiceImpl<YzNsCategoryMapper, YzNsCategory> implements IYzNsCategoryService {
    
    @Override
    public IPage<YzNsCategoryRes> queryPageList(IPage<YzNsCategoryRes> page, YzNsCategoryReq yzNsCategoryReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzNsCategoryRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(YzNsCategoryReq yzNsCategoryReq) {
        return false;
    }

    @Override
    public void saveMain(YzNsCategoryReq yzNsCategoryReq) {
        this.doDuplicateCheck(yzNsCategoryReq);
        YzNsCategory yzNsCategory = new YzNsCategory();
        BeanUtils.copyProperties(yzNsCategoryReq, yzNsCategory);
        this.save(yzNsCategory);
    }

    @Override
    public void updateMain(YzNsCategoryReq yzNsCategoryReq) {
        YzNsCategory yzNsCategory = this.getById(yzNsCategoryReq.getId());
        if (yzNsCategory == null || CommonConstant.DEL_FLAG_1.equals(yzNsCategory.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(yzNsCategoryReq);
        BeanUtils.copyProperties(yzNsCategoryReq, yzNsCategory);
        this.updateById(yzNsCategory);
    }

    @Override
    public YzNsCategoryRes getMainById(String id) {
        YzNsCategoryRes result = new YzNsCategoryRes();
        YzNsCategory yzNsCategory = this.getById(id);
        if (yzNsCategory == null || CommonConstant.DEL_FLAG_1.equals(yzNsCategory.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(yzNsCategory, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            YzNsCategory yzNsCategory = this.getById(id);
            if (yzNsCategory == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<YzNsCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(YzNsCategory::getId, idList);
        YzNsCategory yzNsCategory = new YzNsCategory();
        yzNsCategory.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(yzNsCategory, queryWrapper);
    }
    
    
}