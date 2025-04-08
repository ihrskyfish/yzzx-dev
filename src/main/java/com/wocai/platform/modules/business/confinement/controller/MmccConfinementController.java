package com.wocai.platform.modules.business.confinement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.confinement.dto.MmccConfinementReq;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementUser;
import com.wocai.platform.modules.business.confinement.vo.MmccConfinementRes;
import com.wocai.platform.modules.business.confinement.service.IMmccConfinementService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.confinement.vo.MmccUserLikesVo;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 产期圈
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "产期圈故事管理")
@RestController
@RequestMapping("/confinement")
public class MmccConfinementController extends BaseController<MmccConfinement, IMmccConfinementService> {
    @Autowired
    private IMmccConfinementService mmccConfinementService;

    /**
     * 分页列表查询
     *
     * @param mmccConfinementReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "产期圈-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "产期圈-分页列表查询", notes = "产期圈-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccConfinementRes>> queryPageList(MmccConfinementReq mmccConfinementReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccConfinementRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccConfinementRes> pageList = mmccConfinementService.queryPageList(page, mmccConfinementReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccConfinementReq
     * @return
     */
    @AutoLog(value = "产期圈-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "产期圈-添加", notes = "产期圈-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccConfinementReq mmccConfinementReq) {
        mmccConfinementService.saveMain(mmccConfinementReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccConfinementReq
     * @return
     */
    @AutoLog(value = "产期圈-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "产期圈-编辑", notes = "产期圈-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccConfinementReq mmccConfinementReq) {
        mmccConfinementService.updateMain(mmccConfinementReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "产期圈-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "产期圈-通过id删除", notes = "产期圈-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccConfinementService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "产期圈-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "产期圈-批量删除", notes = "产期圈-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccConfinementService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "产期圈-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "产期圈-通过id查询", notes = "产期圈-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccConfinementRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccConfinementRes mmccConfinementRes = mmccConfinementService.getMainById(id);
        return Result.toSuccess(mmccConfinementRes);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @GetMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MmccConfinement mmccConfinement) {
        return super.exportXls(request, mmccConfinement, MmccConfinement.class, "产期圈列表");
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
//        return super.importExcel(request, response, MmccConfinement.class);
//    }


    @AutoLog(value = "点赞列表根据故事id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "点赞列表根据故事id查询", notes = "点赞列表根据故事id查询")
    @GetMapping(value = "/userLikes")
    public Result<IPage<MmccUserLikesVo>> userLikes(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "id") String id) {
        Page<MmccConfinementUser> page = new Page<>(pageNo, pageSize);
        IPage<MmccUserLikesVo> pageList = mmccConfinementService.userLikes(page,id);
        return Result.toSuccess(pageList);
    }

}