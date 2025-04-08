package com.wocai.platform.modules.business.display.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import com.wocai.platform.modules.business.display.dto.MmccServiceUserReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceUserRes;
import com.wocai.platform.modules.business.display.mapper.MmccServiceUserMapper;
import com.wocai.platform.modules.business.display.service.IMmccServiceUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 预定管理
 * @Author: lq
 * @Date: 2023-05-25
 * @Version: V1.0
 */
@Service
public class MmccServiceUserServiceImpl extends BaseServiceImpl<MmccServiceUserMapper, MmccServiceUser> implements IMmccServiceUserService {
    
    @Override
    public IPage<MmccServiceUserRes> queryPageList(IPage<MmccServiceUserRes> page, MmccServiceUserReq mmccServiceUserReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<MmccServiceUserRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccServiceUserReq mmccServiceUserReq) {
        return false;
    }

    @Override
    public void saveMain(MmccServiceUserReq mmccServiceUserReq) {
        this.doDuplicateCheck(mmccServiceUserReq);
        MmccServiceUser mmccServiceUser = new MmccServiceUser();
        BeanUtils.copyProperties(mmccServiceUserReq, mmccServiceUser);
        this.save(mmccServiceUser);
    }

    @Override
    public void updateMain(MmccServiceUserReq mmccServiceUserReq) {
        MmccServiceUser mmccServiceUser = this.getById(mmccServiceUserReq.getId());
        if (mmccServiceUser == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceUser.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccServiceUserReq);
        BeanUtils.copyProperties(mmccServiceUserReq, mmccServiceUser);
        this.updateById(mmccServiceUser);
    }

    @Override
    public MmccServiceUserRes getMainById(String id) {
        MmccServiceUserRes result = new MmccServiceUserRes();
        MmccServiceUser mmccServiceUser = this.getById(id);
        if (mmccServiceUser == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceUser.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccServiceUser, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccServiceUser mmccServiceUser = this.getById(id);
            if (mmccServiceUser == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccServiceUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccServiceUser::getId, idList);
        MmccServiceUser mmccServiceUser = new MmccServiceUser();
        mmccServiceUser.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccServiceUser, queryWrapper);
    }
    
    
}