package com.wocai.platform.modules.common.controller;

import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.aspect.annotation.NoLogin;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.FileUtils;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.common.dto.CommonBase64Dto;
import com.wocai.platform.modules.common.vo.FileRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Api(tags = "后台公共接口")
@RestController
@RequestMapping("/common")
public class FileCommonController {


    @AutoLog(value = "公共文件上传")
    @ApiOperation(value = "公共文件上传", notes = "公共文件上传")
    @PostMapping(value = "/uploadFile")
    @NoLogin
    public Result uploadFile(MultipartFile multipartFile, String module) {

        if (multipartFile == null && org.apache.commons.lang3.StringUtils.isBlank(multipartFile.getOriginalFilename())) {
            throw new BizException("请选择上传文件");
        }

        if (StringUtils.isBlank(module)) {
            module = "default";
        }

        String fileOriginalName = multipartFile.getOriginalFilename();
        String fileExtName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
        String filePath = "files/" + module + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                + "/" + StringUtils.getUUID().toUpperCase() + "/" + fileOriginalName;

        File file = new File(ServerConfig.plat_upload_path + "/" + filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            throw new BizException("文件上传失败");
        }

        String fileUrl = ServerConfig.plat_upload_head_path + "/" + filePath;
        FileRes result = new FileRes();
        result.setFileKey(filePath);
        result.setFileName(multipartFile.getOriginalFilename());
        result.setFileSize(multipartFile.getSize() / 1000);
        result.setFileUrl(fileUrl);
        return Result.ok(result);
    }

    @AutoLog(value = "多文件公共文件上传")
    @ApiOperation(value = "多文件公共文件上传", notes = "多文件公共文件上传")
    @PostMapping(value = "/uploadFiles")
    @NoLogin
    public Result uploadFiles(MultipartFile[] multipartFiles, String module) {
        List<FileRes> result=new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile == null && org.apache.commons.lang3.StringUtils.isBlank(multipartFile.getOriginalFilename())) {
                throw new BizException("请选择上传文件");
            }

            if (StringUtils.isBlank(module)) {
                module = "default";
            }

            String fileOriginalName = multipartFile.getOriginalFilename();
            String fileExtName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
            String filePath = "files/" + module + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                    + "/" + StringUtils.getUUID().toUpperCase() + "/" + fileOriginalName;

            File file = new File(ServerConfig.plat_upload_path + "/" + filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try {
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            } catch (IOException e) {
                throw new BizException("文件上传失败");
            }

            String fileUrl = ServerConfig.plat_upload_head_path + "/" + filePath;
            FileRes res = new FileRes();
            res.setFileKey(filePath);
            res.setFileName(multipartFile.getOriginalFilename());
            res.setFileSize(multipartFile.getSize() / 1000);
            res.setFileUrl(fileUrl);
            result.add(res);
        }
        return Result.ok(result);
    }

    /**
     * 公共模板下载
     * http://localhost:8080/zhwzgyl-api/common/download/template/站点导入模板.xlsx
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "下载模板", notes = "下载模板")
    @GetMapping(value = "/download/template/**")
    @NoLogin
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = extractPathFromPattern(request);
        InputStream inputStream = this.getClass().getResourceAsStream("/static/import/" + filePath);
        String fileName = "";
        if (filePath.indexOf("/") > 0) {
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        } else {
            fileName = filePath;
        }
        try {
            String finalName = URLEncoder.encode(fileName, "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment; filename=" + finalName);
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            return;
        } catch (Exception e) {
            request.setAttribute("exception", new FileNotFoundException("请求的文件不存在"));
            request.getRequestDispatcher("/error/404.jsp").forward(request, response);
        }
    }

    @ApiOperation(value = "下载文件", notes = "下载文件")
    @GetMapping(value = "/download/**")
    @NoLogin
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = extractPathFromPattern(request);
        String localPath = filePath.replace("", ServerConfig.plat_upload_path);
        File file = new File(localPath);
        InputStream inputStream = new FileInputStream(file);
        String fileName = "";
        if (filePath.indexOf("/") > 0) {
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        } else {
            fileName = filePath;
        }
        try {
            String finalName = URLEncoder.encode(fileName, "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment; filename=" + finalName);
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            return;
        } catch (Exception e) {
            request.setAttribute("exception", new FileNotFoundException("请求的文件不存在"));
            request.getRequestDispatcher("/error/404.jsp").forward(request, response);
        }
    }

    /**
     * 把指定URL后的字符串全部截断当成参数
     * 这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
     *
     * @param request
     * @return
     */
    private static String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }


    @AutoLog(value = "base64上传", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "base64上传", notes = "base64上传")
    @NoLogin
    @PostMapping(value = "/uploadBase64")
    public Result upload(@RequestBody List<CommonBase64Dto>  commonBase64Dtos) {
        List<FileRes> result=new ArrayList<>();
        if (commonBase64Dtos!=null && commonBase64Dtos.size()>0){
            for (CommonBase64Dto commonBase64Dto : commonBase64Dtos) {
                FileRes fileRes=new FileRes();
                String base64 = commonBase64Dto.getBase64();
                BASE64Decoder decoder = new BASE64Decoder();

                int begin = base64.indexOf("/");
                int end = base64.indexOf(";");
                String suffix=base64.substring(begin+1,end);

                byte[] imageByte = new byte[0];

                base64 = base64.substring(base64.indexOf(",") + 1);


                try {
                    imageByte = decoder.decodeBuffer(base64);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String filePath = "files/default/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                        + "/" +  System.currentTimeMillis() + "."+suffix;
                String path = ServerConfig.plat_upload_path+ File.separator+filePath;
                //图片类型
                saveImage(imageByte, path);
//                path=path.substring(path.indexOf("files"));

                fileRes.setFileKey(filePath);
                fileRes.setFileUrl(ServerConfig.plat_upload_head_path+"/"+filePath);
                result.add(fileRes);
            }
        }


        return  Result.ok(result);

    }

    public static void saveImage(byte[] imageByte, String path) {
        InputStream input = null;

        try {
            //转化成流
            input = new ByteArrayInputStream(imageByte);
            BufferedImage bi = ImageIO.read(input);
            File file = new File(path);
//            File file = org.apache.commons.io.FileUtils.getFile(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            ImageIO.write(bi, "png", file);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
