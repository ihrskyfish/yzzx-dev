package com.wocai.platform.modules.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.user.dto.UserReq;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import com.wocai.platform.modules.business.user.service.IAppUserService;
import com.wocai.platform.modules.business.user.vo.UserRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: user
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Service
public class AppUserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IAppUserService {
    
    @Override
    public IPage<UserRes> queryPageList(IPage<UserRes> page, UserReq appUserReq) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty(appUserReq.getUsername())){
            map.put("nickName", appUserReq.getUsername());
        }
        if (StringUtils.isNotEmpty(appUserReq.getUserPhone())){
            map.put("phone", appUserReq.getUserPhone());
        }
        IPage<UserRes> resultList = this.baseMapper.queryPage(page, map);
        List<UserRes> records = resultList.getRecords();
        for (UserRes userRes:records){
            Date startTime = userRes.getStartTime();
            Date endTime = userRes.getEndTime();
            if (startTime != null && endTime != null){
                Long starTime2 = startTime.getTime();
                Long endTime2 = endTime.getTime();
                Long num = endTime2-starTime2;//时间戳相差的毫秒数
                userRes.setStayDays(num/24/60/60/1000);
            }

        }
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(UserReq appUserReq) {
        return false;
    }

    @Override
    public void saveMain(UserReq appUserReq) {
        if (StringUtils.isNotEmpty(appUserReq.getPhone())){
            Integer count = baseMapper.selectCount(
                    Wrappers.<User>lambdaQuery()
                            .eq(User::getDelFlag, 0)
                            .eq(User::getPhone, appUserReq.getPhone()));
            if (count>0){
                throw new BizException("该手机号已存在");
            }
        }
        this.doDuplicateCheck(appUserReq);
        User appUser = new User();
        BeanUtils.copyProperties(appUserReq, appUser);
        appUser.setGestationalTime(new Date());
        this.save(appUser);
    }

    @Override
    public void updateMain(UserReq appUserReq) {
        User appUser = this.getById(appUserReq.getId());
        if (appUser == null || CommonConstant.DEL_FLAG_1.equals(appUser.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        if (StringUtils.isNotEmpty(appUserReq.getPhone())){
            Integer count = baseMapper.selectCount(
                    Wrappers.<User>lambdaQuery()
                            .eq(User::getDelFlag, 0)
                            .eq(User::getPhone, appUserReq.getPhone())
                            .ne(User::getId, appUserReq.getId()));
            if (count>0){
                throw new BizException("该手机号已存在");
            }
        }

//        if (!appUser.getGestationalWeek().equals(appUserReq.getGestationalWeek())){
        BeanUtils.copyProperties(appUserReq, appUser);
//            appUser.setGestationalTime(new Date());
//        }else {
//            BeanUtils.copyProperties(appUserReq, appUser);
//        }

        this.updateById(appUser);
    }

    @Override
    public UserRes getMainById(String id) {
        UserRes result = new UserRes();
        User appUser = this.getById(id);
        if (appUser == null || CommonConstant.DEL_FLAG_1.equals(appUser.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(appUser, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            User appUser = this.getById(id);
            if (appUser == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(User::getId, idList);
        User appUser = new User();
        appUser.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(appUser, queryWrapper);
    }
    
    
}