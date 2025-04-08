package com.wocai.platform.modules.business.communication.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import com.wocai.platform.modules.business.communication.dto.MmccCommunicationCircleReq;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationUser;
import com.wocai.platform.modules.business.communication.vo.MmccCommunicationCircleRes;
import com.wocai.platform.modules.business.communication.vo.MmccUserLeaveMessageVo;

/**
 * @Description: 交流圈话题
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface IMmccCommunicationCircleService extends BaseService<MmccCommunicationCircle> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccCommunicationCircleReq
     * @return
     */
    IPage<MmccCommunicationCircleRes> queryPageList(IPage<MmccCommunicationCircleRes> page, MmccCommunicationCircleReq mmccCommunicationCircleReq);

    /**
     * 数据重复校验
     * @param mmccCommunicationCircleReq
     * @return
     */
    boolean doDuplicateCheck(MmccCommunicationCircleReq mmccCommunicationCircleReq);

    /**
     * 新增
     * @param mmccCommunicationCircleReq
     * @return
     */
    void saveMain(MmccCommunicationCircleReq mmccCommunicationCircleReq);

    /**
     * 编辑
     * @param mmccCommunicationCircleReq
     * @return
     */
    void updateMain(MmccCommunicationCircleReq mmccCommunicationCircleReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccCommunicationCircleRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    IPage<MmccUserLeaveMessageVo> userLeaveMessage(Page<MmccCommunicationUser> page, String id);

    void setSelected(String id);
}