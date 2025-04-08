package com.wocai.platform.modules.business.publicize.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.publicize.entity.YzUserInfo;
import com.wocai.platform.modules.business.publicize.dto.YzUserInfoReq;
import com.wocai.platform.modules.business.publicize.vo.YzUserInfoRes;

/**
 * @Description: 用户信息
 * @Author: why
 * @Date: 2023-06-25
 * @Version: V1.0
 */
public interface IYzUserInfoService extends BaseService<YzUserInfo> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param yzUserInfoReq
     * @return
     */
    IPage<YzUserInfoRes> queryPageList(IPage<YzUserInfoRes> page, YzUserInfoReq yzUserInfoReq);

    /**
     * 数据重复校验
     * @param yzUserInfoReq
     * @return
     */
    boolean doDuplicateCheck(YzUserInfoReq yzUserInfoReq);

    /**
     * 新增
     * @param yzUserInfoReq
     * @return
     */
    void saveMain(YzUserInfoReq yzUserInfoReq);

    /**
     * 编辑
     * @param yzUserInfoReq
     * @return
     */
    void updateMain(YzUserInfoReq yzUserInfoReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    YzUserInfoRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}