package com.wocai.platform.common.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;


/**
 * @Description: TODO
 * @Author: linwenqiang
 * @Date: 2021-11-01 23:21
 * @Version: V1.0
 */
public class ZipUtils {

    public static boolean unZipFiles(String zipFileName, String descFileName) throws IOException {
        return unZipFiles((new ZipFile(zipFileName, "GBK")), descFileName);
    }

    public static boolean unZipFiles(ZipFile zipFile, String descFileName) {
        String descFileNames = descFileName;
        if (!descFileName.endsWith(File.separator)) {
            descFileNames = descFileName + File.separator;
        }
        try {
            ZipEntry entry = null;
            String entryName = null;
            String descFileDir = null;
            byte[] buf = new byte[4096];
            int readByte = 0;
            Enumeration enums = zipFile.getEntries();
            while (true) {
                while (enums.hasMoreElements()) {
                    entry = (ZipEntry) enums.nextElement();
                    entryName = entry.getName();
                    descFileDir = descFileNames + entryName;
                    if (entry.isDirectory()) {
                        (new File(descFileDir)).mkdirs();
                    } else {
                        (new File(descFileDir)).getParentFile().mkdirs();
                        File file = new File(descFileDir);
                        OutputStream os = new FileOutputStream(file);
                        InputStream is = zipFile.getInputStream(entry);

                        while ((readByte = is.read(buf)) != -1) {
                            os.write(buf, 0, readByte);
                        }

                        os.close();
                        is.close();
                    }
                }

                zipFile.close();
                System.out.println("文件解压成功!");
                return true;
            }
        } catch (Exception var13) {
            System.out.println("文件解压失败：" + var13.getMessage());
            return false;
        }
    }

}
