package com.wocai.platform.modules.app.user.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.enums.EnumRedisKeys;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.entity.BaseEntity;
import com.wocai.platform.common.util.*;
import com.wocai.platform.common.util.encryption.AesEncryptUtil;
import com.wocai.platform.modules.app.user.dto.VxUserGestationalWeekDto;
import com.wocai.platform.modules.app.user.dto.WxPhoneCodeDTO;
import com.wocai.platform.modules.app.user.service.VxUserService;
import com.wocai.platform.modules.app.user.vo.AppUserLoginByCodeVo;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 11:13
 **/
@Service
@Slf4j
public class VxUserServiceImpl implements VxUserService {
    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Resource
    private UserMapper userMapper;

    @Override
    public AppUserLoginByCodeVo loginByCode(String code) {
        WxMaJscode2SessionResult wxMaJscode2SessionResult;
        System.out.println(wxMaService);
        try {
            wxMaJscode2SessionResult = wxMaService.jsCode2SessionInfo(code);
//            WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo();

//            wxMaJscode2SessionResult = wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            log.error("获取微信用户openid失败");
            throw new BizException(e);
        }
        String openid = wxMaJscode2SessionResult.getOpenid();
        String unionid = wxMaJscode2SessionResult.getUnionid();
        String sessionKey = wxMaJscode2SessionResult.getSessionKey();

        //保存微信会话,用于获取微信授权用户基本信息
        redisTemplate.opsForValue().set(EnumRedisKeys.WX_SESSION_KEY.getCode() + openid, sessionKey, 24, TimeUnit.HOURS);

        User user = userMapper.selectOne(
                Wrappers.<User>lambdaQuery()
                        .last("limit 1")
                        .eq(User::getDelFlag, CommonConstant.DEL_FLAG_0)
                        .eq(User::getOpenid, openid));

        if (ObjectUtil.isNull(user)) {
            //用户信息(openid,unionid,头像,昵称)没有保存在本地
            user = new User();
            user.setOpenid(openid);
            user.setUnionid(unionid);
            user.setNickName("用户" + RandomUtil.randomString(5));
//            user.setAvatarUrl("files/default/2023/07/13/018677B9C3844020A839DB278736AA88/yzzx.jpeg");
            user.setAvatarUrl("files/default/2024/01/25/C581D75AF62149BB96250A09042F5AE5/z8ZLpSDEuC2Pd1d5b28fea3e1720df4cbcd13d2a427c.jpg");
            userMapper.insert(user);
        }
        String token="";
        try {
            token = AesEncryptUtil.encrypt(TokenUtil.getToken(user.getId())).trim();
        }catch (Exception e){
            throw new BizException("token生成失败");
        }
        redisUtil.set(EnumRedisKeys.LOGIN_TOKEN.getCode() + ":" + user.getId() , token);


        AppUserLoginByCodeVo result = new AppUserLoginByCodeVo();
        BeanUtils.copyProperties(user,result);
        if (StringUtils.isNotEmpty(user.getAvatarUrl())){
            result.setAvatarUrl(ServerConfig.plat_upload_head_path+"/"+user.getAvatarUrl());
        }
        result.setToken(token);
        Integer gestationalWeek = user.getGestationalWeek();
        if (gestationalWeek!=null){
//            Date gestationalTime = user.getGestationalTime();
//            LocalDate localDate = gestationalTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate now = LocalDate.now();
//            long weeks = ChronoUnit.WEEKS.between(localDate, now);
//            result.setGestationalWeek(String.valueOf((Integer.valueOf(gestationalWeek)+weeks)));
            result.setGestationalWeek(gestationalWeek);
        }
        return result;
    }

    @Override
    public void bindPhone(WxPhoneCodeDTO wxPhoneCodeDTO, String userId) {
        User user = userMapper.selectOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getDelFlag, 0)
                        .eq(User::getId, userId).last("limit 1"));
        if (ObjectUtil.isNull(user)) {
            throw new BizException("用户不存在,请重新登录");
        }

        WxMaPhoneNumberInfo phoneNoInfo;
        try {
            phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(wxPhoneCodeDTO.getCode());
        } catch (WxErrorException e) {
            log.error("获取微信用户手机号失败");
            throw new BizException(e);
        }
        User userMember = userMapper.selectOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getDelFlag, 0)
                        .eq(User::getPhone, phoneNoInfo.getPhoneNumber())
                        .ne(User::getId,userId));
        if (userMember!=null){
            user.setPhone(phoneNoInfo.getPhoneNumber());
            user.setNickName(userMember.getNickName());
            user.setMomBirthday(userMember.getMomBirthday());
            user.setBabyBirthday(userMember.getBabyBirthday());
            user.setGestationalWeek(userMember.getGestationalWeek());
            user.setGestationalTime(new Date());
            //将web端用户新增的数据删除
            userMapper.deleteById(userMember.getId());
        }else {
            user.setPhone(phoneNoInfo.getPhoneNumber());
        }
        userMapper.updateById(user);

    }

    @Override
    public AppUserLoginByCodeVo loginByCodeTest(String code) {
        User user = userMapper.selectById(code);
        if (user==null){
            throw new BizException("用户不存在");
        }
        String token="";
        try {
            token = AesEncryptUtil.encrypt(TokenUtil.getToken(user.getId())).trim();
        }catch (Exception e){
            throw new BizException("token生成失败");
        }
        redisUtil.set(EnumRedisKeys.LOGIN_TOKEN.getCode() + ":" + user.getId() , token);
        AppUserLoginByCodeVo result = new AppUserLoginByCodeVo();
        BeanUtils.copyProperties(user,result);
        result.setToken(token);
        Integer gestationalWeek = user.getGestationalWeek();
        if (gestationalWeek!=null){
//            Date gestationalTime = user.getGestationalTime();
//            LocalDate localDate = gestationalTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate now = LocalDate.now();
//            long weeks = ChronoUnit.WEEKS.between(localDate, now);
//            result.setGestationalWeek(String.valueOf((Integer.valueOf(gestationalWeek)+weeks)));
            result.setGestationalWeek(gestationalWeek);
        }
        return result;
    }

    @Override
    public void gestationalWeek(VxUserGestationalWeekDto vxUserGestationalWeekDto, String userId) {
        User user = userMapper.selectById(userId);
        if (user==null || !"0".equalsIgnoreCase(user.getDelFlag().toString())) {
            throw new BizException("该用户不存在");
        }
        user.setGestationalWeek(vxUserGestationalWeekDto.getGestationalWeek());
        user.setGestationalTime(new Date());
        userMapper.updateById(user);
    }
}
