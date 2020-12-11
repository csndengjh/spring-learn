package com.springboot.demo.demo;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dengjianhan
 * @date 2019/8/1 17:12
 */
public class GetImageDemo {

    public static String GetImageStr(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args) {
        String sss = GetImageStr("D:\\data\\12121.jpg");
        System.out.println(sss);
    }
}
