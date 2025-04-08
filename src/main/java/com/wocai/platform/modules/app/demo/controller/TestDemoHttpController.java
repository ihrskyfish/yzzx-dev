package com.wocai.platform.modules.app.demo.controller;

import com.wocai.platform.common.api.dto.IdDTO;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.aspect.annotation.NoLogin;
import com.wocai.platform.modules.app.demo.dto.TestDemoReq;
import com.wocai.platform.modules.app.demo.service.ITestDemoHttpService;
import com.wocai.platform.modules.app.demo.vo.TestDemoRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;


@Slf4j
@Api(tags = "【APP】测试接口")
@RestController
@RequestMapping("/app/test")
public class TestDemoHttpController {


    @Resource
    private ITestDemoHttpService testDemoHttpService;


    /**
     * 查询Demo单条记录免登陆
     */
    @AutoLog(value = "查询Demo单条记录-免登陆")
    @ApiOperation(value = "查询Demo单条记录-免登陆", notes = "查询Demo单条记录-免登陆", response = TestDemoRes.class)
    @PostMapping(value = "/selectDemoByIdNoLogin")
    @NoLogin
    public Result selectDemoByIdNoLogin(@RequestBody @Validated IdDTO idDTO) throws Exception {
        TestDemoRes result = testDemoHttpService.selectById(idDTO);
        return Result.ok(result);
    }

    /**
     * 查询Demo单条记录
     *
     * @param reqUser
     */
    @AutoLog(value = "查询Demo单条记录")
    @ApiOperation(value = "查询Demo单条记录", notes = "查询Demo单条记录", response = TestDemoRes.class)
    @PostMapping(value = "/selectDemoById")
    public Result selectDemoById(@ApiIgnore ReqUser reqUser, @RequestBody @Validated TestDemoReq testDemoReq) throws Exception {
        TestDemoRes result = testDemoHttpService.selectByName(testDemoReq);
        return Result.ok(result);
    }


}