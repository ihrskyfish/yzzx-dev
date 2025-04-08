package com.wocai.platform.modules.business.publicize.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.business.publicize.dto.YzPublicizeRoomReq;
import com.wocai.platform.modules.business.publicize.entity.YzPublicizeRoom;
import com.wocai.platform.modules.business.publicize.vo.YzPublicizeRoomRes;
import com.wocai.platform.modules.business.publicize.service.IYzPublicizeRoomService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  宣传预定
 * @Author: why
 * @Date: 2023-06-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="宣传预定")
@RestController
@RequestMapping("/publicize")
public class YzPublicizeRoomController extends BaseController<YzPublicizeRoom, IYzPublicizeRoomService> {
	@Autowired
	private IYzPublicizeRoomService yzPublicizeRoomService;
	
	/**
     * 分页列表查询
     *
     * @param yzPublicizeRoomReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "宣传预定-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "宣传预定-分页列表查询", notes = "宣传预定-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(YzPublicizeRoomReq yzPublicizeRoomReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<YzPublicizeRoomRes> page = new Page<>(pageNo, pageSize);
        IPage<YzPublicizeRoomRes> pageList = yzPublicizeRoomService.queryPageList(page, yzPublicizeRoomReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yzPublicizeRoomReq
     * @return
     */
    @AutoLog(value = "宣传预定-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宣传预定-添加", notes = "宣传预定-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody YzPublicizeRoomReq yzPublicizeRoomReq) {
        yzPublicizeRoomService.saveMain(yzPublicizeRoomReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param yzPublicizeRoomReq
     * @return
     */
    @AutoLog(value = "宣传预定-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宣传预定-编辑", notes = "宣传预定-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody YzPublicizeRoomReq yzPublicizeRoomReq) {
        yzPublicizeRoomService.updateMain(yzPublicizeRoomReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "宣传预定-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宣传预定-通过id删除", notes = "宣传预定-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        yzPublicizeRoomService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "宣传预定-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宣传预定-批量删除", notes = "宣传预定-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        yzPublicizeRoomService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "宣传预定-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "宣传预定-通过id查询", notes = "宣传预定-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        YzPublicizeRoomRes yzPublicizeRoomRes = yzPublicizeRoomService.getMainById(id);
        return Result.ok(yzPublicizeRoomRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, YzPublicizeRoom yzPublicizeRoom) {
		 return super.exportXls(request, yzPublicizeRoom, YzPublicizeRoom.class,"宣传预定列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, YzPublicizeRoom.class);
	 }

}