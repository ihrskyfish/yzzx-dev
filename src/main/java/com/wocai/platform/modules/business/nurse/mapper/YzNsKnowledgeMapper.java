package com.wocai.platform.modules.business.nurse.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.nurse.entity.YzNsKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.nurse.vo.YzNsKnowledgeRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 护理知识
 * @Author: wocai
 * @Date: 2023-06-02
 * @Version: V1.0
 */
public interface YzNsKnowledgeMapper extends BaseMapper<YzNsKnowledge> {

    IPage<YzNsKnowledgeRes> queryPage(IPage<YzNsKnowledgeRes> page, @Param("m") Map<String, Object> reqParam);

    IPage<YzNsKnowledgeRes> queryPageByCategoryId(Page<YzNsKnowledgeRes> page, Map<String, Object> reqParam,@Param("id") String id);
}