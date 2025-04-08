package com.wocai.platform.modules.business.publicize.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.publicize.entity.YzPublicizeRoom;
import com.wocai.platform.modules.business.publicize.dto.YzPublicizeRoomReq;
import com.wocai.platform.modules.business.publicize.vo.YzPublicizeRoomRes;
import com.wocai.platform.modules.business.publicize.mapper.YzPublicizeRoomMapper;
import com.wocai.platform.modules.business.publicize.service.IYzPublicizeRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 宣传预定
 * @Author: why
 * @Date: 2023-06-14
 * @Version: V1.0
 */
@Service
public class YzPublicizeRoomServiceImpl extends BaseServiceImpl<YzPublicizeRoomMapper, YzPublicizeRoom> implements IYzPublicizeRoomService {
    
    @Override
    public IPage<YzPublicizeRoomRes> queryPageList(IPage<YzPublicizeRoomRes> page, YzPublicizeRoomReq yzPublicizeRoomReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzPublicizeRoomRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(YzPublicizeRoomReq yzPublicizeRoomReq) {
        return false;
    }

    @Override
    public void saveMain(YzPublicizeRoomReq yzPublicizeRoomReq) {
        this.doDuplicateCheck(yzPublicizeRoomReq);
        YzPublicizeRoom yzPublicizeRoom = new YzPublicizeRoom();
        BeanUtils.copyProperties(yzPublicizeRoomReq, yzPublicizeRoom);
        this.save(yzPublicizeRoom);
    }

    @Override
    public void updateMain(YzPublicizeRoomReq yzPublicizeRoomReq) {
        YzPublicizeRoom yzPublicizeRoom = this.getById(yzPublicizeRoomReq.getId());
        if (yzPublicizeRoom == null || CommonConstant.DEL_FLAG_1.equals(yzPublicizeRoom.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(yzPublicizeRoomReq);
        BeanUtils.copyProperties(yzPublicizeRoomReq, yzPublicizeRoom);
        this.updateById(yzPublicizeRoom);
    }

    @Override
    public YzPublicizeRoomRes getMainById(String id) {
        YzPublicizeRoomRes result = new YzPublicizeRoomRes();
        YzPublicizeRoom yzPublicizeRoom = this.getById(id);
        if (yzPublicizeRoom == null || CommonConstant.DEL_FLAG_1.equals(yzPublicizeRoom.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(yzPublicizeRoom, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            YzPublicizeRoom yzPublicizeRoom = this.getById(id);
            if (yzPublicizeRoom == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<YzPublicizeRoom> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(YzPublicizeRoom::getId, idList);
        YzPublicizeRoom yzPublicizeRoom = new YzPublicizeRoom();
        yzPublicizeRoom.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(yzPublicizeRoom, queryWrapper);
    }
    
    
}