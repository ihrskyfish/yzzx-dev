package com.wocai.platform.minio.test;

import com.alibaba.fastjson.JSONObject;
import com.wocai.platform.minio.utils.MinIoUtils;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "minio测试")
@RestController
@RequestMapping("/minio/test")
public class TestController {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinIoUtils minIoUtils;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "测试文件上传", notes = "测试文件上传")
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().object(file.getOriginalFilename())
                    .bucket("test").contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1).build();
            minioClient.putObject(objectArgs);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 下载文件
     *
     * @param filename
     */
    @ApiOperation(value = "测试文件下载", notes = "测试文件下载")
    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse res) {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket("test").object(filename).build();
        try (GetObjectResponse response = minioClient.getObject(objectArgs)) {
            byte[] buf = new byte[1024];
            int len;
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
                while ((len = response.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.flush();
                byte[] bytes = os.toByteArray();
                res.setCharacterEncoding("utf-8");
                res.setContentType("application/force-download");// 设置强制下载不打开
                res.addHeader("Content-Disposition", "attachment;fileName=" + filename);
                try (ServletOutputStream stream = res.getOutputStream()) {
                    stream.write(bytes);
                    stream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @ApiOperation(value = "获取临时上传URL", notes = "获取临时上传URL")
    @PostMapping("/getStsUrl")
    public JSONObject getStsUrl(@Param("key") String key) {
        String url = minIoUtils.getObjectUrl("test", key);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        return jsonObject;
    }

}
