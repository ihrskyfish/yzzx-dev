package com.wocai.platform.modules.business.publicize.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.publicize.entity.YzUserInfo;
import com.wocai.platform.modules.business.publicize.dto.YzUserInfoReq;
import com.wocai.platform.modules.business.publicize.vo.YzUserInfoRes;
import com.wocai.platform.modules.business.publicize.mapper.YzUserInfoMapper;
import com.wocai.platform.modules.business.publicize.service.IYzUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 用户信息
 * @Author: why
 * @Date: 2023-06-25
 * @Version: V1.0
 */
@Service
public class YzUserInfoServiceImpl extends BaseServiceImpl<YzUserInfoMapper, YzUserInfo> implements IYzUserInfoService {
    
    @Override
    public IPage<YzUserInfoRes> queryPageList(IPage<YzUserInfoRes> page, YzUserInfoReq yzUserInfoReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzUserInfoRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(YzUserInfoReq yzUserInfoReq) {
        return false;
    }

    @Override
    public void saveMain(YzUserInfoReq yzUserInfoReq) {
        this.doDuplicateCheck(yzUserInfoReq);
        YzUserInfo yzUserInfo = new YzUserInfo();
        BeanUtils.copyProperties(yzUserInfoReq, yzUserInfo);
        this.save(yzUserInfo);
    }

    @Override
    public void updateMain(YzUserInfoReq yzUserInfoReq) {
        YzUserInfo yzUserInfo = this.getById(yzUserInfoReq.getId());
        if (yzUserInfo == null || CommonConstant.DEL_FLAG_1.equals(yzUserInfo.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(yzUserInfoReq);
        BeanUtils.copyProperties(yzUserInfoReq, yzUserInfo);
        this.updateById(yzUserInfo);
    }

    @Override
    public YzUserInfoRes getMainById(String id) {
        YzUserInfoRes result = new YzUserInfoRes();
        YzUserInfo yzUserInfo = this.getById(id);
        if (yzUserInfo == null || CommonConstant.DEL_FLAG_1.equals(yzUserInfo.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(yzUserInfo, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            YzUserInfo yzUserInfo = this.getById(id);
            if (yzUserInfo == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<YzUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(YzUserInfo::getId, idList);
        YzUserInfo yzUserInfo = new YzUserInfo();
        yzUserInfo.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(yzUserInfo, queryWrapper);
    }
    
    
}