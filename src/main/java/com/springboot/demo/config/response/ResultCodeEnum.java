package com.springboot.demo.config.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dengjianhan
 * @className ResultCodeEnum
 * @description 返回结果编码枚举值
 * @date 2020/12/9 9:53
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    // 公共异常定义
    SUCCESS(200, "操作成功"),
    PARAM_INVALID(400, "非法参数，请核对"),
    UN_LOGIN(403, "您还没有登陆，请先登陆!"),
    NOT_FOUND(404, "找不到请求路径"),
    SERVER_ERROR(500, "服务器异常");

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 提示语
     */
    private String message;



}
