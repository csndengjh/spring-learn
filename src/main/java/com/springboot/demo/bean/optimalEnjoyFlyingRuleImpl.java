package com.springboot.demo.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengjianhan
 * @className 优享飞策略的变种写法
 * @description
 * @date 2020/11/23 15:42
 */
@Configuration
public class optimalEnjoyFlyingRuleImpl implements RuleInterface, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        RULE_TYPE_MAP.put("optimalEnjoyFlying", this);
    }

    @Override
    public void match() {
        System.out.println("优享飞策略匹配处理");
    }

}
