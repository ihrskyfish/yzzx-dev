package com.wocai.platform.modules.business.health.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-26 14:20
 **/
@Data
public class MmccBaByUserVo implements Serializable {

    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
    private String phone;
}
