package com.wocai.platform.modules.business.nurse.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetailsFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseDetailsFileRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 护理知识图片
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
public interface MmccNurseDetailsFileMapper extends BaseMapper<MmccNurseDetailsFile> {

    IPage<MmccNurseDetailsFileRes> queryPage(IPage<MmccNurseDetailsFileRes> page, @Param("m") Map<String, Object> reqParam);

}