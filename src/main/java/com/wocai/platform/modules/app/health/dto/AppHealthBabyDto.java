package com.wocai.platform.modules.app.health.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 09:54
 **/
@Data
public class AppHealthBabyDto implements Serializable {

    @ApiModelProperty(value = "图片url")
    private List<String> urls;
}
