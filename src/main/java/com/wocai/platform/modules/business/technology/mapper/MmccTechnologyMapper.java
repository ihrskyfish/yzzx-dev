package com.wocai.platform.modules.business.technology.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.technology.entity.MmccTechnology;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.technology.vo.MmccTechnologyRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: mmcc-工艺栏
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface MmccTechnologyMapper extends BaseMapper<MmccTechnology> {

    IPage<MmccTechnologyRes> queryPage(IPage<MmccTechnologyRes> page, @Param("m") Map<String, Object> reqParam);

}