package com.wocai.platform.modules.business.nurse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseType;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseTypeReq;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseDetailsMapper;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseTypeRes;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseTypeMapper;
import com.wocai.platform.modules.business.nurse.service.IMmccNurseTypeService;
import com.wocai.platform.modules.business.nurse.vo.MmccTypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 知识护理类型
 * @Author: lq
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Service
public class MmccNurseTypeServiceImpl extends BaseServiceImpl<MmccNurseTypeMapper, MmccNurseType> implements IMmccNurseTypeService {

    @Resource
    private MmccNurseDetailsMapper mmccNurseDetailsMapper;

    @Override
    public IPage<MmccNurseTypeRes> queryPageList(IPage<MmccNurseTypeRes> page, MmccNurseTypeReq mmccNurseTypeReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<MmccNurseTypeRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccNurseTypeReq mmccNurseTypeReq) {
        return false;
    }

    @Override
    public void saveMain(MmccNurseTypeReq mmccNurseTypeReq) {
        this.doDuplicateCheck(mmccNurseTypeReq);
        MmccNurseType mmccNurseType = new MmccNurseType();
        BeanUtils.copyProperties(mmccNurseTypeReq, mmccNurseType);
        this.save(mmccNurseType);
    }

    @Override
    public void updateMain(MmccNurseTypeReq mmccNurseTypeReq) {
        MmccNurseType mmccNurseType = this.getById(mmccNurseTypeReq.getId());
        if (mmccNurseType == null || CommonConstant.DEL_FLAG_1.equals(mmccNurseType.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccNurseTypeReq);
        BeanUtils.copyProperties(mmccNurseTypeReq, mmccNurseType);
        this.updateById(mmccNurseType);
    }

    @Override
    public MmccNurseTypeRes getMainById(String id) {
        MmccNurseTypeRes result = new MmccNurseTypeRes();
        MmccNurseType mmccNurseType = this.getById(id);
        if (mmccNurseType == null || CommonConstant.DEL_FLAG_1.equals(mmccNurseType.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccNurseType, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccNurseType mmccNurseType = this.getById(id);
            if (mmccNurseType == null) {
                throw new BizException("数据不存在");
            }
            Integer count = mmccNurseDetailsMapper.selectCount(
                    Wrappers.<MmccNurseDetails>lambdaQuery()
                            .eq(MmccNurseDetails::getTypeId, id)
                            .eq(MmccNurseDetails::getDelFlag, 0));
            if (count>0){
                throw new BizException("类别名称为"+mmccNurseType.getName()+"的该类型下有知识内容，请先删除知识内容再删除该知识类别");
            }

        }
        LambdaQueryWrapper<MmccNurseType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccNurseType::getId, idList);
        MmccNurseType mmccNurseType = new MmccNurseType();
        mmccNurseType.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccNurseType, queryWrapper);
    }

    @Override
    public void enable(String id) {
        MmccNurseType mmccNurseType = baseMapper.selectById(id);
        if (mmccNurseType == null || CommonConstant.DEL_FLAG_1.equals(mmccNurseType.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        if ("1".equalsIgnoreCase(mmccNurseType.getStatus())){
            mmccNurseType.setStatus("-1");
        }else {
            mmccNurseType.setStatus("1");
        }
        baseMapper.updateById(mmccNurseType);

    }

    @Override
    public List<MmccTypeVo> getAllTypeList() {
        List<MmccTypeVo> result = baseMapper.selectList(
                Wrappers.<MmccNurseType>lambdaQuery()
                        .eq(MmccNurseType::getDelFlag, 0).eq(MmccNurseType::getStatus, 1)
                        .orderByDesc(MmccNurseType::getCreateTime))
                .stream()
                .map(item -> {
                    MmccTypeVo mmccTypeVo = new MmccTypeVo();
                    BeanUtils.copyProperties(item, mmccTypeVo);
                    return mmccTypeVo;
                })
                .collect(Collectors.toList());
        return result;
    }


}