package com.wocai.platform.modules.app.test.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.app.test.entity.TestPwc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.app.test.vo.TestPwcRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 公益栏信息列表
 * @Author: wocai
 * @Date: 2023-06-02
 * @Version: V1.0
 */
public interface TestPwcMapper extends BaseMapper<TestPwc> {

    IPage<TestPwcRes> queryPage(IPage<TestPwcRes> page, @Param("m") Map<String, Object> reqParam);

}