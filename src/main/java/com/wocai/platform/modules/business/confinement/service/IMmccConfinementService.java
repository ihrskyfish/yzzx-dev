package com.wocai.platform.modules.business.confinement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import com.wocai.platform.modules.business.confinement.dto.MmccConfinementReq;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementUser;
import com.wocai.platform.modules.business.confinement.vo.MmccConfinementRes;
import com.wocai.platform.modules.business.confinement.vo.MmccUserLikesVo;

/**
 * @Description: 产期圈
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface IMmccConfinementService extends BaseService<MmccConfinement> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccConfinementReq
     * @return
     */
    IPage<MmccConfinementRes> queryPageList(IPage<MmccConfinementRes> page, MmccConfinementReq mmccConfinementReq);

    /**
     * 数据重复校验
     * @param mmccConfinementReq
     * @return
     */
    boolean doDuplicateCheck(MmccConfinementReq mmccConfinementReq);

    /**
     * 新增
     * @param mmccConfinementReq
     * @return
     */
    void saveMain(MmccConfinementReq mmccConfinementReq);

    /**
     * 编辑
     * @param mmccConfinementReq
     * @return
     */
    void updateMain(MmccConfinementReq mmccConfinementReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccConfinementRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    IPage<MmccUserLikesVo> userLikes(Page<MmccConfinementUser> page, String id);
}