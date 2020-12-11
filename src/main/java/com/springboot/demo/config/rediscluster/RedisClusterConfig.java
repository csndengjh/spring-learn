package com.springboot.demo.config.rediscluster;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dengjianhan
 * @className RedisClusterConfig
 * @description redis 集群配合
 * @date 2020/12/10 9:53
 */
@Configuration
public class RedisClusterConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${redis.password}")
    private String password;

    @Value("${spring.redis.cluster.max-redirects}")
    private int maxRedirects;

    @Value("${spring.redis.timeout}")
    private int timeout;


    // 用 JedisCluster 连接实例 调用方式
  /*  @Bean
    public JedisCluster getJedisCluster() {
        //获取redis集群的ip及端口号等相关信息；
        String[] serverArray = redisProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        //遍历add到HostAndPort中；
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        //构建对象并返回；
        JedisCluster jedisCluster = new JedisCluster(nodes, redisProperties.getCommandTimeout());
        return jedisCluster;
    }*/


  /*  @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }*/


    @Bean
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return jedisPoolConfig;
    }

    /**
     * Redis集群的配置
     *
     * @return RedisClusterConfiguration
     * @throws
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        //Set<RedisNode> clusterNodes
        String[] serverArray = clusterNodes.split(",");
        Set<RedisNode> nodes = new HashSet<RedisNode>();
        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }
        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);
        redisClusterConfiguration.setPassword(RedisPassword.of(password));
        return redisClusterConfiguration;

    }

    /**
     * redis连接工厂类
     *
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        //集群模式
        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration(), getJedisPoolConfig());
        factory.setDatabase(0);
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        return factory;
    }


    /**
     * 配置redis序列化
     * @return
     */
    @Bean(name = "redisTemplate")
    @SuppressWarnings("unchecked")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
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


}