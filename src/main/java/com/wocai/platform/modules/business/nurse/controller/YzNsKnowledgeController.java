package com.wocai.platform.modules.business.nurse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.business.nurse.dto.YzNsKnowledgeReq;
import com.wocai.platform.modules.business.nurse.entity.YzNsKnowledge;
import com.wocai.platform.modules.business.nurse.vo.YzNsKnowledgeRes;
import com.wocai.platform.modules.business.nurse.service.IYzNsKnowledgeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  护理知识
 * @Author: why
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="护理知识")
@RestController
@RequestMapping("/nurse/konwledge")
public class YzNsKnowledgeController extends BaseController<YzNsKnowledge, IYzNsKnowledgeService> {
	@Autowired
	private IYzNsKnowledgeService yzNsKnowledgeService;
	
	/**
     * 分页列表查询
     *
     * @param yzNsKnowledgeReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "护理知识-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "护理知识-分页列表查询", notes = "护理知识-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(YzNsKnowledgeReq yzNsKnowledgeReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<YzNsKnowledgeRes> page = new Page<>(pageNo, pageSize);
        IPage<YzNsKnowledgeRes> pageList = yzNsKnowledgeService.queryPageList(page, yzNsKnowledgeReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yzNsKnowledgeReq
     * @return
     */
    @AutoLog(value = "护理知识-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识-添加", notes = "护理知识-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody YzNsKnowledgeReq yzNsKnowledgeReq) {
        yzNsKnowledgeService.saveMain(yzNsKnowledgeReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param yzNsKnowledgeReq
     * @return
     */
    @AutoLog(value = "护理知识-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识-编辑", notes = "护理知识-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody YzNsKnowledgeReq yzNsKnowledgeReq) {
        yzNsKnowledgeService.updateMain(yzNsKnowledgeReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "护理知识-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识-通过id删除", notes = "护理知识-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        yzNsKnowledgeService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "护理知识-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理知识-批量删除", notes = "护理知识-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        yzNsKnowledgeService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "护理知识-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "护理知识-通过id查询", notes = "护理知识-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        YzNsKnowledgeRes yzNsKnowledgeRes = yzNsKnowledgeService.getMainById(id);
        return Result.ok(yzNsKnowledgeRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, YzNsKnowledge yzNsKnowledge) {
		 return super.exportXls(request, yzNsKnowledge, YzNsKnowledge.class,"护理知识列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, YzNsKnowledge.class);
	 }


     /**
      * 分页列表查询
      *
      * @param id
      * @param pageNo
      * @param pageSize
      * @return
      */
     @AutoLog(value = "护理知识-分页列表查询", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "护理知识-分页列表查询", notes = "护理知识-分页列表查询")
     @GetMapping(value = "/list/getCategoryById")
     public Result queryPageList(@RequestParam(name = "category_id", required = true) String id, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
         Page<YzNsKnowledgeRes> page = new Page<>(pageNo, pageSize);
         IPage<YzNsKnowledgeRes> pageList = yzNsKnowledgeService.queryPageListByCategoryId(page, id);
         return Result.ok(pageList);
     }

}