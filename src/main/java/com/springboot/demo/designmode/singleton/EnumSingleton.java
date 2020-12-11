package com.springboot.demo.designmode.singleton;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dengjianhan
 * @className EnumSingleton
 * @description
 * @date 2020/10/2 23:19
 */
@Slf4j
@Getter
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }


}
