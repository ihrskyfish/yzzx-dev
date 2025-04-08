package com.wocai.platform.modules.business.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.activity.dto.MmccActivityResultsDto;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import com.wocai.platform.modules.business.activity.dto.MmccActivityReq;
import com.wocai.platform.modules.business.activity.vo.MmccActivityRes;
import com.wocai.platform.modules.business.activity.vo.MmccActivityUserListVo;

/**
 * @Description: 孕期活动
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface IMmccActivityService extends BaseService<MmccActivity> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccActivityReq
     * @return
     */
    IPage<MmccActivityRes> queryPageList(IPage<MmccActivityRes> page, MmccActivityReq mmccActivityReq);

    /**
     * 数据重复校验
     * @param mmccActivityReq
     * @return
     */
    boolean doDuplicateCheck(MmccActivityReq mmccActivityReq);

    /**
     * 新增
     * @param mmccActivityReq
     * @return
     */
    void saveMain(MmccActivityReq mmccActivityReq);

    /**
     * 编辑
     * @param mmccActivityReq
     * @return
     */
    void updateMain(MmccActivityReq mmccActivityReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccActivityRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    IPage<MmccActivityUserListVo> activityUserList(Page<MmccActivityUserListVo> page,String id);

    void activityResults(MmccActivityResultsDto mmccActivityResultsDto);

    void release(String id);
}