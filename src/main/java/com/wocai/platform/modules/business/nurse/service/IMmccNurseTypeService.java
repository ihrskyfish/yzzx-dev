package com.wocai.platform.modules.business.nurse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseType;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseTypeReq;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseTypeRes;
import com.wocai.platform.modules.business.nurse.vo.MmccTypeVo;

import java.util.List;

/**
 * @Description: 知识护理类型
 * @Author: lq
 * @Date: 2023-05-23
 * @Version: V1.0
 */
public interface IMmccNurseTypeService extends BaseService<MmccNurseType> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccNurseTypeReq
     * @return
     */
    IPage<MmccNurseTypeRes> queryPageList(IPage<MmccNurseTypeRes> page, MmccNurseTypeReq mmccNurseTypeReq);

    /**
     * 数据重复校验
     * @param mmccNurseTypeReq
     * @return
     */
    boolean doDuplicateCheck(MmccNurseTypeReq mmccNurseTypeReq);

    /**
     * 新增
     * @param mmccNurseTypeReq
     * @return
     */
    void saveMain(MmccNurseTypeReq mmccNurseTypeReq);

    /**
     * 编辑
     * @param mmccNurseTypeReq
     * @return
     */
    void updateMain(MmccNurseTypeReq mmccNurseTypeReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccNurseTypeRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    void enable(String id);

    List<MmccTypeVo> getAllTypeList();

}