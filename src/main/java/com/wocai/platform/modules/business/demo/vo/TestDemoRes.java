package com.wocai.platform.modules.business.demo.vo;

import com.wocai.platform.modules.business.demo.entity.TestDemo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @描述: 测试DemoRes
 * @Author: wocai
 * @Date: 2022-07-07
 * @Version: V1.0
 */
@Data
@ApiModel(value="测试Demo", description="测试Demo")
public class TestDemoRes extends TestDemo {


}