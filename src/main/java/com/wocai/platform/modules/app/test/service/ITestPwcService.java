package com.wocai.platform.modules.app.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.app.test.entity.TestPwc;
import com.wocai.platform.modules.app.test.dto.TestPwcReq;
import com.wocai.platform.modules.app.test.vo.TestPwcRes;

/**
 * @Description: 公益栏增删改查
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
public interface ITestPwcService extends BaseService<TestPwc> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param testPwcReq
     * @return
     */
    IPage<TestPwcRes> queryPageList(IPage<TestPwcRes> page, TestPwcReq testPwcReq);

    /**
     * 数据重复校验
     * @param testPwcReq
     * @return
     */
    boolean doDuplicateCheck(TestPwcReq testPwcReq);

    /**
     * 新增
     * @param testPwcReq
     * @return
     */
    void saveMain(TestPwcReq testPwcReq);

    /**
     * 编辑
     * @param testPwcReq
     * @return
     */
    void updateMain(TestPwcReq testPwcReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    TestPwcRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}