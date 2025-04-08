package com.wocai.platform.modules.business.nurse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.nurse.entity.YzNsCategory;
import com.wocai.platform.modules.business.nurse.dto.YzNsCategoryReq;
import com.wocai.platform.modules.business.nurse.vo.YzNsCategoryRes;

/**
 * @Description: 护理知识分类
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
public interface IYzNsCategoryService extends BaseService<YzNsCategory> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param yzNsCategoryReq
     * @return
     */
    IPage<YzNsCategoryRes> queryPageList(IPage<YzNsCategoryRes> page, YzNsCategoryReq yzNsCategoryReq);

    /**
     * 数据重复校验
     * @param yzNsCategoryReq
     * @return
     */
    boolean doDuplicateCheck(YzNsCategoryReq yzNsCategoryReq);

    /**
     * 新增
     * @param yzNsCategoryReq
     * @return
     */
    void saveMain(YzNsCategoryReq yzNsCategoryReq);

    /**
     * 编辑
     * @param yzNsCategoryReq
     * @return
     */
    void updateMain(YzNsCategoryReq yzNsCategoryReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    YzNsCategoryRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}