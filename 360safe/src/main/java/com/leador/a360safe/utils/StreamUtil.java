package com.leador.a360safe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuwei on 2016/12/25.
 */
public class StreamUtil {
    /**
     * 流转换成字符串
     * @param is
     */
    public static String stream2String(InputStream is) {
        //1.在读取的过程中将读取的内容存储在缓存中，然后一次性转换成字符串返回
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //2.每次读取1024字节
        byte[] buffer = new byte[1024];
        //3.记录读取内容的临时变量
        int temp = -1;
        try {
            while ((temp = is.read(buffer)) != -1) { //循环读取
                bos.write(buffer,0,temp); //将读取的内容写入
            }
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                is.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
