package com.wocai.platform.modules.business.technology.entity;

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
 * @描述: mmcc-工艺栏
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@TableName("mmcc_technology")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_technology对象", description="mmcc-工艺栏")
public class MmccTechnology extends BaseEntity {

	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
	private String name;

	@Excel(name = "副标题", width = 15)
	@ApiModelProperty(value = "副标题")
	private String subtitle;

	/**内容*/
	@Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
	private String content;

	/**内容*/
	@Excel(name = "列表展示图片url", width = 15)
	@ApiModelProperty(value = "列表展示图片url")
	private String url;

	@ApiModelProperty(value = "排序")
	private String sort;

	@ApiModelProperty(value = "发布时间")
	@Excel(name = "发布时间", width = 20, format = "yyyy-MM-dd HH:mm:ss",orderNum = "510")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date releaseTime;

	/**状态*/
	@Excel(name = "状态（1启用   -1停用）", width = 15)
    @ApiModelProperty(value = "状态（1启用   -1停用）")
	private String status;
}