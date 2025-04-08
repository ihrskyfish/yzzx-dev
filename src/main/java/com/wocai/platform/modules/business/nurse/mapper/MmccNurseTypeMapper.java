package com.wocai.platform.modules.business.nurse.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseTypeRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 类型
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
public interface MmccNurseTypeMapper extends BaseMapper<MmccNurseType> {

    IPage<MmccNurseTypeRes> queryPage(IPage<MmccNurseTypeRes> page, @Param("m") Map<String, Object> reqParam);

}