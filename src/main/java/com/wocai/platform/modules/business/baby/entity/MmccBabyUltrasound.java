package com.wocai.platform.modules.business.baby.entity;

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
 * @描述: 宝宝B超
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_baby_ultrasound")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_baby_ultrasound对象", description="宝宝B超")
public class MmccBabyUltrasound extends BaseEntity {

	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
	private String userId;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
	private String fileUrl;
	/**年*/
	@Excel(name = "年", width = 15)
    @ApiModelProperty(value = "年")
	private Integer year;
	/**月*/
	@Excel(name = "月", width = 15)
    @ApiModelProperty(value = "月")
	private Integer month;
	/**日*/
	@Excel(name = "日", width = 15)
    @ApiModelProperty(value = "日")
	private Integer day;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;

	@Excel(name = "连续打卡天数", width = 15)
	@ApiModelProperty(value = "连续打卡天数")
	private Integer continuousDays;
}