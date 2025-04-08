package com.wocai.platform.modules.business.publicize.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.publicize.entity.YzPreRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.publicize.vo.YzPreRoomRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 预定房间信息
 * @Author: wocai
 * @Date: 2023-06-14
 * @Version: V1.0
 */
public interface YzPreRoomMapper extends BaseMapper<YzPreRoom> {

    IPage<YzPreRoomRes> queryPage(IPage<YzPreRoomRes> page, @Param("m") Map<String, Object> reqParam);

}