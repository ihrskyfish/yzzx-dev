package com.wocai.platform.modules.business.communication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.communication.dto.MmccCommunicationCircleReq;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationUser;
import com.wocai.platform.modules.business.communication.vo.MmccCommunicationCircleRes;
import com.wocai.platform.modules.business.communication.service.IMmccCommunicationCircleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.communication.vo.MmccUserLeaveMessageVo;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 交流圈话题
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "交流圈话题")
@RestController
@RequestMapping("/communication/circle")
public class MmccCommunicationCircleController extends BaseController<MmccCommunicationCircle, IMmccCommunicationCircleService> {
    @Autowired
    private IMmccCommunicationCircleService mmccCommunicationCircleService;

    /**
     * 分页列表查询
     *
     * @param mmccCommunicationCircleReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "交流圈话题-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "交流圈话题-分页列表查询", notes = "交流圈话题-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccCommunicationCircleRes>> queryPageList(MmccCommunicationCircleReq mmccCommunicationCircleReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccCommunicationCircleRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccCommunicationCircleRes> pageList = mmccCommunicationCircleService.queryPageList(page, mmccCommunicationCircleReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccCommunicationCircleReq
     * @return
     */
    @AutoLog(value = "交流圈话题-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "交流圈话题-添加", notes = "交流圈话题-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccCommunicationCircleReq mmccCommunicationCircleReq) {
        mmccCommunicationCircleService.saveMain(mmccCommunicationCircleReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccCommunicationCircleReq
     * @return
     */
    @AutoLog(value = "交流圈话题-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "交流圈话题-编辑", notes = "交流圈话题-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccCommunicationCircleReq mmccCommunicationCircleReq) {
        mmccCommunicationCircleService.updateMain(mmccCommunicationCircleReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "交流圈话题-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "交流圈话题-通过id删除", notes = "交流圈话题-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccCommunicationCircleService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "交流圈话题-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "交流圈话题-批量删除", notes = "交流圈话题-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccCommunicationCircleService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "交流圈话题-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "交流圈话题-通过id查询", notes = "交流圈话题-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccCommunicationCircleRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccCommunicationCircleRes mmccCommunicationCircleRes = mmccCommunicationCircleService.getMainById(id);
        return Result.toSuccess(mmccCommunicationCircleRes);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @GetMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MmccCommunicationCircle mmccCommunicationCircle) {
        return super.exportXls(request, mmccCommunicationCircle, MmccCommunicationCircle.class, "交流圈话题列表");
    }

    /**
     * excel导入
     *
     * @param
     * @param
     * @return
     */
//    @PostMapping(value = "/importExcel")
//    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//        return super.importExcel(request, response, MmccCommunicationCircle.class);
//    }
    @AutoLog(value = "留言-根据话题id分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "留言-根据话题id分页列表查询", notes = "交流圈话题-根据话题id分页列表查询")
    @GetMapping(value = "/userLeaveMessage")
    public Result<IPage<MmccUserLeaveMessageVo>> userLeaveMessage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(name = "id") String id) {
        Page<MmccCommunicationUser> page = new Page<>(pageNo, pageSize);
        IPage<MmccUserLeaveMessageVo> pageList = mmccCommunicationCircleService.userLeaveMessage(page, id);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "留言-根据留言id设为精选", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "留言-根据留言id设为精选", notes = "交流圈话题-根据留言id设为精选")
    @GetMapping(value = "/setSelected")
    public Result setSelected(@RequestParam(name = "id") String id) {
        mmccCommunicationCircleService.setSelected(id);
        return Result.toSuccess();
    }

}