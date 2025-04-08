package com.wocai.platform.modules.business.display.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplayFiles;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayFilesReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayFilesRes;

/**
 * @Description: 房间相册
 * @Author: lq
 * @Date: 2023-07-06
 * @Version: V1.0
 */
public interface IMmccServiceDisplayFilesService extends BaseService<MmccServiceDisplayFiles> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccServiceDisplayFilesReq
     * @return
     */
    IPage<MmccServiceDisplayFilesRes> queryPageList(IPage<MmccServiceDisplayFilesRes> page, MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq);

    /**
     * 数据重复校验
     * @param mmccServiceDisplayFilesReq
     * @return
     */
    boolean doDuplicateCheck(MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq);

    /**
     * 新增
     * @param mmccServiceDisplayFilesReq
     * @return
     */
    void saveMain(MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq);

    /**
     * 编辑
     * @param mmccServiceDisplayFilesReq
     * @return
     */
    void updateMain(MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccServiceDisplayFilesRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}