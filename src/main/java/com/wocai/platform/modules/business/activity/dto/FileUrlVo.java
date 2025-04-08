package com.wocai.platform.modules.business.activity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-07-11 11:03
 **/
@Data
public class FileUrlVo implements Serializable {

    private String key;

    private String url;
}
