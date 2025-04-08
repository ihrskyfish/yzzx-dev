package com.wocai.platform.modules.app.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 16:44
 **/
@Data
public class VxUserGestationalWeekDto implements Serializable {

    @ApiModelProperty(value = "当前孕周")
    private Integer gestationalWeek;
}
