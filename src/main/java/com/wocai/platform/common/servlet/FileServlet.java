package com.wocai.platform.common.servlet;


import com.wocai.platform.common.util.Encodes;
import com.wocai.platform.common.util.FileUtils;
import com.wocai.platform.common.util.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@WebServlet(name = "fileServlet", urlPatterns = FileServlet.FILE_BASE_URL + "*")
public class FileServlet extends HttpServlet {

    public final static String FILE_BASE_URL = "/files/";

    //将指定文件转为流响应到客户端
    public void fileOutputStream(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String filepath = req.getRequestURI();
        int index = filepath.indexOf(FILE_BASE_URL);
        if (index >= 0) {
            filepath = filepath.substring(index + FILE_BASE_URL.length());
        }
        try {
            filepath = UriUtils.decode(filepath, "UTF-8");
        } catch (Exception e1) {
            log.error(String.format("解释文件路径失败，URL地址为%s", filepath), e1);
        }
        String fileExtName = FileUtils.getFileExtendName(filepath);
        File file = new File(ServerConfig.plat_upload_path + FILE_BASE_URL + filepath);
        try {
            resp.setCharacterEncoding("UTF-8");
            if ("jpg".equalsIgnoreCase(fileExtName) || "jpeg".equalsIgnoreCase(fileExtName)) {
                resp.setHeader("Content-Type", "image/jpeg");
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } else if ("png".equalsIgnoreCase(fileExtName)) {
                resp.setHeader("Content-Type", "image/png");
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } else if ("mp4".equalsIgnoreCase(fileExtName)) {
                resp.setHeader("Content-Type", "video/mp4");
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } else if ("pdf".equalsIgnoreCase(fileExtName)) {
                resp.setHeader("Content-Type", "application/pdf");
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } else if ("doc".equalsIgnoreCase(fileExtName) || "docx".equalsIgnoreCase(fileExtName)) {
//                resp.setHeader("Content-Type", "application/msword");
                resp.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } else if ("xls".equalsIgnoreCase(fileExtName) || "xlsx".equalsIgnoreCase(fileExtName)) {
//                resp.setHeader("Content-Type", "application/vnd.ms-excel");
                resp.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } else if ("ppt".equalsIgnoreCase(fileExtName) || "pptx".equalsIgnoreCase(fileExtName)) {
                resp.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            } else {
                // 清空response
                resp.reset();
                resp.setCharacterEncoding("UTF-8");
                // 设置response的Header
                resp.setHeader("Content-Type", "application/octet-stream");
                resp.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(file.getName()));
                FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            }
            return;
        } catch (FileNotFoundException e) {
            req.setAttribute("exception", new FileNotFoundException("请求的文件不存在"));
            req.getRequestDispatcher("/error/404.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fileOutputStream(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fileOutputStream(req, resp);
    }
}
