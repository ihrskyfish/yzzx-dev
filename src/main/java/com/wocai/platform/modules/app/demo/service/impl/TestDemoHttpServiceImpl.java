package com.wocai.platform.modules.app.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wocai.platform.common.api.dto.IdDTO;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.modules.app.demo.dto.TestDemoReq;
import com.wocai.platform.modules.app.demo.service.ITestDemoHttpService;
import com.wocai.platform.modules.app.demo.vo.TestDemoRes;
import com.wocai.platform.modules.business.demo.entity.TestDemo;
import com.wocai.platform.modules.business.demo.mapper.TestDemoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 测试DEMO请求
 * @Author: linwenqiang
 * @Date: 7/7/22 2:21 PM
 * @Version: V1.0
 */
@Service
public class TestDemoHttpServiceImpl implements ITestDemoHttpService {

    @Resource
    private TestDemoMapper testDemoMapper;

    @Override
    public TestDemoRes selectById(IdDTO idDTO) {
        return null;
    }

    @Override
    public TestDemoRes selectByName(TestDemoReq testDemoReq) {
        TestDemoRes result = null;
        LambdaQueryWrapper<TestDemo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TestDemo::getNickname, testDemoReq.getNickname());
        queryWrapper.eq(TestDemo::getDelFlag, CommonConstant.DEL_FLAG_0);
        List<TestDemo> dataList = testDemoMapper.selectList(queryWrapper);
        if (dataList != null && dataList.size() > 0) {
            result = new TestDemoRes();
            BeanUtils.copyProperties(dataList.get(0), result);
        }
        return result;
    }
}
