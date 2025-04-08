package com.wocai.platform.modules.business.nurse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseDetailsReq;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseDetailsRes;

/**
 * @Description: 护理内容
 * @Author: lq
 * @Date: 2023-05-23
 * @Version: V1.0
 */
public interface IMmccNurseDetailsService extends BaseService<MmccNurseDetails> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccNurseDetailsReq
     * @return
     */
    IPage<MmccNurseDetailsRes> queryPageList(IPage<MmccNurseDetailsRes> page, MmccNurseDetailsReq mmccNurseDetailsReq);

    /**
     * 数据重复校验
     * @param mmccNurseDetailsReq
     * @return
     */
    boolean doDuplicateCheck(MmccNurseDetailsReq mmccNurseDetailsReq);

    /**
     * 新增
     * @param mmccNurseDetailsReq
     * @return
     */
    void saveMain(MmccNurseDetailsReq mmccNurseDetailsReq);

    /**
     * 编辑
     * @param mmccNurseDetailsReq
     * @return
     */
    void updateMain(MmccNurseDetailsReq mmccNurseDetailsReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccNurseDetailsRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    void essenceById(String id);

}