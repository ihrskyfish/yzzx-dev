package com.wocai.platform.modules.business.technology.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.technology.entity.MmccTechnologyFiles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.technology.vo.MmccTechnologyFilesRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 工艺栏图片表
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface MmccTechnologyFilesMapper extends BaseMapper<MmccTechnologyFiles> {

    IPage<MmccTechnologyFilesRes> queryPage(IPage<MmccTechnologyFilesRes> page, @Param("m") Map<String, Object> reqParam);

}