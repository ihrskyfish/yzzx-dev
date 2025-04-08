package com.wocai.platform.modules.business.nurse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.business.nurse.dto.YzNsCategoryReq;
import com.wocai.platform.modules.business.nurse.entity.YzNsCategory;
import com.wocai.platform.modules.business.nurse.vo.YzNsCategoryRes;
import com.wocai.platform.modules.business.nurse.service.IYzNsCategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  护理知识分类
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="护理知识分类")
@RestController
@RequestMapping("/nurse")
public class YzNsCategoryController extends BaseController<YzNsCategory, IYzNsCategoryService> {
	@Autowired
	private IYzNsCategoryService yzNsCategoryService;
	
	/**
     * 分页列表查询
     *
     * @param yzNsCategoryReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "护理知识分类-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "护理知识分类-分页列表查询", notes = "护理知识分类-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(YzNsCategoryReq yzNsCategoryReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<YzNsCategoryRes> page = new Page<>(pageNo, pageSize);
        IPage<YzNsCategoryRes> pageList = yzNsCategoryService.queryPageList(page, yzNsCategoryReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yzNsCategoryReq
     * @return
     */
    @AutoLog(value = "护理知识分类-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识分类-添加", notes = "护理知识分类-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody YzNsCategoryReq yzNsCategoryReq) {
        yzNsCategoryService.saveMain(yzNsCategoryReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param yzNsCategoryReq
     * @return
     */
    @AutoLog(value = "护理知识分类-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识分类-编辑", notes = "护理知识分类-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody YzNsCategoryReq yzNsCategoryReq) {
        yzNsCategoryService.updateMain(yzNsCategoryReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "护理知识分类-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识分类-通过id删除", notes = "护理知识分类-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        yzNsCategoryService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "护理知识分类-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识分类-批量删除", notes = "护理知识分类-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        yzNsCategoryService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "护理知识分类-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "护理知识分类-通过id查询", notes = "护理知识分类-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        YzNsCategoryRes yzNsCategoryRes = yzNsCategoryService.getMainById(id);
        return Result.ok(yzNsCategoryRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, YzNsCategory yzNsCategory) {
		 return super.exportXls(request, yzNsCategory, YzNsCategory.class,"护理知识分类列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, YzNsCategory.class);
	 }

}