package com.wocai.platform.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.system.base.service.BaseService;
import com.wocai.platform.modules.system.dto.SysVersionReq;
import com.wocai.platform.modules.system.entity.SysVersion;
import com.wocai.platform.modules.system.vo.SysVersionRes;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 服务新增记录管理
 * @Author: wzw
 * @Date: 2021-11-08
 * @Version: V1.0
 */
public interface ISysVersionService extends BaseService<SysVersion> {

    /**
     * 分页列表查询
     *
     * @param page
     * @param sysVersionReq
     * @return
     */
    IPage<SysVersionRes> queryPageList(IPage<SysVersionRes> page, SysVersionReq sysVersionReq);
    /**
     * 上传文件
     *
     * @param req
     * @return
     */
    void uploadFile( SysVersionReq req) throws UnsupportedEncodingException, FileNotFoundException;

    /**
     * 数据重复校验
     * @param sysVersionReq
     * @return
     */
    boolean doDuplicateCheck(SysVersionReq sysVersionReq);

    /**
     * 新增
     * @param sysVersionReq
     * @return
     */
    void saveMain(SysVersionReq sysVersionReq);

    /**
     * 编辑
     * @param sysVersionReq
     * @return
     */
    void updateMain(SysVersionReq sysVersionReq);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    SysVersionRes getMainById(String id);

    /***
     * @描述: 批量删除
     * @param ids
     * @return:
     */
    void batchDelete(String ids);

}