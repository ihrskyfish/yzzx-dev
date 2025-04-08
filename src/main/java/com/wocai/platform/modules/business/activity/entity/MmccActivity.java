package com.wocai.platform.modules.business.activity.entity;

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
 * @描述: 孕期圈活动
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_activity")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_activity对象", description="孕期圈活动")
public class MmccActivity extends BaseEntity {

	/**活动名称*/
	@Excel(name = "活动名称", width = 15)
    @ApiModelProperty(value = "活动名称")
	private String name;
	/**活动联系人姓名*/
	@Excel(name = "活动联系人姓名", width = 15)
    @ApiModelProperty(value = "活动联系人姓名")
	private String activityUserName;
	/**联系人手机号*/
	@Excel(name = "联系人手机号", width = 15)
    @ApiModelProperty(value = "联系人手机号")
	private String activityUserPhone;
	/**报名开始时间*/
	@Excel(name = "报名开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报名开始时间")
	private Date startTime;
	/**报名截止时间*/
	@Excel(name = "报名截止时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报名截止时间")
	private Date endTime;
	/**活动介绍*/
	@Excel(name = "活动介绍", width = 15)
    @ApiModelProperty(value = "活动介绍")
	private String introduce;
	/**结束状态（0：未结束 1已结束）*/
	@Excel(name = "结束状态（0：未结束 1已结束）", width = 15)
    @ApiModelProperty(value = "结束状态（0：未结束 1已结束）")
	private String endState;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
	private String fileUrl;
	/**活动成就*/
	@Excel(name = "活动成就", width = 15)
    @ApiModelProperty(value = "活动成就")
	private String achieve;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
	private Long sort;
	/**状态*/
	@Excel(name = "状态(0 :草稿  10 发布  20 截止)", width = 15)
    @ApiModelProperty(value = "状态(0 :草稿  10 发布  20 截止)")
	private String status;
}