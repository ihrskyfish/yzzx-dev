package com.wocai.platform.modules.app.my.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.entity.BaseEntity;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementPageVo;
import com.wocai.platform.modules.app.display.service.AppDisplayService;
import com.wocai.platform.modules.app.display.vo.AppDisplayMyBookListVo;
import com.wocai.platform.modules.app.my.dto.MyImagesDto;
import com.wocai.platform.modules.app.my.dto.MyUpdateUserDto;
import com.wocai.platform.modules.app.my.service.MyService;
import com.wocai.platform.modules.app.my.vo.*;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsVo;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.baby.mapper.MmccBabyUltrasoundMapper;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationUser;
import com.wocai.platform.modules.business.communication.mapper.MmccCommunicationCircleMapper;
import com.wocai.platform.modules.business.communication.mapper.MmccCommunicationUserMapper;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementFile;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementUser;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementFileMapper;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementMapper;
import com.wocai.platform.modules.business.confinement.mapper.MmccConfinementUserMapper;
import com.wocai.platform.modules.business.display.dto.MmccServiceUserReq;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import com.wocai.platform.modules.business.display.mapper.MmccServiceUserMapper;
import com.wocai.platform.modules.business.display.service.impl.MmccServiceUserServiceImpl;
import com.wocai.platform.modules.business.health.entity.MmccHealthCheck;
import com.wocai.platform.modules.business.health.mapper.MmccHealthCheckMapper;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseUser;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseDetailsMapper;
import com.wocai.platform.modules.business.nurse.mapper.MmccNurseUserMapper;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import com.wocai.platform.modules.system.entity.SysUser;
import com.wocai.platform.modules.system.service.ISysUserService;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.taskdefs.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.metadata.ItemMetadata;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 09:55
 **/
