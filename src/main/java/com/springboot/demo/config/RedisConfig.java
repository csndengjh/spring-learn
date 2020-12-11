package com.springboot.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dengjianhan
 * @className RedisConfig
 * @description  redis 控制中心
 * @date 2020/5/5 16:34
// */
//@Configuration
public class RedisConfig {

    /**
     * 配置redis序列化
     * @return
     */
    @Bean(name = "redisTemplate")
    @SuppressWarnings("unchecked")
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        // Jackson2JsonRedisSerializer  序列化器,存到redis 会记录类信息,返回的数据可直接强转成对象
        // GenericJackson2JsonRedisSerializer  会在json中加入@class属性，类的全路径包名，方便反系列化
        // 设置序列化json
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // json转对象类，不设置默认的会将json转成hashmap
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /*********    整合jedis时客户端使用  *************/


    @Bean
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public JedisPoolConfig getRedisConf() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return jedisPoolConfig;
    }


    @Bean
    public JedisPool jedisPool()  throws Exception{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost");
        return jedisPool;
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setUsePool(true);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }


    @Bean(name = "jedisRedisTemplate")
    public RedisTemplate<?, ?> getRedisTemplate() {
        JedisConnectionFactory factory = getConnectionFactory();
        RedisTemplate<?, ?> template = new StringRedisTemplate(factory);
        return template;
    }

}
