package com.wocai.platform.modules.app.my.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 10:00
 **/
@Data
public class MyUpdateUserDto implements Serializable {
    @Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Excel(name = "妈妈生日", width = 15)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "妈妈生日")
    private Date momBirthday;

    @Excel(name = "宝宝生日", width = 15)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "宝宝生日")
    private Date babyBirthday;

    @Excel(name = "当前孕周", width = 15)
    @ApiModelProperty(value = "当前孕周")
    private Integer gestationalWeek;
}
