package com.wocai.platform.modules.app.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.app.test.dto.TestPwcReq;
import com.wocai.platform.modules.app.test.entity.TestPwc;
import com.wocai.platform.modules.app.test.vo.TestPwcRes;
import com.wocai.platform.modules.app.test.service.ITestPwcService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  公益栏增删改查
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="公益栏增删改查")
@RestController
@RequestMapping("/test")
public class TestPwcController extends BaseController<TestPwc, ITestPwcService> {
	@Autowired
	private ITestPwcService testPwcService;
	
	/**
     * 分页列表查询
     *
     * @param testPwcReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "公益栏增删改查-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "公益栏增删改查-分页列表查询", notes = "公益栏增删改查-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(TestPwcReq testPwcReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<TestPwcRes> page = new Page<>(pageNo, pageSize);
        IPage<TestPwcRes> pageList = testPwcService.queryPageList(page, testPwcReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param testPwcReq
     * @return
     */
    @AutoLog(value = "公益栏增删改查-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "公益栏增删改查-添加", notes = "公益栏增删改查-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody TestPwcReq testPwcReq) {
        testPwcService.saveMain(testPwcReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param testPwcReq
     * @return
     */
    @AutoLog(value = "公益栏增删改查-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "公益栏增删改查-编辑", notes = "公益栏增删改查-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody TestPwcReq testPwcReq) {
        testPwcService.updateMain(testPwcReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公益栏增删改查-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "公益栏增删改查-通过id删除", notes = "公益栏增删改查-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        testPwcService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "公益栏增删改查-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "公益栏增删改查-批量删除", notes = "公益栏增删改查-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        testPwcService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公益栏增删改查-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "公益栏增删改查-通过id查询", notes = "公益栏增删改查-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        TestPwcRes testPwcRes = testPwcService.getMainById(id);
        return Result.ok(testPwcRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, TestPwc testPwc) {
		 return super.exportXls(request, testPwc, TestPwc.class,"公益栏增删改查列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, TestPwc.class);
	 }

}