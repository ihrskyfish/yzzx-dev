package com.wocai.platform.modules.business.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.health.entity.YzPregHealth;
import com.wocai.platform.modules.business.health.dto.YzPregHealthReq;
import com.wocai.platform.modules.business.health.vo.YzPregHealthRes;

/**
 * @Description: 孕期健康
 * @Author: why
 * @Date: 2023-06-16
 * @Version: V1.0
 */
public interface IYzPregHealthService extends BaseService<YzPregHealth> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param yzPregHealthReq
     * @return
     */
    IPage<YzPregHealthRes> queryPageList(IPage<YzPregHealthRes> page, YzPregHealthReq yzPregHealthReq);

    /**
     * 数据重复校验
     * @param yzPregHealthReq
     * @return
     */
    boolean doDuplicateCheck(YzPregHealthReq yzPregHealthReq);

    /**
     * 新增
     * @param yzPregHealthReq
     * @return
     */
    void saveMain(YzPregHealthReq yzPregHealthReq);

    /**
     * 编辑
     * @param yzPregHealthReq
     * @return
     */
    void updateMain(YzPregHealthReq yzPregHealthReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    YzPregHealthRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}