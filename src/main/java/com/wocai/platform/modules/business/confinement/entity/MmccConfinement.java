package com.wocai.platform.modules.business.confinement.entity;

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
 * @描述: mmcc-产期圈
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_confinement")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_confinement对象", description="mmcc-产期圈")
public class MmccConfinement extends BaseEntity {

	/**活动类型*/
	@Excel(name = "活动类型", width = 15)
    @ApiModelProperty(value = "活动类型")
	private String type;
	/**活动标题*/
	@Excel(name = "活动标题", width = 15)
    @ApiModelProperty(value = "活动标题")
	private String title;

	@ApiModelProperty(value = "故事开头")
	private String startContent;
	/**内容*/
	@Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
	private String content;
	/**查看次数*/
	@Excel(name = "查看次数", width = 15)
    @ApiModelProperty(value = "查看次数")
	private Long number;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;

	@ApiModelProperty(value = "排序")
	private String sort;
}