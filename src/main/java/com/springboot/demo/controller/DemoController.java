package com.springboot.demo.controller;

import com.springboot.demo.entity.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dengjianhan
 * @date 2019/8/28 15:24
 */
@RequestMapping("/")
@Slf4j
@Controller
public class DemoController {

    @RequestMapping("/test")
    @ResponseBody
    public ResponseDto testResponse(String page){
        log.info("request 请求参数 page = [{}]",page);
        return new ResponseDto("你好");
    }

    @Value(value="classpath:/static/image/12121.jpg")
    private Resource resource;

    /**
     *  图片通过流的方式输出
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getImage")
    public void getImage(HttpServletResponse response) throws IOException {
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0L);
        BufferedImage image = ImageIO.read(new File("d:/data/12121.jpg"));
        ImageIO.write(image, "jpg",  response.getOutputStream());
    }


    /**
     *  复制文件的方法 （使用字节流）
     * @param srcPath
     * @param destPath
     * @throws IOException
     */
    public static void copyFile2(String srcPath, String destPath) throws IOException {
        // 打开输入流
        FileInputStream fis = new FileInputStream(srcPath);
        // 打开输出流
        FileOutputStream fos = new FileOutputStream(destPath);
        // 读取和写入信息
        int len = 0;
        // 创建一个字节数组，当做缓冲区
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            fos.write(b);
        }
        // 关闭流  先开后关  后开先关
        fos.close(); // 后开先关
        fis.close(); // 先开后关

    }

}
