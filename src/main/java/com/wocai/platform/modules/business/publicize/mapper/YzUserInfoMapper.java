package com.wocai.platform.modules.business.publicize.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.publicize.entity.YzUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.publicize.vo.YzUserInfoRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 房间预定用户信息
 * @Author: wocai
 * @Date: 2023-06-25
 * @Version: V1.0
 */
public interface YzUserInfoMapper extends BaseMapper<YzUserInfo> {

    IPage<YzUserInfoRes> queryPage(IPage<YzUserInfoRes> page, @Param("m") Map<String, Object> reqParam);

}