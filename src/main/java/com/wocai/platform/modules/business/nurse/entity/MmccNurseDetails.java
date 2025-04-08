package com.wocai.platform.modules.business.nurse.entity;

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
 * @描述: 护理内容
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("mmcc_nurse_details")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_nurse_details对象", description="护理内容")
public class MmccNurseDetails extends BaseEntity {

	/**类型id*/
	@Excel(name = "类型id", width = 15)
    @ApiModelProperty(value = "类型id")
	private String typeId;
	/**知识标题*/
	@Excel(name = "知识标题", width = 15)
    @ApiModelProperty(value = "知识标题")
	private String title;

	@Excel(name = "孕期开始周数", width = 15)
	@ApiModelProperty(value = "孕期开始周数")
	private String stateWeek;

	@Excel(name = "孕期结束周数", width = 15)
	@ApiModelProperty(value = "孕期结束周数")
	private String endWeek;

	@Excel(name = "知识介绍", width = 15)
	@ApiModelProperty(value = "知识介绍")
	private String exhibit;
	/**内容*/
	@Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
	private String content;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
	private String sort;
	/**是否推荐(0:否 1:0)*/
	@Excel(name = "是否推荐(0:否 1:0)", width = 15)
    @ApiModelProperty(value = "是否推荐(0:否 1:0)")
	private String recommend;

	@Excel(name = "知识图片", width = 15)
	@ApiModelProperty(value = "知识图片")
	private String url;

	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}