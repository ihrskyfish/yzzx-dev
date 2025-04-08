package com.wocai.platform.modules.app.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @描述: 测试DemoReq
 * @Author: wocai
 * @Date: 2022-07-07
 * @Version: V1.0
 */
@Data
public class TestDemoReq implements Serializable {

    @NotNull(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;


}