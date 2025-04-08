package com.wocai.platform.modules.business.communication.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.communication.vo.MmccCommunicationUserRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 话题留言
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccCommunicationUserMapper extends BaseMapper<MmccCommunicationUser> {

    IPage<MmccCommunicationUserRes> queryPage(IPage<MmccCommunicationUserRes> page, @Param("m") Map<String, Object> reqParam);

}