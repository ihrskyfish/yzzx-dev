package com.wocai.platform.modules.business.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.user.dto.UserReq;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.vo.UserRes;

/**
 * @Description: user
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface IAppUserService extends BaseService<User> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param appUserReq
     * @return
     */
    IPage<UserRes> queryPageList(IPage<UserRes> page, UserReq appUserReq);

    /**
     * 数据重复校验
     * @param appUserReq
     * @return
     */
    boolean doDuplicateCheck(UserReq appUserReq);

    /**
     * 新增
     * @param appUserReq
     * @return
     */
    void saveMain(UserReq appUserReq);

    /**
     * 编辑
     * @param appUserReq
     * @return
     */
    void updateMain(UserReq appUserReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    UserRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}