package com.wocai.platform.modules.app.communication.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.modules.app.communication.dto.AppCommunicationUserDto;
import com.wocai.platform.modules.app.communication.vo.AppCommunicationPageVo;
import com.wocai.platform.modules.app.communication.vo.AppCommunicationQueryByIdVo;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 15:51
 **/
public interface AppCommunicationService {
    IPage<AppCommunicationPageVo> queryPageList(Page<MmccCommunicationCircle> page);

    AppCommunicationQueryByIdVo getMainById(String id);

    void evaluate(AppCommunicationUserDto appCommunicationUserDto, ReqUser reqUser);
}
