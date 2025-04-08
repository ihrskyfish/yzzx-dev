package com.wocai.platform.modules.business.publicize.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.publicize.entity.YzPreRoom;
import com.wocai.platform.modules.business.publicize.dto.YzPreRoomReq;
import com.wocai.platform.modules.business.publicize.vo.YzPreRoomRes;

/**
 * @Description: 预定房间信息
 * @Author: why
 * @Date: 2023-06-14
 * @Version: V1.0
 */
public interface IYzPreRoomService extends BaseService<YzPreRoom> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param yzPreRoomReq
     * @return
     */
    IPage<YzPreRoomRes> queryPageList(IPage<YzPreRoomRes> page, YzPreRoomReq yzPreRoomReq);

    /**
     * 数据重复校验
     * @param yzPreRoomReq
     * @return
     */
    boolean doDuplicateCheck(YzPreRoomReq yzPreRoomReq);

    /**
     * 新增
     * @param yzPreRoomReq
     * @return
     */
    void saveMain(YzPreRoomReq yzPreRoomReq);

    /**
     * 编辑
     * @param yzPreRoomReq
     * @return
     */
    void updateMain(YzPreRoomReq yzPreRoomReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    YzPreRoomRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}