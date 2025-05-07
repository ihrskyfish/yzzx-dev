package com.wocai.platform.modules.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.constant.DefContants;
import com.wocai.platform.common.system.api.ISysBaseAPI;
import com.wocai.platform.common.system.util.JwtUtil;
import com.wocai.platform.common.system.vo.LoginUser;
import com.wocai.platform.common.util.PasswordUtil;
import com.wocai.platform.common.util.RedisUtil;
import com.wocai.platform.common.util.encryption.AesEncryptUtil;
import com.wocai.platform.common.util.encryption.EncryptedString;
import com.wocai.platform.common.util.oConvertUtils;
import com.wocai.platform.common.util.sms.DySmsHelper;
import com.wocai.platform.modules.system.entity.SysDepart;
import com.wocai.platform.modules.system.entity.SysUser;
import com.wocai.platform.modules.system.service.ISysDepartService;
import com.wocai.platform.modules.system.service.ISysLogService;
import com.wocai.platform.modules.system.service.ISysUserService;
import com.wocai.platform.modules.system.vo.SysLoginModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping({"/sys"})
@Api(
        tags = {"用户登录"}
)
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private ISysLogService logService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysDepartService sysDepartService;

    public LoginController() {
    }

    @RequestMapping(
            value = {"/login"},
            method = {RequestMethod.POST}
    )
    @ApiOperation("登录接口")
    public Result<JSONObject> login(@RequestBody SysLoginModel sysLoginModel) throws Exception {
        Result<JSONObject> result = new Result();
        String username = sysLoginModel.getUsername();
        String password = AesEncryptUtil.desEncrypt(sysLoginModel.getPassword());
        if (StringUtils.isBlank(password)) {
            result.error500("用户名或密码错误");
            return result;
        } else {
            SysUser sysUser = this.sysUserService.getUserByName(username);
            result = this.sysUserService.checkUserIsEffective(sysUser);
            if (!result.isSuccess()) {
                return result;
            } else {
                String userpassword = PasswordUtil.encrypt(username, password.trim(), sysUser.getSalt());
                log.info("userpassword is:"+userpassword) ;
                String syspassword = sysUser.getPassword();
                if (!syspassword.equals(userpassword)) {
                    result.error500("用户名或密码错误");
                    return result;
                } else {
                    this.userInfo(sysUser, result);
                    this.sysBaseAPI.addLog("用户名: " + username + ",登录成功！", 1, (Integer) null);
                    return result;
                }
            }
        }
    }

    @RequestMapping({"/logout"})
    public Result<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        LoginUser sysUser = (LoginUser) subject.getPrincipal();
        this.sysBaseAPI.addLog("用户名: " + sysUser.getRealname() + ",退出成功！", 1, (Integer) null);
        log.info(" 用户名:  " + sysUser.getRealname() + ",退出成功！ ");
        subject.logout();
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        this.redisUtil.del(new String[]{"PREFIX_USER_TOKEN_" + token});
        this.redisUtil.del(new String[]{"loginUser_cacheRules::Roles_" + sysUser.getUsername()});
        this.redisUtil.del(new String[]{"loginUser_cacheRules::Permissions_" + sysUser.getUsername()});
        return Result.ok("退出登录成功！");
    }

    @GetMapping({"loginfo"})
    public Result<JSONObject> loginfo() {
        Result<JSONObject> result = new Result();
        JSONObject obj = new JSONObject();
        Calendar calendar = new GregorianCalendar();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Date dayStart = calendar.getTime();
        calendar.add(5, 1);
        Date dayEnd = calendar.getTime();
        Long totalVisitCount = this.logService.findTotalVisitCount();
        obj.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = this.logService.findTodayVisitCount(dayStart, dayEnd);
        obj.put("todayVisitCount", todayVisitCount);
        Long todayIp = this.logService.findTodayIp(dayStart, dayEnd);
        obj.put("todayIp", todayIp);
        result.setResult(obj);
        result.success("登录成功");
        return result;
    }

    @GetMapping({"visitInfo"})
    public Result<List<Map<String, Object>>> visitInfo() {
        Result<List<Map<String, Object>>> result = new Result();
        Calendar calendar = new GregorianCalendar();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(5, 1);
        Date dayEnd = calendar.getTime();
        calendar.add(5, -7);
        Date dayStart = calendar.getTime();
        List<Map<String, Object>> list = this.logService.findVisitCount(dayStart, dayEnd);
        result.setResult(oConvertUtils.toLowerCasePageList(list));
        return result;
    }

    @RequestMapping(
            value = {"/selectDepart"},
            method = {RequestMethod.PUT}
    )
    public Result<?> selectDepart(@RequestBody SysUser user) {
        String username = user.getUsername();
        if (oConvertUtils.isEmpty(username)) {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            username = sysUser.getUsername();
        }

        String orgCode = user.getOrgCode();
        this.sysUserService.updateUserDepart(username, orgCode);
        return Result.ok();
    }

    @PostMapping({"/sms"})
    public Result<String> sms(@RequestBody JSONObject jsonObject) {
        Result<String> result = new Result();
        String mobile = jsonObject.get("mobile").toString();
        String smsmode = jsonObject.get("smsmode").toString();
        log.info(mobile);
        Object object = this.redisUtil.get(mobile);
        if (object != null) {
            result.setMessage("验证码10分钟内，仍然有效！");
            result.setSuccess(false);
            return result;
        } else {
            String captcha = RandomUtil.randomNumbers(6);

            try {
                boolean b = false;
                SysUser sysUser;
                if ("1".equals(smsmode)) {
                    sysUser = this.sysUserService.getUserByPhone(mobile);
                    if (sysUser != null) {
                        result.error500(" 手机号已经注册，请直接登录！");
                        this.sysBaseAPI.addLog("手机号已经注册，请直接登录！", 1, (Integer) null);
                        return result;
                    }

                    b = DySmsHelper.sendSms(mobile, captcha, "");
                } else {
                    sysUser = this.sysUserService.getUserByPhone(mobile);
                    result = this.sysUserService.checkUserIsEffective(sysUser);
                    if (!result.isSuccess()) {
                        return result;
                    }

                    if ("0".equals(smsmode)) {
                        b = DySmsHelper.sendSms(mobile, captcha, "");
                    } else if ("2".equals(smsmode)) {
                        b = DySmsHelper.sendSms(mobile, captcha, "");
                    }
                }

                if (!b) {
                    result.setMessage("短信验证码发送失败,请稍后重试");
                    result.setSuccess(false);
                    return result;
                }

                this.redisUtil.set(mobile, captcha, 600L);
                result.setResult(captcha);
                result.setSuccess(true);
            } catch (ClientException var9) {
                var9.printStackTrace();
            }

            return result;
        }
    }

    @PostMapping({"/phoneLogin"})
    public Result<JSONObject> login(@RequestBody JSONObject jsonObject) {
        new Result();
        String phone = jsonObject.getString("mobile");
        SysUser sysUser = this.sysUserService.getUserByPhone(phone);
        Result<JSONObject> result = this.sysUserService.checkUserIsEffective(sysUser);
        if (!result.isSuccess()) {
            return result;
        } else {
            String smscode = jsonObject.getString("captcha");
            Object code = this.redisUtil.get(phone);
            if (!smscode.equals(code)) {
                result.setMessage("手机验证码错误");
                return result;
            } else {
                this.userInfo(sysUser, result);
                this.sysBaseAPI.addLog("用户名: " + sysUser.getUsername() + ",登录成功！", 1, (Integer) null);
                return result;
            }
        }
    }

    private Result<JSONObject> userInfo(SysUser sysUser, Result<JSONObject> result) {
        String syspassword = sysUser.getPassword();
        String username = sysUser.getUsername();
        String token = JwtUtil.sign(username, syspassword);
        this.redisUtil.set("PREFIX_USER_TOKEN_" + token, token);
        this.redisUtil.expire("PREFIX_USER_TOKEN_" + token, 12 * 60 * 60L);
        JSONObject obj = new JSONObject();
        List<SysDepart> departs = this.sysDepartService.queryUserDeparts(sysUser.getId());
        obj.put("departs", departs);
        if (departs != null && departs.size() != 0) {
            if (departs.size() == 1) {
                this.sysUserService.updateUserDepart(username, ((SysDepart) departs.get(0)).getOrgCode());
                obj.put("multi_depart", 1);
            } else {
                obj.put("multi_depart", 2);
            }
        } else {
            obj.put("multi_depart", 0);
        }

        obj.put("token", token);
        obj.put("userInfo", sysUser);
        result.setResult(obj);
        result.success("登录成功");
        return result;
    }

    @GetMapping({"/getEncryptedString"})
    public Result<Map<String, String>> getEncryptedString() {
        Result<Map<String, String>> result = new Result();
        Map<String, String> map = new HashMap();
        map.put("key", EncryptedString.key);
        map.put("iv", EncryptedString.iv);
        result.setResult(map);
        return result;
    }
}
