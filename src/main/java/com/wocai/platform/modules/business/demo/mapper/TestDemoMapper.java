package com.wocai.platform.modules.business.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.demo.entity.TestDemo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.demo.vo.TestDemoRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 测试Demo
 * @Author: wocai
 * @Date: 2022-07-07
 * @Version: V1.0
 */
public interface TestDemoMapper extends BaseMapper<TestDemo> {

    IPage<TestDemoRes> queryPage(IPage<TestDemoRes> page, @Param("m") Map<String, Object> reqParam);

}