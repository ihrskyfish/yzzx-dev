package com.wocai.platform.modules.business.display.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @描述: 展示用户预定表Res
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@ApiModel(value="展示用户预定表", description="展示用户预定表")
public class MmccServiceUserRes extends MmccServiceUser {

    @ApiModelProperty(value = "房间名称")
    private String name;

    @ApiModelProperty(value = "服务介绍")
    private String serviceIntroduce;

    @Excel(name = "服务标签（，分割开）", width = 15)
    @ApiModelProperty(value = "服务标签（,分割开）")
    private String roomLabel;

    @ApiModelProperty(value = "接待人员姓名")
    private String servicePersonName;







}