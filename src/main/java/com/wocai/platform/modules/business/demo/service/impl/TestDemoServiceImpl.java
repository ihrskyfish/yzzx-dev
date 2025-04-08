package com.wocai.platform.modules.business.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.demo.entity.TestDemo;
import com.wocai.platform.modules.business.demo.dto.TestDemoReq;
import com.wocai.platform.modules.business.demo.vo.TestDemoRes;
import com.wocai.platform.modules.business.demo.mapper.TestDemoMapper;
import com.wocai.platform.modules.business.demo.service.ITestDemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 单表测试
 * @Author: houws
 * @Date: 2022-07-07
 * @Version: V1.0
 */
@Service
public class TestDemoServiceImpl extends BaseServiceImpl<TestDemoMapper, TestDemo> implements ITestDemoService {
    
    @Override
    public IPage<TestDemoRes> queryPageList(IPage<TestDemoRes> page, TestDemoReq testDemoReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<TestDemoRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(TestDemoReq testDemoReq) {
        return false;
    }

    @Override
    public void saveMain(TestDemoReq testDemoReq) {
        this.doDuplicateCheck(testDemoReq);
        TestDemo testDemo = new TestDemo();
        BeanUtils.copyProperties(testDemoReq, testDemo);
        this.save(testDemo);
    }

    @Override
    public void updateMain(TestDemoReq testDemoReq) {
        TestDemo testDemo = this.getById(testDemoReq.getId());
        if (testDemo == null || CommonConstant.DEL_FLAG_1.equals(testDemo.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(testDemoReq);
        BeanUtils.copyProperties(testDemoReq, testDemo);
        this.updateById(testDemo);
    }

    @Override
    public TestDemoRes getMainById(String id) {
        TestDemoRes result = new TestDemoRes();
        TestDemo testDemo = this.getById(id);
        if (testDemo == null || CommonConstant.DEL_FLAG_1.equals(testDemo.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(testDemo, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            TestDemo testDemo = this.getById(id);
            if (testDemo == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<TestDemo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TestDemo::getId, idList);
        TestDemo testDemo = new TestDemo();
        testDemo.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(testDemo, queryWrapper);
    }
    
    
}