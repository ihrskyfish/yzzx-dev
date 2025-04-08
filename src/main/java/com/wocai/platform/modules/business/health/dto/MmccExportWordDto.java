package com.wocai.platform.modules.business.health.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-07-20 10:58
 **/
@Data
public class MmccExportWordDto implements Serializable {

    private String id;

    private List<String> urls;
}
