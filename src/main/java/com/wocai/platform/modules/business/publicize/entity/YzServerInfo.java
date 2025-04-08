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
 * @描述: 预定房间服务信息
 * @Author: wocai
 * @Date: 2023-06-25
 * @Version: V1.0
 */
@Data
@TableName("yz_server_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yz_server_info对象", description="预定房间服务信息")
public class YzServerInfo extends BaseEntity {

	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
	private Integer userId;
	/**用户信息id*/
	@Excel(name = "用户信息id", width = 15)
    @ApiModelProperty(value = "用户信息id")
	private Long userInfoId;
	/**婴儿床*/
	@Excel(name = "婴儿床", width = 15)
    @ApiModelProperty(value = "婴儿床")
	private String bed;
	/**点心、饮品*/
	@Excel(name = "点心、饮品", width = 15)
    @ApiModelProperty(value = "点心、饮品")
	private String food;
	/**月子餐品鉴*/
	@Excel(name = "月子餐品鉴", width = 15)
    @ApiModelProperty(value = "月子餐品鉴")
	private String yzMealAppraise;
	/**月子餐菜单*/
	@Excel(name = "月子餐菜单", width = 15)
    @ApiModelProperty(value = "月子餐菜单")
	private String yzMealMenu;
	/**陪护餐份数*/
	@Excel(name = "陪护餐份数", width = 15)
    @ApiModelProperty(value = "陪护餐份数")
	private Integer mealNumber;
	/**雨伞*/
	@Excel(name = "雨伞", width = 15)
    @ApiModelProperty(value = "雨伞")
	private String umbrella;
	/**保安泊车*/
	@Excel(name = "保安泊车", width = 15)
    @ApiModelProperty(value = "保安泊车")
	private String park;
	/**接送*/
	@Excel(name = "接送", width = 15)
    @ApiModelProperty(value = "接送")
	private String pado;
	/**儿童安全座椅*/
	@Excel(name = "儿童安全座椅", width = 15)
    @ApiModelProperty(value = "儿童安全座椅")
	private String safeSeat;
	/**其他需求*/
	@Excel(name = "其他需求", width = 15)
    @ApiModelProperty(value = "其他需求")
	private String other;
}