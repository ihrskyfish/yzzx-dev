package com.wocai.platform.modules.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-07-21 14:50
 **/
@Data
public class CommonBase64Dto implements Serializable {

    private String base64;

    private String name;
}
