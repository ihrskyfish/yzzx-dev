package com.wocai.platform.modules.business.health.entity;

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
 * @描述: 孕期健康
 * @Author: wocai
 * @Date: 2023-06-16
 * @Version: V1.0
 */
@Data
@TableName("yz_preg_health")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yz_preg_health对象", description="孕期健康")
public class YzPregHealth extends BaseEntity {

	/**1开心2普通3不开心*/
	@Excel(name = "1开心2普通3不开心", width = 15)
    @ApiModelProperty(value = "1开心2普通3不开心")
	private String mood;
	/**打卡天数*/
	@Excel(name = "打卡天数", width = 15)
    @ApiModelProperty(value = "打卡天数")
	private Integer days;
	/**体重*/
	@Excel(name = "体重", width = 15)
    @ApiModelProperty(value = "体重")
	private String weight;
	/**胸围*/
	@Excel(name = "胸围", width = 15)
    @ApiModelProperty(value = "胸围")
	private String bust;
	/**腰围*/
	@Excel(name = "腰围", width = 15)
    @ApiModelProperty(value = "腰围")
	private String waist;
	/**臂围*/
	@Excel(name = "臂围", width = 15)
    @ApiModelProperty(value = "臂围")
	private String arm;
	/**b超图*/
	@Excel(name = "b超图", width = 15)
    @ApiModelProperty(value = "b超图")
	private String bImgs;
}