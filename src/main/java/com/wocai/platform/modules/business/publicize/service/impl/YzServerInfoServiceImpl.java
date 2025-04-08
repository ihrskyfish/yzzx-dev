package com.wocai.platform.modules.business.publicize.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.publicize.entity.YzServerInfo;
import com.wocai.platform.modules.business.publicize.dto.YzServerInfoReq;
import com.wocai.platform.modules.business.publicize.vo.YzServerInfoRes;
import com.wocai.platform.modules.business.publicize.mapper.YzServerInfoMapper;
import com.wocai.platform.modules.business.publicize.service.IYzServerInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 房间预定用户信息
 * @Author: why
 * @Date: 2023-06-25
 * @Version: V1.0
 */
@Service
public class YzServerInfoServiceImpl extends BaseServiceImpl<YzServerInfoMapper, YzServerInfo> implements IYzServerInfoService {
    
    @Override
    public IPage<YzServerInfoRes> queryPageList(IPage<YzServerInfoRes> page, YzServerInfoReq yzServerInfoReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzServerInfoRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(YzServerInfoReq yzServerInfoReq) {
        return false;
    }

    @Override
    public void saveMain(YzServerInfoReq yzServerInfoReq) {
        this.doDuplicateCheck(yzServerInfoReq);
        YzServerInfo yzServerInfo = new YzServerInfo();
        BeanUtils.copyProperties(yzServerInfoReq, yzServerInfo);
        this.save(yzServerInfo);
    }

    @Override
    public void updateMain(YzServerInfoReq yzServerInfoReq) {
        YzServerInfo yzServerInfo = this.getById(yzServerInfoReq.getId());
        if (yzServerInfo == null || CommonConstant.DEL_FLAG_1.equals(yzServerInfo.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(yzServerInfoReq);
        BeanUtils.copyProperties(yzServerInfoReq, yzServerInfo);
        this.updateById(yzServerInfo);
    }

    @Override
    public YzServerInfoRes getMainById(String id) {
        YzServerInfoRes result = new YzServerInfoRes();
        YzServerInfo yzServerInfo = this.getById(id);
        if (yzServerInfo == null || CommonConstant.DEL_FLAG_1.equals(yzServerInfo.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(yzServerInfo, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            YzServerInfo yzServerInfo = this.getById(id);
            if (yzServerInfo == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<YzServerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(YzServerInfo::getId, idList);
        YzServerInfo yzServerInfo = new YzServerInfo();
        yzServerInfo.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(yzServerInfo, queryWrapper);
    }
    
    
}