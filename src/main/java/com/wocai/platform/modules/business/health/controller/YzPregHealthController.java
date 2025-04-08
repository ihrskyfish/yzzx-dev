package com.wocai.platform.modules.business.health.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.health.dto.YzPregHealthReq;
import com.wocai.platform.modules.business.health.entity.YzPregHealth;
import com.wocai.platform.modules.business.health.vo.YzPregHealthRes;
import com.wocai.platform.modules.business.health.service.IYzPregHealthService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  孕期健康
 * @Author: why
 * @Date: 2023-06-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="孕期健康")
@RestController
@RequestMapping("/health")
public class YzPregHealthController extends BaseController<YzPregHealth, IYzPregHealthService> {
	@Autowired
	private IYzPregHealthService yzPregHealthService;
	
	/**
     * 分页列表查询
     *
     * @param yzPregHealthReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "孕期健康-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "孕期健康-分页列表查询", notes = "孕期健康-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<YzPregHealthRes>> queryPageList(YzPregHealthReq yzPregHealthReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<YzPregHealthRes> page = new Page<>(pageNo, pageSize);
        IPage<YzPregHealthRes> pageList = yzPregHealthService.queryPageList(page, yzPregHealthReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param yzPregHealthReq
     * @return
     */
    @AutoLog(value = "孕期健康-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期健康-添加", notes = "孕期健康-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody YzPregHealthReq yzPregHealthReq) {
        yzPregHealthService.saveMain(yzPregHealthReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param yzPregHealthReq
     * @return
     */
    @AutoLog(value = "孕期健康-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期健康-编辑", notes = "孕期健康-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody YzPregHealthReq yzPregHealthReq) {
        yzPregHealthService.updateMain(yzPregHealthReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "孕期健康-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期健康-通过id删除", notes = "孕期健康-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        yzPregHealthService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "孕期健康-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期健康-批量删除", notes = "孕期健康-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        yzPregHealthService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "孕期健康-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "孕期健康-通过id查询", notes = "孕期健康-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<YzPregHealthRes> queryById(@RequestParam(name = "id", required = true) String id) {
        YzPregHealthRes yzPregHealthRes = yzPregHealthService.getMainById(id);
        return Result.toSuccess(yzPregHealthRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, YzPregHealth yzPregHealth) {
		 return super.exportXls(request, yzPregHealth, YzPregHealth.class,"孕期健康列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, YzPregHealth.class);
//	 }

}