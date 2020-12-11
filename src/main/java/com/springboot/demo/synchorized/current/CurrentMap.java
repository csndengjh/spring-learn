package com.springboot.demo.synchorized.current;


import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  多线程的一些方法简单使用
 * @author dengjianhan
 * @date 2019/2/19 17:02
 */
public class CurrentMap {

    @Test
    public void  demo(){
        Thread thread1 = new Thread(()-> System.out.println("hello"),"T1");
        System.out.println(thread1.getId()+"名称"+thread1.getName());
        //线程是否活动
        System.out.println(thread1.isAlive());
        //打印线程的状态
        System.out.println(thread1.getState());
        thread1.start();
        System.out.println("启动之后"+thread1.isAlive());
        System.out.println("启动之后"+thread1.getState());
        //是否是守护线程   是 true   否 false
        System.out.println(thread1.isDaemon());

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                int count = 0;
                while (!Thread.interrupted()){
                    System.out.println(name +":"+ count++);
                }
            }
        };
        Thread a  = new Thread(r);
        Thread b = new Thread(r);
        a.start();
        b.start();
   /*    while (true){
           double n = Math.random();
           if(n >= 0.4999999 && n <=0.5000001){
               break;
           }
       }*/
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.interrupt();
        b.interrupt();

    }

    /**
     * threadLocal 测试以及使用
     */
    @Test
    public void threadLocalTest()  {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("张三");
        new Thread(()-> {
            threadLocal.set("张一");
            System.out.println(Thread.currentThread().getName() + ":"+threadLocal.get());
        },"A").start();
        System.out.println(Thread.currentThread().getName() + ":"+threadLocal.get());
    }



    @Test
    public void scheduledExecutorTest(){
        // 延迟2毫秒执行
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(()->{
            System.out.println(Thread.currentThread().getName()+"   run");
        },2, TimeUnit.SECONDS);

//        scheduledExecutorService.shutdown();
    }
}
