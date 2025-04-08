package com.wocai.platform.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.system.entity.SysDatabaseBack;
import com.wocai.platform.modules.system.dto.SysDatabaseBackReq;
import com.wocai.platform.modules.system.vo.SysDatabaseBackRes;

/**
 * @Description: 数据备份
 * @Author: houws
 * @Date: 2022-07-04
 * @Version: V1.0
 */
public interface ISysDatabaseBackService extends BaseService<SysDatabaseBack> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param sysDatabaseBackReq
     * @return
     */
    IPage<SysDatabaseBackRes> queryPageList(IPage<SysDatabaseBackRes> page, SysDatabaseBackReq sysDatabaseBackReq);

    /**
     * 数据重复校验
     * @param sysDatabaseBackReq
     * @return
     */
    boolean doDuplicateCheck(SysDatabaseBackReq sysDatabaseBackReq);

    /**
     * 新增
     * @param sysDatabaseBackReq
     * @return
     */
    void saveMain(SysDatabaseBackReq sysDatabaseBackReq);

    /**
     * 编辑
     * @param sysDatabaseBackReq
     * @return
     */
    void updateMain(SysDatabaseBackReq sysDatabaseBackReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    SysDatabaseBackRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}