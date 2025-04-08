package com.wocai.platform.common.util;

/**
 * @ClassName: StreamGobbler
 * @Author: wzw
 * @Description:
 * @Date: 2021/9/6 2:02 下午
 * @Version: 1.0
 */

import lombok.extern.slf4j.Slf4j;

import java.io.*;


/**
 * 用于处理Runtime.getRuntime().exec产生的错误流及输出流
 *
 * @author shaojing
 */
@Slf4j
public class StreamGobbler extends Thread {
    InputStream is;
    String type;
    OutputStream os;

    public StreamGobbler(InputStream is, String type) {
        this(is, type, null);
    }

    StreamGobbler(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }

    public void run() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            if (os != null)
                pw = new PrintWriter(os);

            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                //过滤无效信息
                if (line.contains("frame") && line.contains("fps") && line.contains("size")) {

                } else if (line.contains("parsing AU headers") || line.contains("Last message repeated")) {

                } else if (line.contains("Non-monotonous DTS")) {

                } else {
                    log.info(type + ">" + line);
                }
            }

            if (pw != null)
                pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                isr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

