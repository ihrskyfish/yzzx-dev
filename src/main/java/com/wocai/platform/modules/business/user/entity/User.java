package com.wocai.platform.modules.business.user.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wocai.platform.common.system.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @描述: User
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@TableName("app_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="app_user对象", description="User")
public class User extends BaseEntity {

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

	@Excel(name = "填写时间", width = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "填写时间")
	private Date gestationalTime;

	@Excel(name = "开始时间", width = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "开始时间")
	private Date startTime;

	@Excel(name = "离馆时间", width = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "离馆时间")
	private Date endTime;

	@Excel(name = "妈妈生日", width = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "妈妈生日")
	private Date momBirthday;
	
	@Excel(name = "宝宝生日", width = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "宝宝生日")
	private Date babyBirthday;

	/**家庭住址*/
	@Excel(name = "家庭住址", width = 15)
	@ApiModelProperty(value = "家庭住址")
	private String homeAddress;

	/**入住房型*/
	@Excel(name = "入住房型", width = 15)
	@ApiModelProperty(value = "入住房型")
	private String occupancyType;


	/**房号*/
	@Excel(name = "房号", width = 15)
	@ApiModelProperty(value = "房号")
	private String roomNum;


	@Excel(name = "结婚纪念日", width = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "结婚纪念日")
	private Date weddingAnniversary;

	/**胎次*/
	@Excel(name = "胎次", width = 15)
	@ApiModelProperty(value = "胎次")
	private Long parity;

	/**致敏源*/
	@Excel(name = "致敏源", width = 15)
	@ApiModelProperty(value = "致敏源")
	private String allergen;

	/**特殊病史*/
	@Excel(name = "特殊病史", width = 15)
	@ApiModelProperty(value = "特殊病史")
	private String specialMedicalHistory;
}