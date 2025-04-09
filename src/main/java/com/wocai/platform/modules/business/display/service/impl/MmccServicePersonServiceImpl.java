package com.wocai.platform.modules.business.display.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.entity.BaseEntity;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.display.entity.MmccServicePerson;
import com.wocai.platform.modules.business.display.dto.MmccServicePersonReq;
import com.wocai.platform.modules.business.display.vo.MmccServicePersonRes;
import com.wocai.platform.modules.business.display.mapper.MmccServicePersonMapper;
import com.wocai.platform.modules.business.display.service.IMmccServicePersonService;
import com.wocai.platform.modules.business.display.vo.MmccServocePersonVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 预约接待员管理
 * @Author: why
 * @Date: 2024-02-27
 * @Version: V1.0
 */
@Service
public class MmccServicePersonServiceImpl extends BaseServiceImpl<MmccServicePersonMapper, MmccServicePerson> implements IMmccServicePersonService {
    
    @Override
    public IPage<MmccServicePersonRes> queryPageList(IPage<MmccServicePersonRes> page, MmccServicePersonReq mmccServicePersonReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotEmpty(mmccServicePersonReq.getName())){
            reqParam.put("name",mmccServicePersonReq.getName());
        }
        IPage<MmccServicePersonRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccServicePersonReq mmccServicePersonReq) {
        return false;
    }

    @Override
    public void saveMain(MmccServicePersonReq mmccServicePersonReq) {
        this.doDuplicateCheck(mmccServicePersonReq);
        MmccServicePerson mmccServicePerson = new MmccServicePerson();

        LambdaQueryWrapper<MmccServicePerson> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BaseEntity::getDelFlag,CommonConstant.DEL_FLAG_0);
        List<MmccServicePerson> mmccServicePeople = this.baseMapper.selectList(queryWrapper);
        mmccServicePeople.forEach(item ->{
            if (mmccServicePersonReq.getName().equals(item.getName())){
                throw new BizException("该接待人员已存在");
            }
        });
        BeanUtils.copyProperties(mmccServicePersonReq, mmccServicePerson);
        this.save(mmccServicePerson);
    }

    @Override
    public void updateMain(MmccServicePersonReq mmccServicePersonReq) {
        MmccServicePerson mmccServicePerson = this.getById(mmccServicePersonReq.getId());
        if (mmccServicePerson == null || CommonConstant.DEL_FLAG_1.equals(mmccServicePerson.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccServicePersonReq);
        BeanUtils.copyProperties(mmccServicePersonReq, mmccServicePerson);
        this.updateById(mmccServicePerson);
    }

    @Override
    public MmccServicePersonRes getMainById(String id) {
        MmccServicePersonRes result = new MmccServicePersonRes();
        MmccServicePerson mmccServicePerson = this.getById(id);
        if (mmccServicePerson == null || CommonConstant.DEL_FLAG_1.equals(mmccServicePerson.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccServicePerson, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccServicePerson mmccServicePerson = this.getById(id);
            if (mmccServicePerson == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccServicePerson> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccServicePerson::getId, idList);
        MmccServicePerson mmccServicePerson = new MmccServicePerson();
        mmccServicePerson.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccServicePerson, queryWrapper);
    }

    @Override
    public List<MmccServocePersonVO> getServicePersonList() {
        LambdaQueryWrapper<MmccServicePerson> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BaseEntity::getDelFlag,CommonConstant.DEL_FLAG_0);
        List<MmccServicePerson> mmccServicePeople = this.baseMapper.selectList(queryWrapper);
        List<MmccServocePersonVO> list = new ArrayList<>();
        mmccServicePeople.forEach(item ->{
            MmccServocePersonVO mmccServocePersonVO = new MmccServocePersonVO();
            mmccServocePersonVO.setServicePersonId(item.getId());
            mmccServocePersonVO.setServicePersonName(item.getName());
            list.add(mmccServocePersonVO);
        });
        return list;
    }


}