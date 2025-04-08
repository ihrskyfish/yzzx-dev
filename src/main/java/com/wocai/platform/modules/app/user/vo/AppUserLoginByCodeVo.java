package com.wocai.platform.modules.app.user.vo;

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
 * @create: 2023-05-23 11:16
 **/
@Data
public class AppUserLoginByCodeVo implements Serializable {

    private String token;


    @ApiModelProperty(value = "昵称")
    private String nickName;


    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @ApiModelProperty(value = "当前孕周")
    private Integer gestationalWeek;


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

    @ApiModelProperty(value = "手机号")
    private String phone;
}
