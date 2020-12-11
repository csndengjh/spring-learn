package com.springboot.demo.designmode.singleton;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dengjianhan
 * @className Container
 * @description  容器式单例  适用于创建实例非常多的情况，便于管理。但是，是非线程安全的。
 * @date 2020/10/2 23:12
 */
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String,Object> ioc = new ConcurrentHashMap();


    public static  Object getInstance(String className){
//        synchronized (ioc) {
            if (!Objects.equals(className, ioc.get(className))) {
                Object object = null;
                try {
                    object = Class.forName(className).newInstance();
                    ioc.put(className, object);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                return object;
            } else {
                return ioc.get(className);
            }
//        }
    }



    public static class ExectorThread implements Runnable {
        public void run() {
            Object singleton =  ContainerSingleton.getInstance("com.springboot.demo.entity.Son");
            System.out.println(Thread.currentThread().getName()+":"+singleton);
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
    }
}
