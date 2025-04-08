package com.wocai.platform.modules.business.baby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.baby.dto.MmccBabyUltrasoundReq;
import com.wocai.platform.modules.business.baby.vo.MmccBabyUltrasoundRes;

/**
 * @Description: 宝宝B超
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface IMmccBabyUltrasoundService extends BaseService<MmccBabyUltrasound> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccBabyUltrasoundReq
     * @return
     */
    IPage<MmccBabyUltrasoundRes> queryPageList(IPage<MmccBabyUltrasoundRes> page, MmccBabyUltrasoundReq mmccBabyUltrasoundReq);

    /**
     * 数据重复校验
     * @param mmccBabyUltrasoundReq
     * @return
     */
    boolean doDuplicateCheck(MmccBabyUltrasoundReq mmccBabyUltrasoundReq);

    /**
     * 新增
     * @param mmccBabyUltrasoundReq
     * @return
     */
    void saveMain(MmccBabyUltrasoundReq mmccBabyUltrasoundReq);

    /**
     * 编辑
     * @param mmccBabyUltrasoundReq
     * @return
     */
    void updateMain(MmccBabyUltrasoundReq mmccBabyUltrasoundReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccBabyUltrasoundRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}