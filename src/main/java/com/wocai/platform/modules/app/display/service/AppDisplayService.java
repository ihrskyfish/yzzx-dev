package com.wocai.platform.modules.app.display.service;

import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.modules.app.display.dto.AppCancellationDto;
import com.wocai.platform.modules.app.display.dto.AppDisplayBookDto;
import com.wocai.platform.modules.app.display.dto.AppDisplayEvaluateVo;
import com.wocai.platform.modules.app.display.vo.AppDisplayBookQueryByIdVo;
import com.wocai.platform.modules.app.display.vo.AppDisplayListVo;
import com.wocai.platform.modules.app.display.vo.AppDisplayMyBookListVo;
import com.wocai.platform.modules.app.display.vo.AppDisplayQueryByIdVo;
import com.wocai.platform.modules.business.display.vo.MmccServocePersonVO;

import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 16:54
 **/
public interface AppDisplayService {
    List<AppDisplayListVo> queryPageList(ReqUser reqUser);
//    List<AppDisplayListVo> queryPageList();

    AppDisplayQueryByIdVo queyById(String id,ReqUser reqUser);

    void book(AppDisplayBookDto appDisplayBookDto, ReqUser reqUser);

    List<AppDisplayMyBookListVo> mybookList(ReqUser reqUser);

    void evaluate(AppDisplayEvaluateVo appDisplayEvaluateVo);

    AppDisplayBookQueryByIdVo queyByServiceUserId(String serviceUserId);

    String queryDistanceByDisplayId(String longitude, String latitude, String displayId);


    void cancellation(AppCancellationDto appCancellationDto, ReqUser reqUser);

    List<MmccServocePersonVO> getServicePersonList();


}
