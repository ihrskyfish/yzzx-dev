package com.wocai.platform.modules.business.display.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayFilesReq;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplayFiles;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayFilesRes;
import com.wocai.platform.modules.business.display.service.IMmccServiceDisplayFilesService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  房间相册
 * @Author: lq
 * @Date: 2023-07-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="房间相册")
@RestController
@RequestMapping("/service/display/file")
public class MmccServiceDisplayFilesController extends BaseController<MmccServiceDisplayFiles, IMmccServiceDisplayFilesService> {
	@Autowired
	private IMmccServiceDisplayFilesService mmccServiceDisplayFilesService;
	
	/**
     * 分页列表查询
     *
     * @param mmccServiceDisplayFilesReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "房间相册-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "房间相册-分页列表查询", notes = "房间相册-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccServiceDisplayFilesRes>> queryPageList(MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccServiceDisplayFilesRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccServiceDisplayFilesRes> pageList = mmccServiceDisplayFilesService.queryPageList(page, mmccServiceDisplayFilesReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccServiceDisplayFilesReq
     * @return
     */
    @AutoLog(value = "房间相册-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间相册-添加", notes = "房间相册-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq) {
        mmccServiceDisplayFilesService.saveMain(mmccServiceDisplayFilesReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccServiceDisplayFilesReq
     * @return
     */
    @AutoLog(value = "房间相册-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间相册-编辑", notes = "房间相册-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccServiceDisplayFilesReq mmccServiceDisplayFilesReq) {
        mmccServiceDisplayFilesService.updateMain(mmccServiceDisplayFilesReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "房间相册-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间相册-通过id删除", notes = "房间相册-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccServiceDisplayFilesService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "房间相册-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间相册-批量删除", notes = "房间相册-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccServiceDisplayFilesService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "房间相册-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "房间相册-通过id查询", notes = "房间相册-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccServiceDisplayFilesRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccServiceDisplayFilesRes mmccServiceDisplayFilesRes = mmccServiceDisplayFilesService.getMainById(id);
        return Result.toSuccess(mmccServiceDisplayFilesRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccServiceDisplayFiles mmccServiceDisplayFiles) {
		 return super.exportXls(request, mmccServiceDisplayFiles, MmccServiceDisplayFiles.class,"房间相册列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccServiceDisplayFiles.class);
//	 }

}