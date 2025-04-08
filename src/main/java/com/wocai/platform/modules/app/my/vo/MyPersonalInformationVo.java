package com.wocai.platform.modules.app.my.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-06-30 15:47
 **/
@Data
public class MyPersonalInformationVo implements Serializable {


    @ApiModelProperty(value = "id")
    private String id;

    /**用户名*/
    @Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private String username;
    /**昵称*/
    @Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**头像*/
    @Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
    private String avatarKey;

    /**性别(1:男 2:女)*/
    @Excel(name = "性别(1:男 2:女)", width = 15)
    @ApiModelProperty(value = "性别(1:男 2:女)")
    private String gender;
    /**手机号*/
    @Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**出生日期*/
    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
    /**国家*/
    @Excel(name = "国家", width = 15)
    @ApiModelProperty(value = "国家")
    private String country;
    /**省份*/
    @Excel(name = "省份", width = 15)
    @ApiModelProperty(value = "省份")
    private String province;
    /**城市*/
    @Excel(name = "城市", width = 15)
    @ApiModelProperty(value = "城市")
    private String city;
    /**用户openid*/
    @Excel(name = "用户openid", width = 15)
    @ApiModelProperty(value = "用户openid")
    private String openid;
    /**用户unionid*/
    @Excel(name = "用户unionid", width = 15)
    @ApiModelProperty(value = "用户unionid")
    private String unionid;
    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String status;

    @Excel(name = "当前孕周", width = 15)
    @ApiModelProperty(value = "当前孕周")
    private Integer gestationalWeek;

    @Excel(name = "预约记录数量", width = 15)
    @ApiModelProperty(value = "预约记录数量")
    private Integer recordsNum;

    @Excel(name = "填写时间", width = 15)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "填写时间")
    private Date gestationalTime;

    @Excel(name = "妈妈生日", width = 15)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "妈妈生日")
    private Date momBirthday;

    @ApiModelProperty(value = "是否生日")
    private boolean birthdaysToday;

    @ApiModelProperty(value = "是否送过祝福")
    private boolean birthdaystatus;


    @Excel(name = "宝宝生日", width = 15)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "宝宝生日")
    private Date babyBirthday;



}
