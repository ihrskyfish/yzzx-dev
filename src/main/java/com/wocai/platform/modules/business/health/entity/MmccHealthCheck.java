package com.wocai.platform.modules.business.health.entity;

import java.math.BigDecimal;
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
 * @描述: 每日打卡
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_health_check")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_health_check对象", description="每日打卡")
public class MmccHealthCheck extends BaseEntity {

	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
	private String userId;
	/**每天心情（0：不开心 1：普通 2：开心）*/
	@Excel(name = "每天心情（0：不开心 1：普通 2：开心）", width = 15)
    @ApiModelProperty(value = "每天心情（0：不开心 1：普通 2：开心）")
	private String mood;
	/**体重kg*/
	@Excel(name = "体重kg", width = 15)
    @ApiModelProperty(value = "体重kg")
	private BigDecimal weight;
	/**胸围cm*/
	@Excel(name = "胸围cm", width = 15)
    @ApiModelProperty(value = "胸围cm")
	private BigDecimal bust;
	/**腰围cm*/
	@Excel(name = "腰围cm", width = 15)
    @ApiModelProperty(value = "腰围cm")
	private BigDecimal waist;
	/**臀围cm*/
	@Excel(name = "臀围cm", width = 15)
    @ApiModelProperty(value = "臀围cm")
	private BigDecimal hip;
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
	private Integer moodContinuousDays;

	@Excel(name = "连续打卡天数", width = 15)
	@ApiModelProperty(value = "连续打卡天数")
	private Integer continuousDays;

}