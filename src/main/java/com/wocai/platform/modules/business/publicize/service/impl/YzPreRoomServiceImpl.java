package com.wocai.platform.modules.business.publicize.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.publicize.entity.YzPreRoom;
import com.wocai.platform.modules.business.publicize.dto.YzPreRoomReq;
import com.wocai.platform.modules.business.publicize.vo.YzPreRoomRes;
import com.wocai.platform.modules.business.publicize.mapper.YzPreRoomMapper;
import com.wocai.platform.modules.business.publicize.service.IYzPreRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 预定房间信息
 * @Author: why
 * @Date: 2023-06-14
 * @Version: V1.0
 */
@Service
public class YzPreRoomServiceImpl extends BaseServiceImpl<YzPreRoomMapper, YzPreRoom> implements IYzPreRoomService {
    
    @Override
    public IPage<YzPreRoomRes> queryPageList(IPage<YzPreRoomRes> page, YzPreRoomReq yzPreRoomReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<YzPreRoomRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(YzPreRoomReq yzPreRoomReq) {
        return false;
    }

    @Override
    public void saveMain(YzPreRoomReq yzPreRoomReq) {
        this.doDuplicateCheck(yzPreRoomReq);
        YzPreRoom yzPreRoom = new YzPreRoom();
        BeanUtils.copyProperties(yzPreRoomReq, yzPreRoom);
        this.save(yzPreRoom);
    }

    @Override
    public void updateMain(YzPreRoomReq yzPreRoomReq) {
        YzPreRoom yzPreRoom = this.getById(yzPreRoomReq.getId());
        if (yzPreRoom == null || CommonConstant.DEL_FLAG_1.equals(yzPreRoom.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(yzPreRoomReq);
        BeanUtils.copyProperties(yzPreRoomReq, yzPreRoom);
        this.updateById(yzPreRoom);
    }

    @Override
    public YzPreRoomRes getMainById(String id) {
        YzPreRoomRes result = new YzPreRoomRes();
        YzPreRoom yzPreRoom = this.getById(id);
        if (yzPreRoom == null || CommonConstant.DEL_FLAG_1.equals(yzPreRoom.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(yzPreRoom, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            YzPreRoom yzPreRoom = this.getById(id);
            if (yzPreRoom == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<YzPreRoom> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(YzPreRoom::getId, idList);
        YzPreRoom yzPreRoom = new YzPreRoom();
        yzPreRoom.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(yzPreRoom, queryWrapper);
    }
    
    
}