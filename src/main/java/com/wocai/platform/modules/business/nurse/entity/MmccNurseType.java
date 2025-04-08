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
 * @描述: 类型
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("mmcc_nurse_type")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_nurse_type对象", description="类型")
public class MmccNurseType extends BaseEntity {

	/**类别名称*/
	@Excel(name = "类别名称", width = 15)
    @ApiModelProperty(value = "类别名称")
	private String name;

	@Excel(name = "排序", width = 15)
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**状态*/
	@Excel(name = "状态(1启用 -1停用)", width = 15)
    @ApiModelProperty(value = "状态(1启用 -1停用)")
	private String status;
}