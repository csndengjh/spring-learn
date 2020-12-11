package com.springboot.demo.expection;

/**
 * @author dengjianhan
 * @className NhException
 * @description
 * @date 2020/9/12 15:14
 */
public class NhException extends RuntimeException {
    public NhException() {
        super();
    }

    public NhException(String message) {
        super(message);
    }

    public NhException(String message, Throwable cause) {
        super(message, cause);
    }

    public NhException(Throwable cause) {
        super(cause);
    }

}
