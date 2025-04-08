package com.wocai.platform.modules.app.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.app.test.entity.TestPwc;
import com.wocai.platform.modules.app.test.dto.TestPwcReq;
import com.wocai.platform.modules.app.test.vo.TestPwcRes;
import com.wocai.platform.modules.app.test.mapper.TestPwcMapper;
import com.wocai.platform.modules.app.test.service.ITestPwcService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 公益栏增删改查
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Service
public class TestPwcServiceImpl extends BaseServiceImpl<TestPwcMapper, TestPwc> implements ITestPwcService {
    
    @Override
    public IPage<TestPwcRes> queryPageList(IPage<TestPwcRes> page, TestPwcReq testPwcReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotBlank(testPwcReq.getPwcTable())){
            reqParam.put("pwcTable",testPwcReq.getPwcTable());
        }
        if (StringUtils.isNotBlank(testPwcReq.getPwcIntr())){
            reqParam.put("pwcIntr",testPwcReq.getPwcIntr());
        }
        IPage<TestPwcRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(TestPwcReq testPwcReq) {
        return false;
    }

    @Override
    public void saveMain(TestPwcReq testPwcReq) {
        this.doDuplicateCheck(testPwcReq);
        TestPwc testPwc = new TestPwc();
        BeanUtils.copyProperties(testPwcReq, testPwc);
        this.save(testPwc);
    }

    @Override
    public void updateMain(TestPwcReq testPwcReq) {
        TestPwc testPwc = this.getById(testPwcReq.getId());
        if (testPwc == null || CommonConstant.DEL_FLAG_1.equals(testPwc.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(testPwcReq);
        BeanUtils.copyProperties(testPwcReq, testPwc);
        this.updateById(testPwc);
    }

    @Override
    public TestPwcRes getMainById(String id) {
        TestPwcRes result = new TestPwcRes();
        TestPwc testPwc = this.getById(id);
        if (testPwc == null || CommonConstant.DEL_FLAG_1.equals(testPwc.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(testPwc, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            TestPwc testPwc = this.getById(id);
            if (testPwc == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<TestPwc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TestPwc::getId, idList);
        TestPwc testPwc = new TestPwc();
        testPwc.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(testPwc, queryWrapper);
    }
    
    
}