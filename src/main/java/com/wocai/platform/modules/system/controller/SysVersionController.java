package com.wocai.platform.modules.system.controller;
import java.util.Date;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.feign.client.HandleShellClient;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.system.dto.SysVersionReq;
import com.wocai.platform.modules.system.entity.SysVersion;
import com.wocai.platform.modules.system.service.ISysVersionService;
import com.wocai.platform.modules.system.vo.SysVersionRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * @Description:  服务新增记录管理
 * @Author: wzw
 * @Date: 2021-11-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="服务新增记录管理")
@RestController
@RequestMapping("/sys/version")
public class SysVersionController extends BaseController<SysVersion, ISysVersionService> {
	@Autowired
	private ISysVersionService sysVersionService;
	/**
     * 分页列表查询
     *
     * @param sysVersionReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "服务新增记录管理-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "服务新增记录管理-分页列表查询", notes = "服务新增记录管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(SysVersionReq sysVersionReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<SysVersionRes> page = new Page<>(pageNo, pageSize);
        IPage<SysVersionRes> pageList = sysVersionService.queryPageList(page, sysVersionReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param sysVersionReq
     * @return
     */
    @AutoLog(value = "服务新增记录管理-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务新增记录管理-添加", notes = "服务新增记录管理-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody SysVersionReq sysVersionReq) {
        sysVersionService.saveMain(sysVersionReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param sysVersionReq
     * @return
     */
    @AutoLog(value = "服务新增记录管理-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务新增记录管理-编辑", notes = "服务新增记录管理-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody SysVersionReq sysVersionReq) {
        sysVersionService.updateMain(sysVersionReq);
        return Result.ok("修改成功!");
    }
     /*
      * 接收文件
      * 更改文件名并存放文件
      * 返回json
      * */
     @PostMapping("uploadFile")
     @ApiOperation(value = "服务新增记录管理-上传文件", notes = "服务新增记录管理-上传文件")
     public Result uploadFile(@RequestParam MultipartFile file,
                              @RequestParam String sysType,
                              @RequestParam String remark) throws UnsupportedEncodingException, FileNotFoundException {
         SysVersionReq req = new SysVersionReq();
         req.setFile(file);
         req.setSysType(sysType);
         req.setRemark(remark);
         sysVersionService.uploadFile(req);
         return Result.ok("上传成功");
     }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "服务新增记录管理-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务新增记录管理-通过id删除", notes = "服务新增记录管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        sysVersionService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "服务新增记录管理-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务新增记录管理-批量删除", notes = "服务新增记录管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        sysVersionService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "服务新增记录管理-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "服务新增记录管理-通过id查询", notes = "服务新增记录管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        SysVersionRes sysVersionRes = sysVersionService.getMainById(id);
        return Result.ok(sysVersionRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, SysVersion sysVersion) {
		 return super.exportXls(request, sysVersion, SysVersion.class,"服务新增记录管理列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, SysVersion.class);
	 }

}