package com.springboot.demo.service.impl;

import com.springboot.demo.inherit.Son;
import com.springboot.demo.service.SonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author dengjianhan
 * @className SonServiceImpl
 * @description
 * @date 2020/5/5 23:38
 */
@Service
@Slf4j
public class SonServiceImpl implements SonService {
    @Override
    @Cacheable(value = "son",key = "methodName")
    public Optional<Son> getSon(Integer id) {
        log.info("进入方法");
        Son son = new Son();
        son.setCount(22);
        son.setMoney(33);
        son.setName("张六");
        return Optional.of(son);
    }
}
