package com.wocai.platform.modules.business.display.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.PositionUtil;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayReq;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplayFiles;
import com.wocai.platform.modules.business.display.entity.MmccServicePerson;
import com.wocai.platform.modules.business.display.mapper.MmccServiceDisplayFilesMapper;
import com.wocai.platform.modules.business.display.mapper.MmccServicePersonMapper;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayRes;
import com.wocai.platform.modules.business.display.mapper.MmccServiceDisplayMapper;
import com.wocai.platform.modules.business.display.service.IMmccServiceDisplayService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 服务展示
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Service
public class MmccServiceDisplayServiceImpl extends BaseServiceImpl<MmccServiceDisplayMapper, MmccServiceDisplay> implements IMmccServiceDisplayService {


    @Resource
    private MmccServiceDisplayFilesMapper mmccServiceDisplayFilesMapper;

    @Resource
    private MmccServicePersonMapper mmccServicePersonMapper;

    @Override
    public IPage<MmccServiceDisplayRes> queryPageList(IPage<MmccServiceDisplayRes> page, MmccServiceDisplayReq mmccServiceDisplayReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotEmpty(mmccServiceDisplayReq.getName())){
            reqParam.put("name",mmccServiceDisplayReq.getName());
        }
        IPage<MmccServiceDisplayRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords().forEach(item -> {
            if (StringUtils.isNotEmpty(item.getDetailsUrl())){
                item.setDetailsUrl(ServerConfig.plat_upload_head_path +"/"+ item.getDetailsUrl());
            }
            if (StringUtils.isNotEmpty(item.getCoverImageUrl())){
                item.setCoverImageUrl(ServerConfig.plat_upload_head_path +"/"+ item.getCoverImageUrl());
            }
            List<String> fileUrls = mmccServiceDisplayFilesMapper.selectList(
                    Wrappers.<MmccServiceDisplayFiles>lambdaQuery()
                            .eq(MmccServiceDisplayFiles::getDelFlag, 0)
                            .eq(MmccServiceDisplayFiles::getDisplayId, item.getId()))
                    .stream()
                    .map(vo -> {
                        vo.setFileUrl(ServerConfig.plat_upload_head_path +"/"+ vo.getFileUrl());
                        return vo.getFileUrl();
                    })
                    .collect(Collectors.toList());
            item.setFileUrls(fileUrls);
//            if(StringUtils.isNotEmpty(item.getServicePersonId())){
//                MmccServicePerson mmccServicePerson = mmccServicePersonMapper.selectById(item.getServicePersonId());
//                item.setServicePersonStr(mmccServicePerson.getName());
//            }
        });
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccServiceDisplayReq mmccServiceDisplayReq) {
        return false;
    }

    @Override
    public void saveMain(MmccServiceDisplayReq mmccServiceDisplayReq) {
        this.doDuplicateCheck(mmccServiceDisplayReq);
        MmccServiceDisplay mmccServiceDisplay = new MmccServiceDisplay();
        BeanUtils.copyProperties(mmccServiceDisplayReq, mmccServiceDisplay);
        mmccServiceDisplay.setStatus("-1");
        this.save(mmccServiceDisplay);
        if (mmccServiceDisplayReq.getFileUrls()!=null && mmccServiceDisplayReq.getFileUrls().size()>0){
            for (String url : mmccServiceDisplayReq.getFileUrls()) {
                MmccServiceDisplayFiles mmccServiceDisplayFiles = new MmccServiceDisplayFiles();
                mmccServiceDisplayFiles.setDisplayId(mmccServiceDisplay.getId());
                mmccServiceDisplayFiles.setFileUrl(url);
                mmccServiceDisplayFilesMapper.insert(mmccServiceDisplayFiles);
            }
        }
    }

    @Override
    public void updateMain(MmccServiceDisplayReq mmccServiceDisplayReq) {
        MmccServiceDisplay mmccServiceDisplay = this.getById(mmccServiceDisplayReq.getId());
        if (mmccServiceDisplay == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceDisplay.getDelFlag())) {
            throw new BizException("数据不存在");
        }

        mmccServiceDisplayFilesMapper.delete(
                Wrappers.<MmccServiceDisplayFiles>lambdaQuery()
                        .eq(MmccServiceDisplayFiles::getDelFlag, 0)
                        .eq(MmccServiceDisplayFiles::getDisplayId, mmccServiceDisplayReq.getId()));

        this.doDuplicateCheck(mmccServiceDisplayReq);
        BeanUtils.copyProperties(mmccServiceDisplayReq, mmccServiceDisplay);
        this.updateById(mmccServiceDisplay);

        for (String url : mmccServiceDisplayReq.getFileUrls()) {
            MmccServiceDisplayFiles mmccServiceDisplayFiles = new MmccServiceDisplayFiles();
            mmccServiceDisplayFiles.setDisplayId(mmccServiceDisplay.getId());
            mmccServiceDisplayFiles.setFileUrl(url);
            mmccServiceDisplayFilesMapper.insert(mmccServiceDisplayFiles);
        }
    }

    @Override
    public MmccServiceDisplayRes getMainById(String id) {
        MmccServiceDisplayRes result = new MmccServiceDisplayRes();
        MmccServiceDisplay mmccServiceDisplay = this.getById(id);
        if (mmccServiceDisplay == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceDisplay.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccServiceDisplay, result);
        List<String> fileUrls = mmccServiceDisplayFilesMapper.selectList(
                Wrappers.<MmccServiceDisplayFiles>lambdaQuery()
                        .eq(MmccServiceDisplayFiles::getDelFlag, 0)
                        .eq(MmccServiceDisplayFiles::getDisplayId, id))
                .stream()
                .map(vo -> {
                    vo.setFileUrl(ServerConfig.plat_upload_head_path + vo.getFileUrl());
                    return vo.getFileUrl();
                })
                .collect(Collectors.toList());
        result.setFileUrls(fileUrls);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccServiceDisplay mmccServiceDisplay = this.getById(id);
            if (mmccServiceDisplay == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccServiceDisplay> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccServiceDisplay::getId, idList);
        MmccServiceDisplay mmccServiceDisplay = new MmccServiceDisplay();
        mmccServiceDisplay.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccServiceDisplay, queryWrapper);
    }

    @Override
    public void enable(String id) {
        MmccServiceDisplay mmccServiceDisplay = baseMapper.selectById(id);
        if (mmccServiceDisplay == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceDisplay.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        if ("1".equalsIgnoreCase(mmccServiceDisplay.getStatus())){
            mmccServiceDisplay.setStatus("-1");
        }else {
            mmccServiceDisplay.setStatus("1");
        }
        baseMapper.updateById(mmccServiceDisplay);
    }
//
//    @Override
//    public String queryDistanceByDisplayId(String longitude, String latitude, String displayId ) {
//        MmccServiceDisplay mmccServiceDisplay = baseMapper.selectById(displayId);
//        // 获取门店经纬度
//        String longitude1 = mmccServiceDisplay.getLongitude();
//        String latitude1 = mmccServiceDisplay.getLatitude();
//
//        double d_longitude1 = Double.parseDouble(longitude1);
//        double d_latitude1 = Double.parseDouble(latitude1);
//        double d_longitude = Double.parseDouble(longitude);
//        double d_latitude = Double.parseDouble(latitude);
//        double distance1 = PositionUtil.getDistance1(d_longitude1, d_latitude1, d_longitude, d_latitude);
//        return "" + distance1;
//    }


}