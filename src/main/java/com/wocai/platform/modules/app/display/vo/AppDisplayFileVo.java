package com.wocai.platform.modules.app.display.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-06-29 18:02
 **/
@Data
public class AppDisplayFileVo implements Serializable {

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "介绍")
    private String introduce;

}
