package com.wocai.platform.modules.business.nurse.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.nurse.entity.YzNsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.nurse.vo.YzNsCategoryRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 护理知识分类管理
 * @Author: wocai
 * @Date: 2023-06-02
 * @Version: V1.0
 */
public interface YzNsCategoryMapper extends BaseMapper<YzNsCategory> {

    IPage<YzNsCategoryRes> queryPage(IPage<YzNsCategoryRes> page, @Param("m") Map<String, Object> reqParam);

}