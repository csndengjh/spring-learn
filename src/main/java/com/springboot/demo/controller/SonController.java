package com.springboot.demo.controller;

import com.springboot.demo.inherit.Son;
import com.springboot.demo.service.SonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author dengjianhan
 * @className CacheController
 * @description
 * @date 2020/5/5 23:37
 */
@RestController
@RequestMapping("/")
public class SonController {



    @Autowired
    private SonService sonService;

    @RequestMapping("/getSon")
    public Optional<Son> getSon(Integer id){
        return sonService.getSon(id);
    }


    @RequestMapping("/updateSon")
    public Son updateSon(@RequestBody Son son){
        System.out.println("updateSon 方法");
        return new Son(22,10, "张三");
    }


}
