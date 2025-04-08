package com.wocai.platform.modules.business.publicize.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.publicize.entity.YzServerInfo;
import com.wocai.platform.modules.business.publicize.dto.YzServerInfoReq;
import com.wocai.platform.modules.business.publicize.vo.YzServerInfoRes;

/**
 * @Description: 房间预定用户信息
 * @Author: why
 * @Date: 2023-06-25
 * @Version: V1.0
 */
public interface IYzServerInfoService extends BaseService<YzServerInfo> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param yzServerInfoReq
     * @return
     */
    IPage<YzServerInfoRes> queryPageList(IPage<YzServerInfoRes> page, YzServerInfoReq yzServerInfoReq);

    /**
     * 数据重复校验
     * @param yzServerInfoReq
     * @return
     */
    boolean doDuplicateCheck(YzServerInfoReq yzServerInfoReq);

    /**
     * 新增
     * @param yzServerInfoReq
     * @return
     */
    void saveMain(YzServerInfoReq yzServerInfoReq);

    /**
     * 编辑
     * @param yzServerInfoReq
     * @return
     */
    void updateMain(YzServerInfoReq yzServerInfoReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    YzServerInfoRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}