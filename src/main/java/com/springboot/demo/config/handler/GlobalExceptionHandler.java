package com.springboot.demo.config.handler;

import com.springboot.demo.config.response.Result;
import com.springboot.demo.config.response.ResultCodeEnum;
import com.springboot.demo.expection.RestFulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dengjianhan
 * @className GlobalExceptionHandler
 * @description 统一异常处理
 * @date 2020/12/4 15:32
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        log.error(" GlobalExceptionHandler 统一异常捕获{}", e);
        return new Result(ResultCodeEnum.SERVER_ERROR.getCode(),ResultCodeEnum.SERVER_ERROR.getMessage(),e.getMessage(), null);
    }


    @ExceptionHandler(RestFulException.class)
    @ResponseBody
    public Result restFulExceptionHandler(Exception e) {
        log.error(" GlobalExceptionHandler 统一异常捕获 restFulException业务异常{}", e);
        return new Result(ResultCodeEnum.SERVER_ERROR.getCode(),e.getMessage(),"", null);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String defaultMessage = fieldError.getDefaultMessage();
            return new Result(ResultCodeEnum.PARAM_INVALID.getCode(),defaultMessage,"", null);
        }
        return null;
    }



}

