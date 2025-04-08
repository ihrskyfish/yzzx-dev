package com.wocai.platform.modules.business.rotation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.rotation.entity.MmccRotationChart;
import com.wocai.platform.modules.business.rotation.dto.MmccRotationChartReq;
import com.wocai.platform.modules.business.rotation.vo.MmccRotationChartRes;

/**
 * @Description: 轮播图
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface IMmccRotationChartService extends BaseService<MmccRotationChart> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccRotationChartReq
     * @return
     */
    IPage<MmccRotationChartRes> queryPageList(IPage<MmccRotationChartRes> page, MmccRotationChartReq mmccRotationChartReq);

    /**
     * 数据重复校验
     * @param mmccRotationChartReq
     * @return
     */
    boolean doDuplicateCheck(MmccRotationChartReq mmccRotationChartReq);

    /**
     * 新增
     * @param mmccRotationChartReq
     * @return
     */
    void saveMain(MmccRotationChartReq mmccRotationChartReq);

    /**
     * 编辑
     * @param mmccRotationChartReq
     * @return
     */
    void updateMain(MmccRotationChartReq mmccRotationChartReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccRotationChartRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}