@Service
@Slf4j
public class MyServiceImpl implements MyService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private AppDisplayService appDisplayService;


    @Resource
    private MmccHealthCheckMapper mmccHealthCheckMapper;

    @Resource
    private MmccBabyUltrasoundMapper mmccBabyUltrasoundMapper;

    @Resource
    private MmccConfinementMapper mmccConfinementMapper;

    @Resource
    private MmccConfinementUserMapper mmccConfinementUserMapper;

    @Resource
    private MmccNurseDetailsMapper mmccNurseDetailsMapper;

    @Resource
    private MmccNurseUserMapper mmccNurseUserMapper;

    @Resource
    private MmccConfinementFileMapper mmccConfinementFileMapper;

    @Resource
    private ISysUserService sysUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private MmccServiceUserMapper mmccServiceUser;

    @Autowired
    private MmccServiceUserServiceImpl mmccServiceUserService;

    @Override
    public void updateMyInformation(MyUpdateUserDto myUpdateUserDto, ReqUser reqUser) {
        User user = userMapper.selectById(reqUser.getUserId());
        if (user == null || !"0".equalsIgnoreCase(user.getDelFlag().toString())) {
            throw new BizException("该用户不存在");
        }

        if (user.getMomBirthday() != null && myUpdateUserDto.getMomBirthday()!=null) {
            if (!myUpdateUserDto.getMomBirthday().equals(user.getMomBirthday())) {
                throw new BizException("妈妈生日不可以修改");
            }
        }

        // 用户修改昵称后 将之前的预约记录的用户名称统一为新名称
        if (!myUpdateUserDto.getNickName().equals(user.getNickName())){
            LambdaQueryWrapper<MmccServiceUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(MmccServiceUser::getUserName,user.getNickName())
                    .eq(MmccServiceUser::getUserPhone,user.getPhone())
                    .eq(BaseEntity::getDelFlag, CommonConstant.DEL_FLAG_0);
            List<MmccServiceUser> mmccServiceUsers = mmccServiceUser.selectList(lambdaQueryWrapper);
            mmccServiceUsers.forEach(item ->{
                item.setUserName(myUpdateUserDto.getNickName());
                MmccServiceUserReq mmccServiceUserReq = new MmccServiceUserReq();
                BeanUtils.copyProperties(item,mmccServiceUserReq);
                mmccServiceUserService.updateMain(mmccServiceUserReq);
            });
        }

        BeanUtils.copyProperties(myUpdateUserDto, user);
        user.setGestationalWeek(myUpdateUserDto.getGestationalWeek());
        user.setGestationalTime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public MyPregnancyArchivesVo MyPregnancyArchives(ReqUser reqUser) {
        MyPregnancyArchivesVo myPregnancyArchivesVo = new MyPregnancyArchivesVo();
        LocalDateTime now = LocalDateTime.now();
        List<MyMoodVo> myMoodVos = new ArrayList<>();
        List<MyWeightVo> myWeightVos = new ArrayList<>();
        List<MyThreeCircumferencesVo> myThreeCircumferencesVos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LocalDateTime minusDays = now.minusDays(i);
            int year = minusDays.getYear();
            int monthValue = minusDays.getMonthValue();
            int dayOfMonth = minusDays.getDayOfMonth();
            MmccHealthCheck mmccHealthCheck = mmccHealthCheckMapper.selectOne(
                    Wrappers.<MmccHealthCheck>lambdaQuery()
                            .eq(MmccHealthCheck::getDelFlag, 0)
                            .eq(MmccHealthCheck::getUserId, reqUser.getUserId())
                            .eq(MmccHealthCheck::getYear, year)
                            .eq(MmccHealthCheck::getMonth, monthValue)
                            .eq(MmccHealthCheck::getDay, dayOfMonth)
                            .last("limit 1"));
            MyMoodVo myMoodVo = new MyMoodVo();
            MyWeightVo myWeightVo = new MyWeightVo();
            MyThreeCircumferencesVo myThreeCircumferencesVo = new MyThreeCircumferencesVo();
            if (mmccHealthCheck != null && "0".equalsIgnoreCase(mmccHealthCheck.getDelFlag().toString())) {
                BeanUtils.copyProperties(mmccHealthCheck, myMoodVo);
                BeanUtils.copyProperties(mmccHealthCheck, myWeightVo);
                BeanUtils.copyProperties(mmccHealthCheck, myThreeCircumferencesVo);
            }
            myMoodVos.add(myMoodVo);
            myWeightVos.add(myWeightVo);
            myThreeCircumferencesVos.add(myThreeCircumferencesVo);
        }
//        myPregnancyArchivesVo.setMyMoodVos(myMoodVos);
//        myPregnancyArchivesVo.setMyThreeCircumferencesVos(myThreeCircumferencesVos);
//        myPregnancyArchivesVo.setMyWeightVos(myWeightVos);
        return myPregnancyArchivesVo;
    }

    @Override
    public MyPregnancyArchivesVo MyPregnancyArchivesV2(ReqUser reqUser) {
        MyPregnancyArchivesVo result = new MyPregnancyArchivesVo();
        User user = userMapper.selectById(reqUser.getUserId());
        if (user == null || !"0".equalsIgnoreCase(user.getDelFlag().toString())) {
            throw new BizException("该用户不存在");
        }
        Integer gestationalWeek = user.getGestationalWeek();
        Date gestationalTime = user.getGestationalTime();
        //孕周开始第一天
        LocalDate localDate = gestationalTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays((gestationalWeek - 1) * 7);
        LocalDate now = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(localDate, now);
        List<MyMoodVo> myMoodVos = new ArrayList<>();
        List<MyWeightVo> myWeightVos = new ArrayList<>();
        List<MyThreeCircumferencesVo> myThreeCircumferencesVos = new ArrayList<>();
        List<LocalDate> localDates=new ArrayList<>();
        for (int i = 1; i <= daysBetween; i++) {
            LocalDate date = localDate.plusDays(i);
            int year = date.getYear();
            int monthValue = date.getMonthValue();
            int dayOfMonth = date.getDayOfMonth();
            MmccHealthCheck mmccHealthCheck = mmccHealthCheckMapper.selectOne(
                    Wrappers.<MmccHealthCheck>lambdaQuery()
                            .eq(MmccHealthCheck::getDelFlag, 0)
                            .eq(MmccHealthCheck::getUserId, reqUser.getUserId())
                            .eq(MmccHealthCheck::getYear, year)
                            .eq(MmccHealthCheck::getMonth, monthValue)
                            .eq(MmccHealthCheck::getDay, dayOfMonth)
                            .last("limit 1"));
            if (mmccHealthCheck == null) {
                mmccHealthCheck = new MmccHealthCheck();
                mmccHealthCheck.setYear(year);
                mmccHealthCheck.setMonth(monthValue);
                mmccHealthCheck.setDay(dayOfMonth);
            }
            MyMoodVo myMoodVo = new MyMoodVo();
            MyWeightVo myWeightVo = new MyWeightVo();
            MyThreeCircumferencesVo myThreeCircumferencesVo = new MyThreeCircumferencesVo();
            BeanUtils.copyProperties(mmccHealthCheck, myMoodVo);
            BeanUtils.copyProperties(mmccHealthCheck, myWeightVo);
            BeanUtils.copyProperties(mmccHealthCheck, myThreeCircumferencesVo);

            myMoodVos.add(myMoodVo);
            myWeightVos.add(myWeightVo);
            myThreeCircumferencesVos.add(myThreeCircumferencesVo);
            localDates.add(date);
        }
        List<String> moodVos = myMoodVos.stream().map(MyMoodVo::getMood).collect(Collectors.toList());
        List<BigDecimal> busts = myThreeCircumferencesVos.stream().map(MyThreeCircumferencesVo::getBust).collect(Collectors.toList());
        List<BigDecimal> waists = myThreeCircumferencesVos.stream().map(MyThreeCircumferencesVo::getWaist).collect(Collectors.toList());
        List<BigDecimal> hips = myThreeCircumferencesVos.stream().map(MyThreeCircumferencesVo::getHip).collect(Collectors.toList());
        List<BigDecimal> weights = myWeightVos.stream().map(MyWeightVo::getWeight).collect(Collectors.toList());


        result.setMyMoodVos(moodVos);
        result.setMyThreeCircumferencesVos(myThreeCircumferencesVos);
        result.setMyWeightVos(myWeightVos);
        result.setLocalDates(localDates);
        result.setBusts(busts);
        result.setWaists(waists);
        result.setHip(hips);
        result.setWeights(weights);

        return result;
}

    @Override
    public List<MyBabyProfileVo> babyProfile(ReqUser reqUser) {
        List<MyDateVo> myDateVoLisst = mmccBabyUltrasoundMapper.getAllDay(reqUser.getUserId());
        List<MyBabyProfileVo> result = myDateVoLisst
                .stream()
                .map(item -> {
                    MyBabyProfileVo myBabyProfileVo = new MyBabyProfileVo();
                    BeanUtils.copyProperties(item, myBabyProfileVo);
                    List<String> urls = mmccBabyUltrasoundMapper.selectList(
                            Wrappers.<MmccBabyUltrasound>lambdaQuery()
                                    .eq(MmccBabyUltrasound::getDelFlag, 0)
                                    .eq(MmccBabyUltrasound::getUserId, reqUser.getUserId())
                                    .eq(MmccBabyUltrasound::getYear, item.getYear())
                                    .eq(MmccBabyUltrasound::getMonth, item.getMonth())
                                    .eq(MmccBabyUltrasound::getDay, item.getDay()))
                            .stream()
                            .map(vo -> {
                                vo.setFileUrl(ServerConfig.plat_upload_head_path + "/" + vo.getFileUrl());
                                return vo;
                            })
                            .map(MmccBabyUltrasound::getFileUrl)
                            .collect(Collectors.toList());
                    myBabyProfileVo.setFileUrls(urls);
                    return myBabyProfileVo;
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<MyLikesVo> myLikes(ReqUser reqUser) {
        List<MyLikesVo> result = new ArrayList<>();
        List<String> confinementIds = mmccConfinementUserMapper.selectList(
                Wrappers.<MmccConfinementUser>lambdaQuery()
                        .eq(MmccConfinementUser::getDelFlag, 0)
                        .eq(MmccConfinementUser::getUserId, reqUser.getUserId()))
                .stream()
                .map(MmccConfinementUser::getConfinementId)
                .distinct()
                .collect(Collectors.toList());
        if (confinementIds != null && confinementIds.size() > 0) {
            result = mmccConfinementMapper.selectList(
                    Wrappers.<MmccConfinement>lambdaQuery()
                            .eq(MmccConfinement::getDelFlag, 0)
                            .in(MmccConfinement::getId, confinementIds))
                    .stream()
                    .map(item -> {
                        MyLikesVo myLikesVo = new MyLikesVo();
                        BeanUtils.copyProperties(item, myLikesVo);
                        Integer count = mmccConfinementUserMapper.selectCount(
                                Wrappers.<MmccConfinementUser>lambdaQuery()
                                        .eq(MmccConfinementUser::getDelFlag, 0)
                                        .eq(MmccConfinementUser::getConfinementId, item.getId()));
                        myLikesVo.setLikeNumber(count);


                        List<String> urls = mmccConfinementFileMapper.selectList(
                                Wrappers.<MmccConfinementFile>lambdaQuery()
                                        .eq(MmccConfinementFile::getDelFlag, 0)
                                        .eq(MmccConfinementFile::getConfinementId, item.getId()))
                                .stream()
                                .map(vo -> {
                                    vo.setFileUrl(ServerConfig.plat_upload_head_path + "/" + vo.getFileUrl());
                                    return vo;
                                })
                                .map(MmccConfinementFile::getFileUrl)
                                .collect(Collectors.toList());
                        myLikesVo.setUrls(urls);

                        SysUser user = sysUserService.getUserByName(item.getCreateBy());
                        if (user != null) {
                            if (StringUtils.isNotEmpty(user.getAvatar())) {
                                myLikesVo.setAvatar(ServerConfig.plat_upload_head_path + "/" + user.getAvatar());
                            }
                        }


                        return myLikesVo;
                    })
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public List<MyCollectionVo> myCollection(ReqUser reqUser) {
        List<MyCollectionVo> result = new ArrayList<>();
        List<String> detailesIds = mmccNurseUserMapper.selectList(
                Wrappers.<MmccNurseUser>lambdaQuery()
                        .eq(MmccNurseUser::getDelFlag, 0)
                        .eq(MmccNurseUser::getUserId, reqUser.getUserId()))
                .stream()
                .map(MmccNurseUser::getDetailesId)
                .distinct()
                .collect(Collectors.toList());
        if (detailesIds != null && detailesIds.size() > 0) {
            result = mmccNurseDetailsMapper.selectList(
                    Wrappers.<MmccNurseDetails>lambdaQuery()
                            .eq(MmccNurseDetails::getDelFlag, 0)
                            .in(MmccNurseDetails::getId, detailesIds)
                            .orderByDesc(MmccNurseDetails::getSort)
                            .orderByDesc(MmccNurseDetails::getCreateTime))
                    .stream()
                    .map(item -> {
                        MyCollectionVo myCollectionVo = new MyCollectionVo();
                        BeanUtils.copyProperties(item, myCollectionVo);
                        return myCollectionVo;
                    })
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public String imageSynthesis(MyImagesDto myImagesDto) {
        List<String> images = myImagesDto.getImages();
        if (images == null || images.size() <= 0) {
            throw new BizException("地址不可以为空");
        }
        images = images
                .stream()
                .map(item -> {
                    item = ServerConfig.plat_upload_path + "/" + item;
                    return item;
                })
                .collect(Collectors.toList());
        String path = "files/default" + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                + "/" + StringUtils.getUUID().toUpperCase() + "/" + System.currentTimeMillis() + ".png";
        File file = new File(ServerConfig.plat_upload_path + "/" + path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        mergeImagesVertically(images, ServerConfig.plat_upload_path + "/" + path);
        return ServerConfig.plat_upload_head_path + "/" + path;
    }

    @Override
    public MyPersonalInformationVo myPersonalInformation(ReqUser reqUser) {
        MyPersonalInformationVo result = new MyPersonalInformationVo();
        User user = userMapper.selectById(reqUser.getUserId());
        if (user == null || !"0".equalsIgnoreCase(user.getDelFlag().toString())) {
            throw new BizException("该用户不存在");
        }
        BeanUtils.copyProperties(user, result);
        if (StringUtils.isNotEmpty(user.getAvatarUrl())) {
            result.setAvatarKey(user.getAvatarUrl());
            result.setAvatarUrl(ServerConfig.plat_upload_head_path + "/" + user.getAvatarUrl());
        }

        if (user.getMomBirthday()!=null){
            LocalDate birthday =user.getMomBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 获取今天的日期
            LocalDate today = LocalDate.now();
            LocalDate birthdayThisYear = LocalDate.of(today.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
            //生日
            if (birthdayThisYear.equals(today)){
                result.setBirthdaysToday(true);
                LocalDateTime now = LocalDateTime.now();
                // 获取今天的结束时间，即当天的23:59:59
                LocalTime endOfDay = LocalTime.of(23, 59, 59);
                LocalDateTime endOfToday = LocalDateTime.of(now.toLocalDate(), endOfDay);
                // 计算当前时间和今天结束时间之间的秒数差
                long secondsUntilEndOfDay = ChronoUnit.SECONDS.between(now, endOfToday);
                String s = redisTemplate.opsForValue().get("birdy" + reqUser.getUserId());
                if (StringUtils.isNotEmpty(s)){
                    result.setBirthdaystatus(true);
                }else {
                    redisTemplate.opsForValue().set("birdy" + reqUser.getUserId(),"birthday",secondsUntilEndOfDay, TimeUnit.SECONDS);
                    result.setBirthdaystatus(false);
                }
            }else {
                result.setBirthdaysToday(false);
            }
        }

        // 获取预约记录数量
        List<AppDisplayMyBookListVo> appDisplayMyBookListVos = appDisplayService.mybookList(reqUser);
        result.setRecordsNum(appDisplayMyBookListVos.size());

        return result;
    }

    @Override
    public void closeBirthdayGifts(ReqUser reqUser) {
        LocalDateTime now = LocalDateTime.now();
        // 获取今天的结束时间，即当天的23:59:59
        LocalTime endOfDay = LocalTime.of(23, 59, 59);
        LocalDateTime endOfToday = LocalDateTime.of(now.toLocalDate(), endOfDay);
        // 计算当前时间和今天结束时间之间的秒数差
        long secondsUntilEndOfDay = ChronoUnit.SECONDS.between(now, endOfToday);
        redisTemplate.opsForValue().set("birdy" + reqUser.getUserId(),"birthday",secondsUntilEndOfDay, TimeUnit.SECONDS);
    }

    public static void mergeImagesVertically(List<String> images, String outputImagePath) {
        try {
            // 加载三张图片
            BufferedImage image1 = ImageIO.read(new File(images.get(0)));
            BufferedImage image2 = ImageIO.read(new File(images.get(1)));
            BufferedImage image3 = ImageIO.read(new File(images.get(2)));

            // 计算合并后图片的宽度和高度
            int width = Math.max(Math.max(image1.getWidth(), image2.getWidth()), image3.getWidth());
            int height = image1.getHeight() + image2.getHeight() + image3.getHeight();

            // 创建一个新的BufferedImage对象，宽度为三张图片中的最大宽度，高度为三张图片的总高度
            BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = mergedImage.createGraphics();

            // 将三张图片绘制到新的BufferedImage对象中
            int yOffset = 0; // 记录当前的垂直偏移量
            graphics.drawImage(image1, 0, yOffset, null);
            yOffset += image1.getHeight();
            graphics.drawImage(image2, 0, yOffset, null);
            yOffset += image2.getHeight();
            graphics.drawImage(image3, 0, yOffset, null);

            // 保存合并后的图片
            ImageIO.write(mergedImage, "png", new File(outputImagePath));
            log.info("合成后的图片地址为："+outputImagePath);

            // 释放资源
            graphics.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
