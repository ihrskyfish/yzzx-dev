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
 * @描述: 预定房间信息
 * @Author: wocai
 * @Date: 2023-06-14
 * @Version: V1.0
 */
@Data
@TableName("yz_pre_room")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yz_pre_room对象", description="预定房间信息")
public class YzPreRoom extends BaseEntity {

	/**预定时间*/
	@Excel(name = "预定时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "预定时间")
	private Date time;
	/**房间图片*/
	@Excel(name = "房间图片", width = 15)
    @ApiModelProperty(value = "房间图片")
	private String img;
	/**评价内容*/
	@Excel(name = "评价内容", width = 15)
    @ApiModelProperty(value = "评价内容")
	private String evaluate;
	/**房间id*/
	@Excel(name = "房间id", width = 15)
    @ApiModelProperty(value = "房间id")
	private Long roomId;
}