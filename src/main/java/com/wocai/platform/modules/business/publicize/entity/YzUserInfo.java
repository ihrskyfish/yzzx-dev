package com.wocai.platform.modules.business.publicize.entity;

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
 * @描述: 房间预定用户信息
 * @Author: wocai
 * @Date: 2023-06-25
 * @Version: V1.0
 */
@Data
@TableName("yz_user_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yz_user_info对象", description="房间预定用户信息")
public class YzUserInfo extends BaseEntity {

	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
	private Integer userId;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String name;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String phoneNumber;
	/**参观时间*/
	@Excel(name = "参观时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "参观时间")
	private Date visitTime;
	/**孕周*/
	@Excel(name = "孕周", width = 15)
    @ApiModelProperty(value = "孕周")
	private Integer gestWeek;
	/**住宅*/
	@Excel(name = "住宅", width = 15)
    @ApiModelProperty(value = "住宅")
	private String residence;
	/**参观人数*/
	@Excel(name = "参观人数", width = 15)
    @ApiModelProperty(value = "参观人数")
	private Integer number;
	/**产检医院*/
	@Excel(name = "产检医院", width = 15)
    @ApiModelProperty(value = "产检医院")
	private String hospital;
	/**陪同人员关系*/
	@Excel(name = "陪同人员关系", width = 15)
    @ApiModelProperty(value = "陪同人员关系")
	private String relation;
	/**是否带孩子*/
	@Excel(name = "是否带孩子", width = 15)
    @ApiModelProperty(value = "是否带孩子")
	private String child;
	/**年龄段*/
	@Excel(name = "年龄段", width = 15)
    @ApiModelProperty(value = "年龄段")
	private String age;
}