package com.wocai.platform.modules.business.display.entity;

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
 * @描述: 服务展示
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@TableName("mmcc_service_display")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_service_display对象", description="服务展示")
public class MmccServiceDisplay extends BaseEntity {

	/**房间名称*/
	@Excel(name = "封面图url", width = 15)
	@ApiModelProperty(value = "封面图url")
	private String coverImageUrl;

	/**房间名称*/
	@Excel(name = "详情图url", width = 15)
	@ApiModelProperty(value = "详情图url")
	private String detailsUrl;

	/**房间名称*/
	@Excel(name = "房间名称", width = 15)
    @ApiModelProperty(value = "房间名称")
	private String name;
	/**房型介绍*/
	@Excel(name = "房型介绍", width = 15)
    @ApiModelProperty(value = "房型介绍")
	private String roomModel;
	/**服务介绍*/
	@Excel(name = "服务介绍", width = 15)
    @ApiModelProperty(value = "服务介绍")
	private String serviceIntroduce;

	@Excel(name = "服务标签（，分割开）", width = 15)
	@ApiModelProperty(value = "服务标签（,分割开）")
	private String roomLabel;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;

	/**营业时间*/
	@Excel(name = "营业时间", width = 15)
	@ApiModelProperty(value = "营业时间")
	private String businessHours;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
	@ApiModelProperty(value = "电话号码")
	private String phoneCode;

	/**门店地址*/
	@Excel(name = "门店地址", width = 15)
	@ApiModelProperty(value = "门店地址")
	private String storeAddress;

	/**经度*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String longitude;

	/**纬度*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
	private String latitude;

}