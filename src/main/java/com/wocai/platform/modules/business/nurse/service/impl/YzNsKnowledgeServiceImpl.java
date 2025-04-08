package com.wocai.platform.modules.business.nurse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.nurse.entity.YzNsKnowledge;
import com.wocai.platform.modules.business.nurse.dto.YzNsKnowledgeReq;
import com.wocai.platform.modules.business.nurse.vo.YzNsKnowledgeRes;
import com.wocai.platform.modules.business.nurse.mapper.YzNsKnowledgeMapper;
import com.wocai.platform.modules.business.nurse.service.IYzNsKnowledgeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 护理知识
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Service
public class YzNsKnowledgeServiceImpl extends BaseServiceImpl<YzNsKnowledgeMapper, YzNsKnowledge> implements IYzNsKnowledgeService {
    
    @Override
    public IPage<YzNsKnowledgeRes> queryPageList(IPage<YzNsKnowledgeRes> page, YzNsKnowledgeReq yzNsKnowledgeReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzNsKnowledgeRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(YzNsKnowledgeReq yzNsKnowledgeReq) {
        return false;
    }

    @Override
    public void saveMain(YzNsKnowledgeReq yzNsKnowledgeReq) {
        this.doDuplicateCheck(yzNsKnowledgeReq);
        YzNsKnowledge yzNsKnowledge = new YzNsKnowledge();
        BeanUtils.copyProperties(yzNsKnowledgeReq, yzNsKnowledge);
        this.save(yzNsKnowledge);
    }

    @Override
    public void updateMain(YzNsKnowledgeReq yzNsKnowledgeReq) {
        YzNsKnowledge yzNsKnowledge = this.getById(yzNsKnowledgeReq.getId());
        if (yzNsKnowledge == null || CommonConstant.DEL_FLAG_1.equals(yzNsKnowledge.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(yzNsKnowledgeReq);
        BeanUtils.copyProperties(yzNsKnowledgeReq, yzNsKnowledge);
        this.updateById(yzNsKnowledge);
    }

    @Override
    public YzNsKnowledgeRes getMainById(String id) {
        YzNsKnowledgeRes result = new YzNsKnowledgeRes();
        YzNsKnowledge yzNsKnowledge = this.getById(id);
        if (yzNsKnowledge == null || CommonConstant.DEL_FLAG_1.equals(yzNsKnowledge.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(yzNsKnowledge, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            YzNsKnowledge yzNsKnowledge = this.getById(id);
            if (yzNsKnowledge == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<YzNsKnowledge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(YzNsKnowledge::getId, idList);
        YzNsKnowledge yzNsKnowledge = new YzNsKnowledge();
        yzNsKnowledge.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(yzNsKnowledge, queryWrapper);
    }

    @Override
    public IPage<YzNsKnowledgeRes> queryPageListByCategoryId(Page<YzNsKnowledgeRes> page, String id) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzNsKnowledgeRes> resultList = this.baseMapper.queryPageByCategoryId(page, reqParam,id);
        return resultList;
    }


}