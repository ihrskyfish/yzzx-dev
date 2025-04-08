package com.wocai.platform.modules.business.technology.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.technology.entity.MmccTechnology;
import com.wocai.platform.modules.business.technology.dto.MmccTechnologyReq;
import com.wocai.platform.modules.business.technology.vo.MmccTechnologyRes;

/**
 * @Description: 工艺栏
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface IMmccTechnologyService extends BaseService<MmccTechnology> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccTechnologyReq
     * @return
     */
    IPage<MmccTechnologyRes> queryPageList(IPage<MmccTechnologyRes> page, MmccTechnologyReq mmccTechnologyReq);

    /**
     * 数据重复校验
     * @param mmccTechnologyReq
     * @return
     */
    boolean doDuplicateCheck(MmccTechnologyReq mmccTechnologyReq);

    /**
     * 新增
     * @param mmccTechnologyReq
     * @return
     */
    void saveMain(MmccTechnologyReq mmccTechnologyReq);

    /**
     * 编辑
     * @param mmccTechnologyReq
     * @return
     */
    void updateMain(MmccTechnologyReq mmccTechnologyReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccTechnologyRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    void enable(String id);
}