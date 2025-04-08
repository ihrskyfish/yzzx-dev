package com.wocai.platform.modules.system.entity;

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
 * @描述: 数据库备份表
 * @Author: wocai
 * @Date: 2022-07-04
 * @Version: V1.0
 */
@Data
@TableName("sys_database_back")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_database_back对象", description="数据库备份表")
public class SysDatabaseBack extends BaseEntity {

	/**备份名称*/
	@Excel(name = "备份名称", width = 15)
    @ApiModelProperty(value = "备份名称")
	private String backName;
	/**备份位置*/
	@Excel(name = "备份位置", width = 15)
    @ApiModelProperty(value = "备份位置")
	private String backPath;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String description;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}