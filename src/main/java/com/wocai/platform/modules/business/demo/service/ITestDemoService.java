package com.wocai.platform.modules.business.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.demo.entity.TestDemo;
import com.wocai.platform.modules.business.demo.dto.TestDemoReq;
import com.wocai.platform.modules.business.demo.vo.TestDemoRes;

/**
 * @Description: 单表测试
 * @Author: houws
 * @Date: 2022-07-07
 * @Version: V1.0
 */
public interface ITestDemoService extends BaseService<TestDemo> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param testDemoReq
     * @return
     */
    IPage<TestDemoRes> queryPageList(IPage<TestDemoRes> page, TestDemoReq testDemoReq);

    /**
     * 数据重复校验
     * @param testDemoReq
     * @return
     */
    boolean doDuplicateCheck(TestDemoReq testDemoReq);

    /**
     * 新增
     * @param testDemoReq
     * @return
     */
    void saveMain(TestDemoReq testDemoReq);

    /**
     * 编辑
     * @param testDemoReq
     * @return
     */
    void updateMain(TestDemoReq testDemoReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    TestDemoRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}