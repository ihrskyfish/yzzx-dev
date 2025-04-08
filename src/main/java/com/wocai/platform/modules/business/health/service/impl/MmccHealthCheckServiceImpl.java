package com.wocai.platform.modules.business.health.service.impl;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.FileUtils;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.modules.app.my.vo.MyDateVo;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.baby.mapper.MmccBabyUltrasoundMapper;
import com.wocai.platform.modules.business.health.dto.MmccExportWordDto;
import com.wocai.platform.modules.business.health.entity.MmccHealthCheck;
import com.wocai.platform.modules.business.health.dto.MmccHealthCheckReq;
import com.wocai.platform.modules.business.health.vo.*;
import com.wocai.platform.modules.business.health.mapper.MmccHealthCheckMapper;
import com.wocai.platform.modules.business.health.service.IMmccHealthCheckService;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 健康打卡
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Service
@Slf4j
public class MmccHealthCheckServiceImpl extends BaseServiceImpl<MmccHealthCheckMapper, MmccHealthCheck> implements IMmccHealthCheckService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private MmccBabyUltrasoundMapper mmccBabyUltrasoundMapper;


    @Override
    public IPage<MmccHealthCheckRes> queryPageList(IPage<MmccHealthCheckRes> page, MmccHealthCheckReq mmccHealthCheckReq) {
        Map<String, Object> reqParam = new HashMap<>();
        IPage<MmccHealthCheckRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccHealthCheckReq mmccHealthCheckReq) {
        return false;
    }

    @Override
    public void saveMain(MmccHealthCheckReq mmccHealthCheckReq) {
        this.doDuplicateCheck(mmccHealthCheckReq);
        MmccHealthCheck mmccHealthCheck = new MmccHealthCheck();
        BeanUtils.copyProperties(mmccHealthCheckReq, mmccHealthCheck);
        this.save(mmccHealthCheck);
    }

    @Override
    public void updateMain(MmccHealthCheckReq mmccHealthCheckReq) {
        MmccHealthCheck mmccHealthCheck = this.getById(mmccHealthCheckReq.getId());
        if (mmccHealthCheck == null || CommonConstant.DEL_FLAG_1.equals(mmccHealthCheck.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccHealthCheckReq);
        BeanUtils.copyProperties(mmccHealthCheckReq, mmccHealthCheck);
        this.updateById(mmccHealthCheck);
    }

    @Override
    public MmccHealthCheckRes getMainById(String id) {
        MmccHealthCheckRes result = new MmccHealthCheckRes();
        MmccHealthCheck mmccHealthCheck = this.getById(id);
        if (mmccHealthCheck == null || CommonConstant.DEL_FLAG_1.equals(mmccHealthCheck.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccHealthCheck, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccHealthCheck mmccHealthCheck = this.getById(id);
            if (mmccHealthCheck == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccHealthCheck> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccHealthCheck::getId, idList);
        MmccHealthCheck mmccHealthCheck = new MmccHealthCheck();
        mmccHealthCheck.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccHealthCheck, queryWrapper);
    }

    @Override
    public IPage<MmccMoodVo> moodList(Page<MmccHealthCheck> page,String userId) {
        IPage<MmccMoodVo> result = new Page<>();
        IPage<MmccHealthCheck> mmccHealthCheckIPage = baseMapper.selectPage(page,
                Wrappers.<MmccHealthCheck>lambdaQuery()
                        .eq(MmccHealthCheck::getDelFlag, 0)
                        .eq(MmccHealthCheck::getUserId,userId)
                        .orderByDesc(MmccHealthCheck::getCreateTime));
        BeanUtils.copyProperties(mmccHealthCheckIPage, result);
        List<MmccMoodVo> collect = mmccHealthCheckIPage.getRecords()
                .stream()
                .map(item -> {
                    MmccMoodVo mmccMoodVo = new MmccMoodVo();
                    BeanUtils.copyProperties(item, mmccMoodVo);
                    User user = userMapper.selectById(item.getUserId());
                    if (user != null && "0".equalsIgnoreCase(user.getDelFlag().toString())) {
                        mmccMoodVo.setNickName(user.getNickName());
                    }
                    return mmccMoodVo;
                })
                .collect(Collectors.toList());
        result.setRecords(collect);
        return result;
    }

    @Override
    public List<MmccPregnancyArchivesVo> pregnancyArchivesList(String userId) {
        List<MmccHealthCheck> mmccHealthCheckIPage = baseMapper.selectList(
                Wrappers.<MmccHealthCheck>lambdaQuery()
                        .eq(MmccHealthCheck::getDelFlag, 0)
                        .eq(MmccHealthCheck::getUserId,userId)
                        .orderByDesc(MmccHealthCheck::getCreateTime));
        List<MmccPregnancyArchivesVo> result = mmccHealthCheckIPage
                .stream()
                .map(item -> {
                    MmccPregnancyArchivesVo mmccPregnancyArchivesVo = new MmccPregnancyArchivesVo();
                    BeanUtils.copyProperties(item, mmccPregnancyArchivesVo);
                    User user = userMapper.selectById(item.getUserId());
                    if (user != null && "0".equalsIgnoreCase(user.getDelFlag().toString())) {
                        mmccPregnancyArchivesVo.setNickName(user.getNickName());
                    }
                    return mmccPregnancyArchivesVo;
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<MmccPregnancyArchivesVo> pregnancyArchivesListV2(String userId) {
        List<MmccPregnancyArchivesVo> result=new ArrayList<>();
        User user = userMapper.selectById(userId);
        if (user == null || !"0".equalsIgnoreCase(user.getDelFlag().toString())) {
            throw new BizException("该用户不存在");
        }
        Integer gestationalWeek = user.getGestationalWeek();
        Date gestationalTime = user.getGestationalTime();
        //孕周开始第一天
        LocalDate localDate = gestationalTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays((gestationalWeek - 1) * 7);
        LocalDate now = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(localDate, now);
        for (int i = 1; i <= daysBetween; i++) {
            MmccPregnancyArchivesVo mmccPregnancyArchivesVo=new MmccPregnancyArchivesVo();
            LocalDate date = localDate.plusDays(i);
            int year = date.getYear();
            int monthValue = date.getMonthValue();
            int dayOfMonth = date.getDayOfMonth();

            MmccHealthCheck mmccHealthCheck = baseMapper.selectOne(
                    Wrappers.<MmccHealthCheck>lambdaQuery()
                            .eq(MmccHealthCheck::getDelFlag, 0)
                            .eq(MmccHealthCheck::getUserId, userId)
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
            BeanUtils.copyProperties(mmccHealthCheck,mmccPregnancyArchivesVo);
            mmccPregnancyArchivesVo.setLocalDates(date);
            result.add(mmccPregnancyArchivesVo);
        }

        return result;
    }

    @Override
    public List<MmccBabyProfileVo> babyProfileList(String userId) {
        List<MyDateVo> myDateVoLisst=mmccBabyUltrasoundMapper.getAllPageDay(userId);
        List<MmccBabyProfileVo> result = myDateVoLisst
                .stream()
                .map(item -> {
                    MmccBabyProfileVo mmccBabyProfileVo = new MmccBabyProfileVo();
                    BeanUtils.copyProperties(item, mmccBabyProfileVo);
                    List<String> collect = mmccBabyUltrasoundMapper.selectList(
                            Wrappers.<MmccBabyUltrasound>lambdaQuery()
                                    .eq(MmccBabyUltrasound::getDelFlag, 0)
                                    .eq(MmccBabyUltrasound::getUserId, userId)
                                    .eq(MmccBabyUltrasound::getYear, item.getYear())
                                    .eq(MmccBabyUltrasound::getMonth, item.getMonth())
                                    .eq(MmccBabyUltrasound::getDay, item.getDay()))
                            .stream()
                            .map(vo -> {
                                String url = vo.getFileUrl();
                                return ServerConfig.plat_upload_head_path +"/"+ url;
                            })
                            .collect(Collectors.toList());
                    mmccBabyProfileVo.setFileUrls(collect);
                    return mmccBabyProfileVo;
                })
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public IPage<MmccBaByUserVo> babyUserList(Page<MmccBaByUserVo> page) {
        IPage<MmccBaByUserVo> result=baseMapper.babyUserList(page);
        return result;
    }

    @Override
    public String exportWord(MmccExportWordDto mmccExportWordDto) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userMapper.selectById(mmccExportWordDto.getId());
        if (user!=null && "0".equalsIgnoreCase(user.getDelFlag().toString())) {
            map.put("userName",user.getNickName());
        }


        List<MmccHealthCheck> mmccHealthChecks = baseMapper.selectList(
                Wrappers.<MmccHealthCheck>lambdaQuery()
                        .eq(MmccHealthCheck::getDelFlag, 0)
                        .eq(MmccHealthCheck::getUserId, mmccExportWordDto.getId())
                        .orderByDesc(MmccHealthCheck::getCreateTime));

        List<Map<String, Object>> moodList =new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (mmccHealthChecks!=null && mmccHealthChecks.size()>0){
            for (int i = 1; i <= mmccHealthChecks.size(); i++) {
                MmccHealthCheck mmccHealthCheck = mmccHealthChecks.get(i - 1);
                Map<String, Object> mood = new HashMap<>();
                mood.put("xh",i);
                mood.put("time",sdf.format(mmccHealthCheck.getCreateTime()));
                if ("0".equalsIgnoreCase(mmccHealthCheck.getMood())){
                    mood.put("xq","不开心");
                }else if ("1".equalsIgnoreCase(mmccHealthCheck.getMood())){
                    mood.put("xq","普通");
                }else if ("2".equalsIgnoreCase(mmccHealthCheck.getMood())){
                    mood.put("xq","开心");
                }
                moodList.add(mood);
            }
            map.put("moodList",moodList);
        }


        List<String> urls = mmccExportWordDto.getUrls();
        List<Map<String, Object>> pregnancyArchivesList =new ArrayList<>();
        if (urls!=null && urls.size()>0){
            for (String url : urls) {
                log.info("体型检测的url地址是"+url);
                Map<String, Object> pregnancyArchivesMap = new HashMap<>();
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setHeight(301);
                imageEntity.setWidth(600);
                imageEntity.setUrl(ServerConfig.plat_upload_path+"/"+url);
                pregnancyArchivesMap.put("tx",imageEntity);
                pregnancyArchivesList.add(pregnancyArchivesMap);
            }
            map.put("pregnancyArchivesList",pregnancyArchivesList);
        }



        List<Map<String, Object>> babyList =new ArrayList<>();
        List<MyDateVo> myDateVoLisst=mmccBabyUltrasoundMapper.getAllPageDay( mmccExportWordDto.getId());
        List<MmccBabyProfileVo> result = myDateVoLisst
                .stream()
                .map(item -> {
                    MmccBabyProfileVo mmccBabyProfileVo = new MmccBabyProfileVo();
                    BeanUtils.copyProperties(item, mmccBabyProfileVo);
                    List<String> collect = mmccBabyUltrasoundMapper.selectList(
                            Wrappers.<MmccBabyUltrasound>lambdaQuery()
                                    .eq(MmccBabyUltrasound::getDelFlag, 0)
                                    .eq(MmccBabyUltrasound::getUserId,  mmccExportWordDto.getId())
                                    .eq(MmccBabyUltrasound::getYear, item.getYear())
                                    .eq(MmccBabyUltrasound::getMonth, item.getMonth())
                                    .eq(MmccBabyUltrasound::getDay, item.getDay())
                                    .last("limit 4"))
                            .stream()
                            .map(vo -> {
                                String url = vo.getFileUrl();
                                return ServerConfig.plat_upload_path +"/"+ url;
                            })
                            .collect(Collectors.toList());
                    mmccBabyProfileVo.setFileUrls(collect);
                    return mmccBabyProfileVo;
                })
                .collect(Collectors.toList());
        if (result!=null && result.size()>0){
            for (int i = 0; i < result.size(); i++) {
                MmccBabyProfileVo mmccBabyProfileVo = result.get(i);
                Map<String,Object> baby=new HashMap<>();
                baby.put("time",sdf.format(mmccBabyProfileVo.getCreateTime()));
                List<String> fileUrls = mmccBabyProfileVo.getFileUrls();
                if (fileUrls!=null && fileUrls.size()>0){
                    for (int y = 0; y < fileUrls.size(); y++) {
                        String url = fileUrls.get(y);
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setHeight(200);
                        imageEntity.setWidth(100);
                        imageEntity.setUrl(url);
//                        imageEntity.setUrl("D:\\home\\excel\\222.jpeg");
                        baby.put("pic"+y,imageEntity);

                    }
                }
                babyList.add(baby);
            }
            map.put("babyList",babyList);
        }


        String path="files/docx/宝宝档案_"+System.currentTimeMillis()+".docx";
        try {
            ClassPathResource resource = new ClassPathResource("static/import/宝宝档案.docx");
            InputStream inputStream = resource.getInputStream();

            String tempPath = ServerConfig.plat_upload_path+"/" + path;
            log.info("生成地址是"+tempPath);
//            String tempPath="D:\\home\\excel\\宝宝档案_"+System.currentTimeMillis()+".docx";
            File tempFile = new File(tempPath);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            XWPFDocument doc = WordExportUtil.exportWord07(tempFile.getPath(), map);
            FileOutputStream fos = new FileOutputStream(tempPath);
//            XWPFDocument doc = WordExportUtil.exportWord07(
//                    "D:/home/excel/宝宝档案.docx", map);
//            FileOutputStream fos = new FileOutputStream("D:/home/excel/宝宝档案_"+System.currentTimeMillis()+".docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            throw new BizException("文件生成失败");
//            throw new RuntimeException(e);
        }

        return ServerConfig.plat_upload_head_path+"/"+path;
//        return null;
    }


}