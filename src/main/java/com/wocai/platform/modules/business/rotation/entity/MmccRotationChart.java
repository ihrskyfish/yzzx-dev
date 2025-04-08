package com.wocai.platform.modules.business.rotation.entity;

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
 * @描述: 轮播图
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@TableName("mmcc_rotation_chart")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_rotation_chart对象", description="轮播图")
public class MmccRotationChart extends BaseEntity {

	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
	private String fileUrl;
	/**图片名称*/
	@Excel(name = "图片名称", width = 15)
    @ApiModelProperty(value = "图片名称")
	private String fileName;

	@ApiModelProperty(value = "图片内容")
	private String content;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}