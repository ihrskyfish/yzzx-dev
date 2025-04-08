package com.wocai.platform.modules.business.display.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.display.entity.MmccServicePerson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.display.vo.MmccServicePersonRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 预约接待员管理
 * @Author: wocai
 * @Date: 2024-02-27
 * @Version: V1.0
 */
public interface MmccServicePersonMapper extends BaseMapper<MmccServicePerson> {

    IPage<MmccServicePersonRes> queryPage(IPage<MmccServicePersonRes> page, @Param("m") Map<String, Object> reqParam);

}