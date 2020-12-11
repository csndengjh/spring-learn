package com.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName: Constant
 * @Description: 静态获取 配置文件的值
 * @Author: dengjianhan
 * @Date: 2019/11/7 20:29
 */
//@Component
public class Constant {
    private static String integraionUrl;

    @Value("${spring.redis.host}")
    public void setIntegrationUrl(String param) {
        Constant.integraionUrl= param;
    }
}
