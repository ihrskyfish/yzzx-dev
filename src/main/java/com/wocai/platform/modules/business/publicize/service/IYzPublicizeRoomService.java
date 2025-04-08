package com.wocai.platform.modules.business.publicize.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.publicize.entity.YzPublicizeRoom;
import com.wocai.platform.modules.business.publicize.dto.YzPublicizeRoomReq;
import com.wocai.platform.modules.business.publicize.vo.YzPublicizeRoomRes;

/**
 * @Description: 宣传预定
 * @Author: why
 * @Date: 2023-06-14
 * @Version: V1.0
 */
public interface IYzPublicizeRoomService extends BaseService<YzPublicizeRoom> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param yzPublicizeRoomReq
     * @return
     */
    IPage<YzPublicizeRoomRes> queryPageList(IPage<YzPublicizeRoomRes> page, YzPublicizeRoomReq yzPublicizeRoomReq);

    /**
     * 数据重复校验
     * @param yzPublicizeRoomReq
     * @return
     */
    boolean doDuplicateCheck(YzPublicizeRoomReq yzPublicizeRoomReq);

    /**
     * 新增
     * @param yzPublicizeRoomReq
     * @return
     */
    void saveMain(YzPublicizeRoomReq yzPublicizeRoomReq);

    /**
     * 编辑
     * @param yzPublicizeRoomReq
     * @return
     */
    void updateMain(YzPublicizeRoomReq yzPublicizeRoomReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    YzPublicizeRoomRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}