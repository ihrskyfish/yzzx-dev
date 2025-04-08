package com.wocai.platform.modules.business.display.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.business.display.entity.MmccServicePerson;
import com.wocai.platform.modules.business.display.dto.MmccServicePersonReq;
import com.wocai.platform.modules.business.display.vo.MmccServicePersonRes;
import com.wocai.platform.modules.business.display.vo.MmccServocePersonVO;

import java.util.List;

/**
 * @Description: 预约接待员管理
 * @Author: why
 * @Date: 2024-02-27
 * @Version: V1.0
 */
public interface IMmccServicePersonService extends BaseService<MmccServicePerson> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param mmccServicePersonReq
     * @return
     */
    IPage<MmccServicePersonRes> queryPageList(IPage<MmccServicePersonRes> page, MmccServicePersonReq mmccServicePersonReq);

    /**
     * 数据重复校验
     * @param mmccServicePersonReq
     * @return
     */
    boolean doDuplicateCheck(MmccServicePersonReq mmccServicePersonReq);

    /**
     * 新增
     * @param mmccServicePersonReq
     * @return
     */
    void saveMain(MmccServicePersonReq mmccServicePersonReq);

    /**
     * 编辑
     * @param mmccServicePersonReq
     * @return
     */
    void updateMain(MmccServicePersonReq mmccServicePersonReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    MmccServicePersonRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

    List<MmccServocePersonVO> getServicePersonList();

}