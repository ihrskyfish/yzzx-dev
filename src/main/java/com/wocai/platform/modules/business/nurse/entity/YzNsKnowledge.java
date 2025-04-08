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
 * @描述: 护理知识
 * @Author: wocai
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Data
@TableName("yz_ns_knowledge")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yz_ns_knowledge对象", description="护理知识")
public class YzNsKnowledge extends BaseEntity {

	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
	private String knwTable;
	/**简介*/
	@Excel(name = "简介", width = 15)
    @ApiModelProperty(value = "简介")
	private String intr;
	/**正文*/
	@Excel(name = "正文", width = 15)
    @ApiModelProperty(value = "正文")
	private String text;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
	private String imgBig;
	/**分类id*/
	@Excel(name = "分类id", width = 15)
    @ApiModelProperty(value = "分类id")
	private Integer categoryId;
	/**0表示未收藏1表示收藏*/
	@Excel(name = "0表示未收藏1表示收藏", width = 15)
    @ApiModelProperty(value = "0表示未收藏1表示收藏")
	private String collectFlag;
}