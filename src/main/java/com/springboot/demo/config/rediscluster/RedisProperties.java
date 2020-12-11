package com.springboot.demo.config.rediscluster;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dengjianhan
 * @className RedisProperties
 * @description redis 集群配置
 * @date 2020/12/10 9:54
 */
//依赖注入
@Component
//该注解用于读取配置文件中的属性，其中prefix表示前缀；
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class RedisProperties {
    private int expireSeconds;
    private String nodes;
    private String password;
    private int commandTimeout;
}
