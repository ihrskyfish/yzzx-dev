package com.wocai.platform.modules.business.communication.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.communication.vo.MmccCommunicationCircleRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: mmcc-交流圈
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccCommunicationCircleMapper extends BaseMapper<MmccCommunicationCircle> {

    IPage<MmccCommunicationCircleRes> queryPage(IPage<MmccCommunicationCircleRes> page, @Param("m") Map<String, Object> reqParam);

}