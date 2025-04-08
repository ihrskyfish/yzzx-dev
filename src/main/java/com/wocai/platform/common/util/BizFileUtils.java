package com.wocai.platform.common.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author admin
 */
public class BizFileUtils {
    /**
     * 将MultipartFile转换为zipFile，用断言过滤
     *
     * @param inFile
     */
    public static  ZipFile checkZip(MultipartFile inFile) {
        InputStream fileStream = null;
        File file = null;
        Stream<? extends ZipEntry> signStream = null;
        Stream<? extends ZipEntry> zipStream = null;
        ZipFile zipFile = null;
        //临时文件
        Path path = Paths.get(System.getProperty("java.io.tmpdir"), inFile.getName() + UUID.randomUUID() + "zip");
        file = path.toFile();
        try {
            fileStream = inFile.getInputStream();
            org.apache.commons.io.FileUtils.copyInputStreamToFile(fileStream, file);
            zipFile = new ZipFile(file, Charset.defaultCharset());
            signStream = zipFile.stream();
            zipStream = zipFile.stream();
            //断言
            Predicate<ZipEntry> signTxt = ze -> ze.getName().contains("sign,txt");
            Predicate<ZipEntry> zipTxt = ze -> ze.getName().endsWith(".zip");
            //，过滤
            Optional<ZipEntry> signInfo = (Optional<ZipEntry>) signStream.filter(signTxt).findFirst();
            Optional<ZipEntry> zipInfo = (Optional<ZipEntry>) zipStream.filter(zipTxt).findFirst();
            if (signInfo.isPresent() && zipInfo.isPresent()) {
                return zipFile;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流

                fileStream.close();
                signStream.close();
                zipStream.close();
                zipFile.close();
                //删除临时文件
                org.apache.commons.io.FileUtils.deleteQuietly(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static File MultipartFileToFile(MultipartFile multipartFile){
        //文件上传前的名称
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        OutputStream out = null;
        try{
            //获取文件流，以文件流的方式输出到新文件
//    InputStream in = multipartFile.getInputStream();
            out = new FileOutputStream(file);
            byte[] ss = multipartFile.getBytes();
            for(int i = 0; i < ss.length; i++){
                out.write(ss[i]);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
