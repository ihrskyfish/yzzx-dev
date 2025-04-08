package com.wocai.platform.modules.business.display.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplayFiles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayFilesRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 服务展示图片表
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface MmccServiceDisplayFilesMapper extends BaseMapper<MmccServiceDisplayFiles> {

    IPage<MmccServiceDisplayFilesRes> queryPage(IPage<MmccServiceDisplayFilesRes> page, @Param("m") Map<String, Object> reqParam);

}