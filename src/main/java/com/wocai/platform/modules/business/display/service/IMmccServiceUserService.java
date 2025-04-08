package com.wocai.platform.modules.business.display.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import com.wocai.platform.modules.business.display.dto.MmccServiceUserReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceUserRes;

/**
 * @Description: 预定管理
 * @Author: lq
 * @Date: 2023-05-25
 * @Version: V1.0
 */
public interface IMmccServiceUserService extends BaseService<MmccServiceUser> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccServiceUserReq
     * @return
     */
    IPage<MmccServiceUserRes> queryPageList(IPage<MmccServiceUserRes> page, MmccServiceUserReq mmccServiceUserReq);

    /**
     * 数据重复校验
     * @param mmccServiceUserReq
     * @return
     */
    boolean doDuplicateCheck(MmccServiceUserReq mmccServiceUserReq);

    /**
     * 新增
     * @param mmccServiceUserReq
     * @return
     */
    void saveMain(MmccServiceUserReq mmccServiceUserReq);

    /**
     * 编辑
     * @param mmccServiceUserReq
     * @return
     */
    void updateMain(MmccServiceUserReq mmccServiceUserReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccServiceUserRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}