package com.wocai.platform.modules.business.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.business.demo.dto.TestDemoReq;
import com.wocai.platform.modules.business.demo.entity.TestDemo;
import com.wocai.platform.modules.business.demo.vo.TestDemoRes;
import com.wocai.platform.modules.business.demo.service.ITestDemoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  单表测试
 * @Author: houws
 * @Date: 2022-07-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="单表测试")
@RestController
@RequestMapping("/test/demo")
public class TestDemoController extends BaseController<TestDemo, ITestDemoService> {
	@Autowired
	private ITestDemoService testDemoService;
	
	/**
     * 分页列表查询
     *
     * @param testDemoReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "单表测试-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "单表测试-分页列表查询", notes = "单表测试-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(TestDemoReq testDemoReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<TestDemoRes> page = new Page<>(pageNo, pageSize);
        IPage<TestDemoRes> pageList = testDemoService.queryPageList(page, testDemoReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param testDemoReq
     * @return
     */
    @AutoLog(value = "单表测试-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "单表测试-添加", notes = "单表测试-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody TestDemoReq testDemoReq) {
        testDemoService.saveMain(testDemoReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param testDemoReq
     * @return
     */
    @AutoLog(value = "单表测试-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "单表测试-编辑", notes = "单表测试-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody TestDemoReq testDemoReq) {
        testDemoService.updateMain(testDemoReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "单表测试-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "单表测试-通过id删除", notes = "单表测试-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        testDemoService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "单表测试-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "单表测试-批量删除", notes = "单表测试-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        testDemoService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "单表测试-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "单表测试-通过id查询", notes = "单表测试-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        TestDemoRes testDemoRes = testDemoService.getMainById(id);
        return Result.ok(testDemoRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, TestDemo testDemo) {
		 return super.exportXls(request, testDemo, TestDemo.class,"单表测试列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, TestDemo.class);
	 }

}