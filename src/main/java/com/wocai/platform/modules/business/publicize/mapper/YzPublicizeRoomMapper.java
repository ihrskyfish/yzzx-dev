package com.wocai.platform.modules.business.publicize.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.publicize.entity.YzPublicizeRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.publicize.vo.YzPublicizeRoomRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 宣传预定
 * @Author: wocai
 * @Date: 2023-06-14
 * @Version: V1.0
 */
public interface YzPublicizeRoomMapper extends BaseMapper<YzPublicizeRoom> {

    IPage<YzPublicizeRoomRes> queryPage(IPage<YzPublicizeRoomRes> page, @Param("m") Map<String, Object> reqParam);

}