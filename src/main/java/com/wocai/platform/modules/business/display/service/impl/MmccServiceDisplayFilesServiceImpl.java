package com.wocai.platform.modules.business.display.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplayFiles;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayFilesReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayFilesRes;
import com.wocai.platform.modules.business.display.mapper.MmccServiceDisplayFilesMapper;
import com.wocai.platform.modules.business.display.service.IMmccServiceDisplayFilesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 房间相册
 * @Author: lq
 * @Date: 2023-07-06
 * @Version: V1.0
 */
@Service
public class MmccServiceDisplayFilesServiceImpl extends BaseServiceImpl<MmccServiceDisplayFilesMapper, MmccServiceDisplayFiles> implements IMmccServiceDisplayFilesService {
    
    @Override
    public IPage<MmccServiceDisplayFilesRes> queryPageList(IPage<MmccServiceDisplayFilesRes> page, MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq) {
        Map<String, Object> reqParam = new HashMap<>();
        reqParam.put("displayId",mmccServiceDisplayFilesReq.getDisplayId());
        IPage<MmccServiceDisplayFilesRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords().forEach(item->{
            if (StringUtils.isNotEmpty(item.getFileUrl())){
                item.setFileUrl(ServerConfig.plat_upload_head_path+"/"+item.getFileUrl());
            }
        });
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq) {
        return false;
    }

    @Override
    public void saveMain(MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq) {
        this.doDuplicateCheck(mmccServiceDisplayFilesReq);
        if (StringUtils.isEmpty(mmccServiceDisplayFilesReq.getDisplayId())){
            throw new BizException("房间id为空");
        }
        MmccServiceDisplayFiles mmccServiceDisplayFiles = new MmccServiceDisplayFiles();
        BeanUtils.copyProperties(mmccServiceDisplayFilesReq, mmccServiceDisplayFiles);
        this.save(mmccServiceDisplayFiles);
    }

    @Override
    public void updateMain(MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq) {
        MmccServiceDisplayFiles mmccServiceDisplayFiles = this.getById(mmccServiceDisplayFilesReq.getId());
        if (mmccServiceDisplayFiles == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceDisplayFiles.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccServiceDisplayFilesReq);
        BeanUtils.copyProperties(mmccServiceDisplayFilesReq, mmccServiceDisplayFiles);
        this.updateById(mmccServiceDisplayFiles);
    }

    @Override
    public MmccServiceDisplayFilesRes getMainById(String id) {
        MmccServiceDisplayFilesRes result = new MmccServiceDisplayFilesRes();
        MmccServiceDisplayFiles mmccServiceDisplayFiles = this.getById(id);
        if (mmccServiceDisplayFiles == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceDisplayFiles.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccServiceDisplayFiles, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccServiceDisplayFiles mmccServiceDisplayFiles = this.getById(id);
            if (mmccServiceDisplayFiles == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccServiceDisplayFiles> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccServiceDisplayFiles::getId, idList);
        MmccServiceDisplayFiles mmccServiceDisplayFiles = new MmccServiceDisplayFiles();
        mmccServiceDisplayFiles.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccServiceDisplayFiles, queryWrapper);
    }
    
    
}