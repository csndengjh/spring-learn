package com.springboot.demo.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengjianhan
 * @className RuleInterface
 * @description
 * @date 2020/11/23 15:41
 */
public interface RuleInterface {
    /**
     * 接口存放策略的map
     */
    Map<String, RuleInterface> RULE_TYPE_MAP = new HashMap<>();

    void match();

}
