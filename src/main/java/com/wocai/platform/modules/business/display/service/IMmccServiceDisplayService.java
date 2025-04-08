package com.wocai.platform.modules.business.display.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayRes;

/**
 * @Description: 服务展示
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface IMmccServiceDisplayService extends BaseService<MmccServiceDisplay> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccServiceDisplayReq
     * @return
     */
    IPage<MmccServiceDisplayRes> queryPageList(IPage<MmccServiceDisplayRes> page, MmccServiceDisplayReq mmccServiceDisplayReq);

    /**
     * 数据重复校验
     * @param mmccServiceDisplayReq
     * @return
     */
    boolean doDuplicateCheck(MmccServiceDisplayReq mmccServiceDisplayReq);

    /**
     * 新增
     * @param mmccServiceDisplayReq
     * @return
     */
    void saveMain(MmccServiceDisplayReq mmccServiceDisplayReq);

    /**
     * 编辑
     * @param mmccServiceDisplayReq
     * @return
     */
    void updateMain(MmccServiceDisplayReq mmccServiceDisplayReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccServiceDisplayRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    void enable(String id);

//    String queryDistanceByDisplayId(String longitude, String latitude, String displayId);

}