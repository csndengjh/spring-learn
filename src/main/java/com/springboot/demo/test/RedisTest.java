package com.springboot.demo.test;

import com.springboot.demo.config.RedisConfig;
import com.springboot.demo.entity.Son;
import com.springboot.demo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengjianhan
 * @className RedisTest
 * @description
 * @date 2020/5/5 16:45
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisConfig.class)
//@ContextConfiguration(classes = {RedisConfig.class})
public class RedisTest {

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void listTest(){
//        List<String> list = new ArrayList<>();
//        list.add("张三");
//        list.add("张四");
//        list.add("张五");
//        list.forEach( str -> redisTemplate.opsForList().leftPush("mylist",str));
//        log.info("mylist ->{}",redisTemplate.opsForList().range("mylist",0,-1));
        jedisPool.getResource().set("张三","张三");
        log.info("GET ->{}", jedisPool.getResource().get("张三"));

    }


    @Test
    public void listTest2(){
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setName("张三");
        student.setAge("22");
        student.setSex("男");
        list.add(student);
        list.forEach( str -> redisTemplate.opsForList().leftPush("newList", str));
        log.info("mylist ->{}",redisTemplate.opsForList().range("newList",0,-1));
        List<Student> newList = redisTemplate.opsForList().range("newList", 0, -1);
        newList.forEach( student1 -> log.info("student = {}",student1));

    }


    @Test
    public void listTest3(){
        List<Son> list = new ArrayList<>();
        Son student = new Son();
        student.setName("张三");
        student.setAge("22");
        student.setSex("男");
        list.add(student);
        list.forEach( str -> redisTemplate.opsForList().leftPush("newList", str));
        log.info("mylist ->{}",redisTemplate.opsForList().range("newList",0,-1));
    }

    @Test
    public void mapTest(){
        Student student = new Student();
        student.setName("张三");
        student.setAge("22");
        student.setSex("男");
        redisTemplate.opsForHash().put("myMap","student1",student);
        redisTemplate.opsForHash().put("myMap","num",2);
        log.info("myMap ->{}",redisTemplate.opsForHash().get("myMap","student1"));
        log.info("myMap ->{}",redisTemplate.opsForHash().get("myMap","num"));
    }
}
