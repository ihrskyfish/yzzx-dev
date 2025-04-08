package com.wocai.platform.modules.business.publicize.entity;

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
 * @描述: 宣传预定
 * @Author: wocai
 * @Date: 2023-06-14
 * @Version: V1.0
 */
@Data
@TableName("yz_publicize_room")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yz_publicize_room对象", description="宣传预定")
public class YzPublicizeRoom extends BaseEntity {

	/**头像*/
	@Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
	private String imgSmall;
	/**相册*/
	@Excel(name = "相册", width = 15)
    @ApiModelProperty(value = "相册")
	private String imgs;
	/**房间名称*/
	@Excel(name = "房间名称", width = 15)
    @ApiModelProperty(value = "房间名称")
	private String name;
	/**房间介绍*/
	@Excel(name = "房间介绍", width = 15)
    @ApiModelProperty(value = "房间介绍")
	private String roomIntr;
	/**服务介绍*/
	@Excel(name = "服务介绍", width = 15)
    @ApiModelProperty(value = "服务介绍")
	private String serviceIntr;
	/**服务展示*/
	@Excel(name = "服务展示", width = 15)
    @ApiModelProperty(value = "服务展示")
	private String serviceShow;
}