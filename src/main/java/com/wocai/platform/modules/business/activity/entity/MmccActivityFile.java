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
 * @描述: 活动图片表
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_activity_file")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_activity_file对象", description="活动图片表")
public class MmccActivityFile extends BaseEntity {

	/**活动id*/
	@Excel(name = "活动id", width = 15)
    @ApiModelProperty(value = "活动id")
	private String activityId;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
	private String fileUrl;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}