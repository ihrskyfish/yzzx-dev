package com.wocai.platform.modules.business.nurse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.nurse.entity.YzNsKnowledge;
import com.wocai.platform.modules.business.nurse.dto.YzNsKnowledgeReq;
import com.wocai.platform.modules.business.nurse.vo.YzNsKnowledgeRes;

/**
 * @Description: 护理知识
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
public interface IYzNsKnowledgeService extends BaseService<YzNsKnowledge> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param yzNsKnowledgeReq
     * @return
     */
    IPage<YzNsKnowledgeRes> queryPageList(IPage<YzNsKnowledgeRes> page, YzNsKnowledgeReq yzNsKnowledgeReq);

    /**
     * 数据重复校验
     * @param yzNsKnowledgeReq
     * @return
     */
    boolean doDuplicateCheck(YzNsKnowledgeReq yzNsKnowledgeReq);

    /**
     * 新增
     * @param yzNsKnowledgeReq
     * @return
     */
    void saveMain(YzNsKnowledgeReq yzNsKnowledgeReq);

    /**
     * 编辑
     * @param yzNsKnowledgeReq
     * @return
     */
    void updateMain(YzNsKnowledgeReq yzNsKnowledgeReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    YzNsKnowledgeRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    IPage<YzNsKnowledgeRes> queryPageListByCategoryId(Page<YzNsKnowledgeRes> page, String id);
}