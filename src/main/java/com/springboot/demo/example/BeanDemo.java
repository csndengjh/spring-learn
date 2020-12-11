package com.springboot.demo.example;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 在有指针的情况下，浅拷贝只是增加了一个指针指向已经存在的内存，
 * 而深拷贝就是增加一个指针并且申请一个新的内存，使这个增加的指针指向这个新的内存，
 * 采用深拷贝的情况下，释放内存的时候就不会出现在浅拷贝时重复释放同一内存的错误！
 */
@Slf4j
public class BeanDemo {
    @Data
    class User{
        private String name;
        /**
         *   @ ToString.Exclude 排除指定字段不会被 toString 打印
         *   @ ToString.Include（rank = -1）更改成员的打印顺序。  等级越高优先打印
         */
        @ToString.Exclude
        private Integer age;

        private  Integer [] score;

        public User() {
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public User(String name, Integer age, Integer[] score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }
    }

    /**
     *  org.springframework.beans.BeanUtils.copyProperties(Object source, Object target)
     *  方法拷贝，拷贝方案是浅拷贝
     */
    @Test
    public void beanCopy(){
        Integer [] score = {11,22,33};
        User user = new User("张三",22,score);
        User user2 = new User();
        BeanUtils.copyProperties(user,user2);
        System.out.println("----修改前------");
        System.out.println(user2);
        user.setName("李四");
        user.getScore()[0] = 99;
        System.out.println("----修改后------");
        System.out.println(user2);
        log.info("user = {}",user);
        System.out.println("user : "+user);
    }

    @Test
    public void mapTest(){
        HashMap<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        Set<Map.Entry<String,String>> entrySet =  map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() +":" + next.getValue());
        }
        map.forEach((key,value)->
            System.out.println(key + "-"+value)
        );
        if (map != null) {
            System.out.println(map);
        }

    }

    @Test
    public void userTest(){
        Integer [] scores = {97,96};
        User user = new User("张三",22,scores);
        System.out.println("user : "+user);
    }
}
