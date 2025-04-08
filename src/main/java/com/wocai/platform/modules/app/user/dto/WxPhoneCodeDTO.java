package com.wocai.platform.modules.app.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 11:31
 **/
@Data
public class WxPhoneCodeDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "授权code")
    @NotBlank(message = "授权code不可为空")
    @NotNull(message = "授权code不可为null")
    private String code;
}
