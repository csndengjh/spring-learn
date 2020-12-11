package com.springboot.demo.controller;

import com.springboot.demo.bean.RuleInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengjianhan
 * @className MatchController
 * @description 匹配控制类(实验一种新的写法)
 * @date 2020/11/23 15:50
 */
@RestController
@RequestMapping("/")
public class MatchController {

    /**
     * 根据传进来的类型执行匹配方法
     *
     * @params [matchType]
     * @return void
     */
    @RequestMapping("/match")
    public void match(String matchType){
        RuleInterface.RULE_TYPE_MAP.get(matchType).match();
    }

}
