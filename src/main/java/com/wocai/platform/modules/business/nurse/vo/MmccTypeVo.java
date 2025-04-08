package com.wocai.platform.modules.business.nurse.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-07-07 13:55
 **/
@Data
public class MmccTypeVo implements Serializable {
    private String id;

    @ApiModelProperty(value = "类别名称")
    private String name;

}
