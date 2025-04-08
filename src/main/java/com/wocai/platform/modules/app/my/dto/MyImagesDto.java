package com.wocai.platform.modules.app.my.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-06-27 15:15
 **/
@Data
public class MyImagesDto implements Serializable {

    private List<String> images;
}
