package com.springboot.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author dengjianhan
 * @className FileDemo
 * @description  Files 工具类的demo
 * @date 2020/9/8 15:29
 */
@Slf4j
public class FileDemo {

    @Test
    public void test() throws IOException {
        Path path = Paths.get("d:", "BaiduNetdiskDownload");
        boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        log.info("{}", exists);
        // 判断是不是目录
        boolean directory = Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS);
        log.info("{}", directory);
        // 路径下文件的大小
        log.info("{}",Files.size(path));

        // 复制
        Path path2 = Paths.get("d:", "BaiduNetdiskDownload/008.jpg");
//        FileOutputStream os = new FileOutputStream("d:/BaiduNetdiskDownload/009.jpg");
//        Files.copy(path2,os);

        // 移动
        Path path3 = Paths.get("d:", "008.jpg");
        Files.move(path2,path3);





    }
}
