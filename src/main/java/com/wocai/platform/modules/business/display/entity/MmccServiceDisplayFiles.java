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
 * @描述: 服务展示图片表
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@TableName("mmcc_service_display_files")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_service_display_files对象", description="服务展示图片表")
public class MmccServiceDisplayFiles extends BaseEntity {

	/**服务展示id*/
	@Excel(name = "服务展示id", width = 15)
    @ApiModelProperty(value = "服务展示id")
	private String displayId;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
	private String fileUrl;

	@ApiModelProperty(value = "介绍")
	private String introduce;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}