package com.wocai.platform.modules.quartz.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * className DeviceInfo
 * description TODO
 *
 * @author DR XZT
 * @version 1.0
 * @date 2020/4/28 17:22
 */
@Data
public class DeviceInfo implements Serializable {
    //设备编号
    private String deviceNo;
    //名称
    private String name;
    //详细地址
    private String location;
    private double longitude;
    private double latitude;
    //设备类型1:智能厨余设备2:智能垃圾分类设备4:传统厨余设备
    private Integer type;
    //状态1:在线2:离线3:维修中4: 维修完成
    private Integer status;
    //代理商姓名
    private String agentName;
    //代理商电话
    private String agentPhone;
    //创建时间
    private String dateCreated;

}
