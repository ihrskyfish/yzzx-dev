package com.wocai.platform.modules.business.rotation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.modules.business.rotation.entity.MmccRotationChart;
import com.wocai.platform.modules.business.rotation.dto.MmccRotationChartReq;
import com.wocai.platform.modules.business.rotation.vo.MmccRotationChartRes;
import com.wocai.platform.modules.business.rotation.mapper.MmccRotationChartMapper;
import com.wocai.platform.modules.business.rotation.service.IMmccRotationChartService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 轮播图
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Service
public class MmccRotationChartServiceImpl extends BaseServiceImpl<MmccRotationChartMapper, MmccRotationChart> implements IMmccRotationChartService {

    @Override
    public IPage<MmccRotationChartRes> queryPageList(IPage<MmccRotationChartRes> page, MmccRotationChartReq mmccRotationChartReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<MmccRotationChartRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords()
                .forEach(item -> {
                    item.setFileUrl(ServerConfig.plat_upload_head_path+"/"+item.getFileUrl());
                });
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccRotationChartReq mmccRotationChartReq) {
        return false;
    }

    @Override
    public void saveMain(MmccRotationChartReq mmccRotationChartReq) {
        this.doDuplicateCheck(mmccRotationChartReq);
        MmccRotationChart mmccRotationChart = new MmccRotationChart();
        BeanUtils.copyProperties(mmccRotationChartReq, mmccRotationChart);
        this.save(mmccRotationChart);
    }

    @Override
    public void updateMain(MmccRotationChartReq mmccRotationChartReq) {
        MmccRotationChart mmccRotationChart = this.getById(mmccRotationChartReq.getId());
        if (mmccRotationChart == null || CommonConstant.DEL_FLAG_1.equals(mmccRotationChart.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccRotationChartReq);
        BeanUtils.copyProperties(mmccRotationChartReq, mmccRotationChart);
        this.updateById(mmccRotationChart);
    }

    @Override
    public MmccRotationChartRes getMainById(String id) {
        MmccRotationChartRes result = new MmccRotationChartRes();
        MmccRotationChart mmccRotationChart = this.getById(id);
        if (mmccRotationChart == null || CommonConstant.DEL_FLAG_1.equals(mmccRotationChart.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccRotationChart, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccRotationChart mmccRotationChart = this.getById(id);
            if (mmccRotationChart == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccRotationChart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccRotationChart::getId, idList);
        MmccRotationChart mmccRotationChart = new MmccRotationChart();
        mmccRotationChart.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccRotationChart, queryWrapper);
    }


}