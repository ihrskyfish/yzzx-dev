package com.wocai.platform.modules.business.activity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-26 09:18
 **/
@Data
public class MmccActivityUserListVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    @ApiModelProperty(value = "结束状态（0：未结束 1已结束）")
    private String endState;

    @ApiModelProperty(value = "报名时间")
    private Date createTime;


}
