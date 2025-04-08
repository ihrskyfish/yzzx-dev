package com.wocai.platform.modules.business.publicize.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.publicize.entity.YzServerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.publicize.vo.YzServerInfoRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 预定房间服务信息
 * @Author: wocai
 * @Date: 2023-06-25
 * @Version: V1.0
 */
public interface YzServerInfoMapper extends BaseMapper<YzServerInfo> {

    IPage<YzServerInfoRes> queryPage(IPage<YzServerInfoRes> page, @Param("m") Map<String, Object> reqParam);

}