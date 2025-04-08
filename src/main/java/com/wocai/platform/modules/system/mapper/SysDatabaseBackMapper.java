package com.wocai.platform.modules.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.system.entity.SysDatabaseBack;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.system.vo.SysDatabaseBackRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 数据库备份表
 * @Author: wocai
 * @Date: 2022-07-04
 * @Version: V1.0
 */
public interface SysDatabaseBackMapper extends BaseMapper<SysDatabaseBack> {

    IPage<SysDatabaseBackRes> queryPage(IPage<SysDatabaseBackRes> page, @Param("m") Map<String, Object> reqParam);

}