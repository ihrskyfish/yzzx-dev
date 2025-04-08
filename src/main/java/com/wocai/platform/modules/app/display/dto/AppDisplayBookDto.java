package com.wocai.platform.modules.app.display.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 13:43
 **/
@Data
public class AppDisplayBookDto implements Serializable {

    @Excel(name = "服务展示id", width = 15)
    @ApiModelProperty(value = "服务展示id")
    private String displayId;

    /**
     * 用户姓名
     */
    @Excel(name = "用户姓名", width = 15)
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    /**
     * 用户手机号
     */
    @Excel(name = "用户手机号", width = 15)
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;
    /**
     * 参观时间
     */
    @Excel(name = "参观时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "参观时间")
    private Date visitingTime;

    /**
     * 孕周
     */
    @Excel(name = "孕周", width = 15)
    @ApiModelProperty(value = "孕周")
    private String gestationalWeek;
    /**
     * 住宅
     */
    @Excel(name = "住宅", width = 15)
    @ApiModelProperty(value = "住宅")
    private String residence;
    /**
     * 产检医院
     */
    @Excel(name = "产检医院", width = 15)
    @ApiModelProperty(value = "产检医院")
    private String inspectHospital;

    /**
     * 参观人数
     */
    @Excel(name = "参观人数", width = 15)
    @ApiModelProperty(value = "参观人数")
    private String visitorsNum;
    /**
     * 陪同人员关系
     */
    @Excel(name = "陪同人员关系", width = 15)
    @ApiModelProperty(value = "陪同人员关系")
    private String accompanyingRelationship;

    @Excel(name = "是否带孩子（0：是 1：否）", width = 15)
    @ApiModelProperty(value = "是否带孩子（0：是 1：否）")
    private String withChildren;
    /**
     * 年龄段(0:0-5岁 1:5-10岁 2:10-15岁 3 15-20岁 4:20-25岁 5:25-30岁 6:30-35岁 7:35-40岁 8:40-45岁 9:45岁以上)
     */
    @Excel(name = "年龄段(0:0-5岁 1:5-10岁 2:10-15岁 3 15-20岁 4:20-25岁 5:25-30岁 6:30-35岁 7:35-40岁 8:40-45岁 9:45岁以上)", width = 15)
    @ApiModelProperty(value = "年龄段(0:0-5岁 1:5-10岁 2:10-15岁 3 15-20岁 4:20-25岁 5:25-30岁 6:30-35岁 7:35-40岁 8:40-45岁 9:45岁以上)")
    private String ageGroup;

    /**
     * 胎次
     */
    @Excel(name = "胎次", width = 15)
    @ApiModelProperty(value = "胎次")
    private Long parity;

    /**
     * 是否需要婴儿床（0：是 1：否）
     */
    @Excel(name = "是否需要婴儿床（0：是 1：否）", width = 15)
    @ApiModelProperty(value = "是否需要婴儿床（0：是 1：否）")
    private String crib;
    /**
     * 点心饮品
     */
    @Excel(name = "点心饮品", width = 15)
    @ApiModelProperty(value = "点心饮品")
    private String snack;
    /**
     * 月子餐品鉴
     */
    @Excel(name = "月子餐品鉴", width = 15)
    @ApiModelProperty(value = "月子餐品鉴")
    private String foodTasting;
    /**
     * 月子餐菜单
     */
    @Excel(name = "月子餐菜单", width = 15)
    @ApiModelProperty(value = "月子餐菜单")
    private String mealMenu;
    /**
     * 陪护餐份数
     */
    @Excel(name = "陪护餐份数", width = 15)
    @ApiModelProperty(value = "陪护餐份数")
    private String servingsNum;
    /**
     * 是否需要带雨伞（0：是1：否）
     */
    @Excel(name = "是否需要带雨伞（0：是1：否）", width = 15)
    @ApiModelProperty(value = "是否需要带雨伞（0：是1：否）")
    private String umbrella;
    /**
     * 保安泊车（0：是 1：否）
     */
    @Excel(name = "保安泊车（0：是 1：否）", width = 15)
    @ApiModelProperty(value = "保安泊车（0：是 1：否）")
    private String parking;
    /**
     * 是否需要接送（0：是 1：否）
     */
    @Excel(name = "是否需要接送（0：是 1：否）", width = 15)
    @ApiModelProperty(value = "是否需要接送（0：是 1：否）")
    private String pickup;
    /**
     * 儿童安全椅（0：是 1否）
     */
    @Excel(name = "儿童安全椅（0：是 1否）", width = 15)
    @ApiModelProperty(value = "儿童安全椅（0：是 1否）")
    private String safetySeat;
    /**
     * 其他要求
     */
    @Excel(name = "其他要求", width = 15)
    @ApiModelProperty(value = "其他要求")
    private String otherRequirements;

    @Excel(name = "饮品类型（0 柠檬水(夏) 1 咖啡(四季) 2 姜茶(秋冬) 3雪梨水(四季)）", width = 15)
    @ApiModelProperty(value = "饮品类型（0 柠檬水(夏) 1 咖啡(四季) 2 姜茶(秋冬) 3雪梨水(四季)）")
    private String beverageType;

    /**接待人员id*/
    @Excel(name = "接待人员id", width = 15)
    @ApiModelProperty(value = "接待人员id")
    private String servicePersonId;



}
