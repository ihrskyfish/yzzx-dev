package com.wocai.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.system.dto.SysDatabaseBackReq;
import com.wocai.platform.modules.system.entity.SysDatabaseBack;
import com.wocai.platform.modules.system.mapper.SysDatabaseBackMapper;
import com.wocai.platform.modules.system.service.ISysDatabaseBackService;
import com.wocai.platform.modules.system.vo.SysDatabaseBackRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数据备份
 * @Author: houws
 * @Date: 2022-07-04
 * @Version: V1.0
 */
@Service
@Slf4j
public class SysDatabaseBackServiceImpl extends BaseServiceImpl<SysDatabaseBackMapper, SysDatabaseBack> implements ISysDatabaseBackService {

    @Value("${mysql.environment}")
    private String environment;

    @Value("${mysql.path}")
    private String mysqlPath;

    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String username;

    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;

    @Override
    public IPage<SysDatabaseBackRes> queryPageList(IPage<SysDatabaseBackRes> page, SysDatabaseBackReq sysDatabaseBackReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotBlank(sysDatabaseBackReq.getBackName())) {
            reqParam.put("backName", sysDatabaseBackReq.getBackName());
        }
        IPage<SysDatabaseBackRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(SysDatabaseBackReq sysDatabaseBackReq) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_flag", "0");
        queryWrapper.eq("back_name", sysDatabaseBackReq.getBackName());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BizException("备份文件名称重复");
        }
        return false;
    }

    @Override
    public void saveMain(SysDatabaseBackReq sysDatabaseBackReq) {
        String backName = sysDatabaseBackReq.getBackName();
        String database = getDatabaseByUrl(jdbcUrl);
        String host = getHostByUrl(jdbcUrl);
        if (StringUtils.isBlank(backName)) {
            backName = database + "_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".sql";
            sysDatabaseBackReq.setBackName(backName);
        }
        SysDatabaseBack sysDatabaseBack = new SysDatabaseBack();
        BeanUtils.copyProperties(sysDatabaseBackReq, sysDatabaseBack);
        this.doDuplicateCheck(sysDatabaseBackReq);
        String backPath = "/home/data/backup/mysql/" + database + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + backName;
        File file = new File(backPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if ("windows".equalsIgnoreCase(environment)) {
            backupDatabaseByWindows(database, host, backPath);
        } else if ("linux".equalsIgnoreCase(environment)) {
            backupDatabaseByLinux(database, host, backPath);
        } else if ("docker".equalsIgnoreCase(environment)) {
            backupDatabaseByDocker(database, host, backPath);
        } else {
            throw new BizException("系统环境配置错误");
        }
        sysDatabaseBack.setBackPath(backPath);
        this.save(sysDatabaseBack);
    }

    public String getDatabaseByUrl(String jdbcUrl) {
        String database = jdbcUrl.replace("jdbc:mysql://", "");
        database = database.substring(database.indexOf("/") + 1, database.indexOf("?"));
        return database;
    }

    public String getHostByUrl(String jdbcUrl) {
        String host = jdbcUrl.replace("jdbc:mysql://", "");
        host = host.substring(0, host.indexOf(":"));
        return host;
    }

    public String backupDatabaseByWindows(String database, String host, String backPath) {
        //mysqldump -h127.0.0.1 -uroot -proot test --default-character-set=utf8 --skip-extended-insert -r D:\filePath\db/test.sql
        String command = mysqlPath + "/mysqldump -h" + host + " -u" + username + " -p" + password + " " + database + " --default-character-set=utf8 --skip-extended-insert -r " + backPath;
        try {
            Runtime rt = Runtime.getRuntime();
            log.info("cmd命令为：" + command);
            Process proc = rt.exec(command);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("备份成功");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return backPath;
    }

    public String backupDatabaseByLinux(String database, String host, String backPath) {
        //./mysqldump -h127.0.0.1 -uroot -proot test --default-character-set=utf8 --skip-extended-insert > /opt/db/test.sql
        String command = mysqlPath + "/mysqldump -h" + host + " -u" + username + " -p" + password + " " + database + " --default-character-set=utf8 --skip-extended-insert > " + backPath;
        Process process = null;
        try {
            log.info("cmd命令为：" + command);
            //执行命令
            String[] cmd = new String[]{"/bin/sh", "-c", command};
            process = Runtime.getRuntime().exec(cmd);
            InputStreamReader ips = new InputStreamReader(process.getInputStream());
            BufferedReader br = new BufferedReader(ips);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return backPath;
    }

    public String backupDatabaseByDocker(String database, String host, String backPath) {
        //docker命令 docker exec mysql mysqldump -uroot -p123456 paas_portal > /cloud/sql/paas_portal.sql
        StringBuffer buffer = new StringBuffer();
        buffer.append("docker exec ");
        buffer.append(" mysql ");//docker mysql name;
        buffer.append(" mysqldump ");
        buffer.append(" -u " + username);
        buffer.append(" -p" + password);
        buffer.append(" --default-character-set=utf8 --skip-extended-insert ");
        buffer.append(" " + database + " >");
        buffer.append(backPath);

        log.info("cmd命令为：" + buffer.toString());
        log.info("开始备份：" + database);
//        Runtime runtime = Runtime.getRuntime();
        //Process process = runtime.exec("cmd /c"+buffer.toString());
        Process process = linuxEnv(buffer.toString());
        int exitVal = process.exitValue();
        log.info("数据库备份命令退出码：" + exitVal);
        log.info("备份成功!");
        return backPath;
    }

    public static Process linuxEnv(String command) {
        Process ps = null;
        try {
            String[] commands = {"/bin/sh", "-c", command};
            ps = Runtime.getRuntime().exec(commands);
            String line;
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer out = new StringBuffer();
            while ((line = stdoutReader.readLine()) != null) {
                out.append(line);
            }
            ps.waitFor();
            ps.destroy();
            log.info("命令执行完成" + out.toString());
        } catch (Exception ex) {
            log.info("命令执行异常");
            ex.printStackTrace();
        }
        return ps;
    }


    @Override
    public void updateMain(SysDatabaseBackReq sysDatabaseBackReq) {
        SysDatabaseBack sysDatabaseBack = this.getById(sysDatabaseBackReq.getId());
        if (sysDatabaseBack == null || CommonConstant.DEL_FLAG_1.equals(sysDatabaseBack.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(sysDatabaseBackReq);
        BeanUtils.copyProperties(sysDatabaseBackReq, sysDatabaseBack);
        this.updateById(sysDatabaseBack);
    }

    @Override
    public SysDatabaseBackRes getMainById(String id) {
        SysDatabaseBackRes result = new SysDatabaseBackRes();
        SysDatabaseBack sysDatabaseBack = this.getById(id);
        if (sysDatabaseBack == null || CommonConstant.DEL_FLAG_1.equals(sysDatabaseBack.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(sysDatabaseBack, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            SysDatabaseBack sysDatabaseBack = this.getById(id);
            if (sysDatabaseBack == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<SysDatabaseBack> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDatabaseBack::getId, idList);
        SysDatabaseBack sysDatabaseBack = new SysDatabaseBack();
        sysDatabaseBack.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(sysDatabaseBack, queryWrapper);
    }


}