package com.wocai.platform.modules.business.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.health.dto.MmccExportWordDto;
import com.wocai.platform.modules.business.health.entity.MmccHealthCheck;
import com.wocai.platform.modules.business.health.dto.MmccHealthCheckReq;
import com.wocai.platform.modules.business.health.vo.*;

import java.util.List;

/**
 * @Description: 健康打卡
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface IMmccHealthCheckService extends BaseService<MmccHealthCheck> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccHealthCheckReq
     * @return
     */
    IPage<MmccHealthCheckRes> queryPageList(IPage<MmccHealthCheckRes> page, MmccHealthCheckReq mmccHealthCheckReq);

    /**
     * 数据重复校验
     * @param mmccHealthCheckReq
     * @return
     */
    boolean doDuplicateCheck(MmccHealthCheckReq mmccHealthCheckReq);

    /**
     * 新增
     * @param mmccHealthCheckReq
     * @return
     */
    void saveMain(MmccHealthCheckReq mmccHealthCheckReq);

    /**
     * 编辑
     * @param mmccHealthCheckReq
     * @return
     */
    void updateMain(MmccHealthCheckReq mmccHealthCheckReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccHealthCheckRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    IPage<MmccMoodVo> moodList(Page<MmccHealthCheck> page,String userId);

    List<MmccPregnancyArchivesVo> pregnancyArchivesList(String userId);

    List<MmccPregnancyArchivesVo> pregnancyArchivesListV2(String userId);

    List<MmccBabyProfileVo> babyProfileList(String userId);

    IPage<MmccBaByUserVo> babyUserList(Page<MmccBaByUserVo> page);

    String exportWord(MmccExportWordDto mmccExportWordDto);

}