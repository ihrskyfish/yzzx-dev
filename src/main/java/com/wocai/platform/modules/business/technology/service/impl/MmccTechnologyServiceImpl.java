package com.wocai.platform.modules.business.technology.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.technology.entity.MmccTechnology;
import com.wocai.platform.modules.business.technology.dto.MmccTechnologyReq;
import com.wocai.platform.modules.business.technology.entity.MmccTechnologyFiles;
import com.wocai.platform.modules.business.technology.mapper.MmccTechnologyFilesMapper;
import com.wocai.platform.modules.business.technology.vo.MmccTechnologyRes;
import com.wocai.platform.modules.business.technology.mapper.MmccTechnologyMapper;
import com.wocai.platform.modules.business.technology.service.IMmccTechnologyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 工艺栏
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Service
public class MmccTechnologyServiceImpl extends BaseServiceImpl<MmccTechnologyMapper, MmccTechnology> implements IMmccTechnologyService {

    @Resource
    private MmccTechnologyFilesMapper mmccTechnologyFilesMapper;

    @Override
    public IPage<MmccTechnologyRes> queryPageList(IPage<MmccTechnologyRes> page, MmccTechnologyReq mmccTechnologyReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotEmpty(mmccTechnologyReq.getName())){
            reqParam.put("name",mmccTechnologyReq.getName());
        }
        IPage<MmccTechnologyRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords()
                .forEach(item->{
                    if (StringUtils.isNotEmpty(item.getUrl())){
                        item.setUrl(ServerConfig.plat_upload_head_path +"/"+item.getUrl());
                    }
                    List<String> collect = mmccTechnologyFilesMapper.selectList(
                            Wrappers.<MmccTechnologyFiles>lambdaQuery()
                                    .eq(MmccTechnologyFiles::getDelFlag, 0)
                                    .eq(MmccTechnologyFiles::getTechnologyId, item.getId()))
                            .stream()
                            .map(vo -> {
                                vo.setFileUrl(ServerConfig.plat_upload_head_path +"/"+ vo.getFileUrl());
                                return vo;
                            })
                            .map(MmccTechnologyFiles::getFileUrl)
                            .collect(Collectors.toList());
                    item.setFileUrls(collect);
                });
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccTechnologyReq mmccTechnologyReq) {
        return false;
    }

    @Override
    public void saveMain(MmccTechnologyReq mmccTechnologyReq) {
        this.doDuplicateCheck(mmccTechnologyReq);
        MmccTechnology mmccTechnology = new MmccTechnology();
        BeanUtils.copyProperties(mmccTechnologyReq, mmccTechnology);
        mmccTechnology.setStatus("-1");
        this.save(mmccTechnology);
        if (mmccTechnologyReq.getFileUrls()!=null && mmccTechnologyReq.getFileUrls().size()>0){
            for (String url : mmccTechnologyReq.getFileUrls()) {
                MmccTechnologyFiles mmccTechnologyFiles = new MmccTechnologyFiles();
                mmccTechnologyFiles.setTechnologyId(mmccTechnology.getId());
                mmccTechnologyFiles.setFileUrl(url);
                mmccTechnologyFilesMapper.insert(mmccTechnologyFiles);
            }
        }

    }

    @Override
    public void updateMain(MmccTechnologyReq mmccTechnologyReq) {

        MmccTechnology mmccTechnology = this.getById(mmccTechnologyReq.getId());
        if (mmccTechnology == null || CommonConstant.DEL_FLAG_1.equals(mmccTechnology.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        mmccTechnologyFilesMapper.delete(
                Wrappers.<MmccTechnologyFiles>lambdaQuery()
                        .eq(MmccTechnologyFiles::getDelFlag,0)
                        .eq(MmccTechnologyFiles::getTechnologyId,mmccTechnologyReq.getId()));
        this.doDuplicateCheck(mmccTechnologyReq);
        BeanUtils.copyProperties(mmccTechnologyReq, mmccTechnology);
        this.updateById(mmccTechnology);
        if (mmccTechnologyReq.getFileUrls()!=null && mmccTechnologyReq.getFileUrls().size()>0){
            for (String url : mmccTechnologyReq.getFileUrls()) {
                MmccTechnologyFiles mmccTechnologyFiles = new MmccTechnologyFiles();
                mmccTechnologyFiles.setTechnologyId(mmccTechnology.getId());
                mmccTechnologyFiles.setFileUrl(url);
                mmccTechnologyFilesMapper.insert(mmccTechnologyFiles);
            }
        }

    }

    @Override
    public MmccTechnologyRes getMainById(String id) {
        MmccTechnologyRes result = new MmccTechnologyRes();
        MmccTechnology mmccTechnology = this.getById(id);
        if (mmccTechnology == null || CommonConstant.DEL_FLAG_1.equals(mmccTechnology.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccTechnology, result);
        List<String> collect = mmccTechnologyFilesMapper.selectList(
                Wrappers.<MmccTechnologyFiles>lambdaQuery()
                        .eq(MmccTechnologyFiles::getDelFlag, 0)
                        .eq(MmccTechnologyFiles::getTechnologyId, id))
                .stream()
                .map(item -> {
                    item.setFileUrl(ServerConfig.plat_upload_head_path + item.getFileUrl());
                    return item;
                })
                .map(MmccTechnologyFiles::getFileUrl)
                .collect(Collectors.toList());
        result.setFileUrls(collect);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccTechnology mmccTechnology = this.getById(id);
            if (mmccTechnology == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccTechnology> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccTechnology::getId, idList);
        MmccTechnology mmccTechnology = new MmccTechnology();
        mmccTechnology.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccTechnology, queryWrapper);
    }

    @Override
    public void enable(String id) {
        MmccTechnology mmccTechnology = baseMapper.selectById(id);
        if (mmccTechnology == null || CommonConstant.DEL_FLAG_1.equals(mmccTechnology.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        if ("1".equalsIgnoreCase(mmccTechnology.getStatus())){
            mmccTechnology.setStatus("-1");
        }else {
            mmccTechnology.setStatus("1");
            mmccTechnology.setReleaseTime(new Date());
        }
        baseMapper.updateById(mmccTechnology);
    }


}