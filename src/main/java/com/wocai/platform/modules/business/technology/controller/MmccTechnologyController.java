package com.wocai.platform.modules.business.technology.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.technology.dto.MmccTechnologyReq;
import com.wocai.platform.modules.business.technology.entity.MmccTechnology;
import com.wocai.platform.modules.business.technology.vo.MmccTechnologyRes;
import com.wocai.platform.modules.business.technology.service.IMmccTechnologyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  工艺栏
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="公益栏目")
@RestController
@RequestMapping("/technology")
public class MmccTechnologyController extends BaseController<MmccTechnology, IMmccTechnologyService> {
	@Autowired
	private IMmccTechnologyService mmccTechnologyService;
	
	/**
     * 分页列表查询
     *
     * @param mmccTechnologyReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "工艺栏-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "工艺栏-分页列表查询", notes = "工艺栏-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccTechnologyRes>> queryPageList(MmccTechnologyReq mmccTechnologyReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccTechnologyRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccTechnologyRes> pageList = mmccTechnologyService.queryPageList(page, mmccTechnologyReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccTechnologyReq
     * @return
     */
    @AutoLog(value = "工艺栏-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "工艺栏-添加", notes = "工艺栏-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccTechnologyReq mmccTechnologyReq) {
        mmccTechnologyService.saveMain(mmccTechnologyReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccTechnologyReq
     * @return
     */
    @AutoLog(value = "工艺栏-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "工艺栏-编辑", notes = "工艺栏-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccTechnologyReq mmccTechnologyReq) {
        mmccTechnologyService.updateMain(mmccTechnologyReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工艺栏-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "工艺栏-通过id删除", notes = "工艺栏-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccTechnologyService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "工艺栏-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "工艺栏-批量删除", notes = "工艺栏-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccTechnologyService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工艺栏-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "工艺栏-通过id查询", notes = "工艺栏-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccTechnologyRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccTechnologyRes mmccTechnologyRes = mmccTechnologyService.getMainById(id);
        return Result.toSuccess(mmccTechnologyRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccTechnology mmccTechnology) {
		 return super.exportXls(request, mmccTechnology, MmccTechnology.class,"工艺栏列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccTechnology.class);
//	 }


     @AutoLog(value = "工艺栏-停用启用", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "工艺栏-停用启用", notes = "工艺栏-停用启用")
     @GetMapping(value = "/enable")
     public Result enable(@RequestParam(name = "id", required = true) String id) {
         mmccTechnologyService.enable(id);
         return Result.toSuccess();
     }

}