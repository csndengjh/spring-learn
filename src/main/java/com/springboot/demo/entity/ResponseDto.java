package com.springboot.demo.entity;


/**
 * 请求响应基础类
 *
 * @author laogaochuang
 */
public class ResponseDto<T> {
    /**
     * 响应码
     */
    private int code;
    /**
     * 相应描述
     */
    private String mes;
    /**
     * 请求成功，响应数据
     */
    private T data;

    public ResponseDto() {
        this.code = 200;
        this.mes = "sucess";
    }

    public ResponseDto(T data) {
        this.code = 200;
        this.mes = "sucess";
        this.data = data;
    }

    public ResponseDto(int code, String mes) {
        this.code = code;
        this.mes = mes;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDto{" + "code=" + code + ", mes='" + mes + '\'' + ", data=" + data + '}';
    }
}
