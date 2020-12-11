package com.springboot.demo.service;

import com.springboot.demo.inherit.Son;

import java.util.Optional;

/**
 * @ClassName: OptionalService
 * @Description:
 * @Author: dengjianhan
 * @Date: 2019/12/25 14:18
 */
public interface SonService {

    Optional<Son> getSon(Integer id);
}
