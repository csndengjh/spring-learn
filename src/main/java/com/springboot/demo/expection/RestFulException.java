package com.springboot.demo.expection;

import lombok.Data;

/**
 * @author dengjianhan
 * @className RestFulException
 * @description restFul 业务异常类
 * @date 2020/12/4 15:41
 */
@Data
public class RestFulException extends RuntimeException{

    public RestFulException(String message) {
        super(message);
    }


}
