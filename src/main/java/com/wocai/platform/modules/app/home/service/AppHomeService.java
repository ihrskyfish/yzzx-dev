package com.wocai.platform.modules.app.home.service;

import com.wocai.platform.modules.app.home.vo.AppProcessColumnVo;
import com.wocai.platform.modules.app.home.vo.AppRotationChartVo;

import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 16:53
 **/
public interface AppHomeService {
    List<AppProcessColumnVo> processColumnList();


    List<AppRotationChartVo> rotationChartList();


    AppProcessColumnVo processColumnQueryById(String id);
}
