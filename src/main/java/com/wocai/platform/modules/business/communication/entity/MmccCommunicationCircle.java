package com.wocai.platform.modules.business.communication.entity;

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
 * @描述: mmcc-交流圈
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_communication_circle")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_communication_circle对象", description="mmcc-交流圈")
public class MmccCommunicationCircle extends BaseEntity {

	/**活动标题*/
	@Excel(name = "活动标题", width = 15)
    @ApiModelProperty(value = "活动标题")
	private String title;
	/**内容*/
	@Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
	private String content;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
	private String sort;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}