package com.wocai.platform.modules.app.nurse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 16:30
 **/
@Data
public class AppNurseCollectDto implements Serializable {

    @ApiModelProperty(value = "类型id")
    private String id;

    @ApiModelProperty(value = "是否收藏（0：收藏 1：取消收藏）")
    private String collectType;
}
