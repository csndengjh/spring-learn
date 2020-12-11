package com.springboot.demo.config.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dengjianhan
 * @className Result
 * @description 返回结果封装类
 * @date 2020/12/4 15:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 6985225433048454111L;

    private Integer code;
    /**
     * 前端提示语
     */
    private String message;
    /**
     * 后台的异常处理信息
     */
    private String backGroundMessage;

    private Object data;


    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.backGroundMessage = "";
    }

    public Result(ResultCodeEnum resultCodeEnum, Object data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.backGroundMessage = "";
        this.data = data;
    }



    public static <D> Result success(D data){
        return new Result(ResultCodeEnum.SUCCESS, data);
    }





}
