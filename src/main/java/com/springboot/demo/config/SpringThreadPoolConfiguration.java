package com.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *   bean 注入的方法来获取线程池
 * @author dengjianhan
 * @date 2019/8/27 21:55
 */
@Configuration
public class SpringThreadPoolConfiguration {

    AtomicInteger threadNo = new AtomicInteger(1);

    /**
     * ThreadPoolTaskExecutor  是由spring 封装的异步线程池
     * @return
     */
    @Bean("springThreadPoolExcutor")
    @Scope(value = "singleton")  // singleton 默认也是单例模式   prototype  即每个bean都会产生一次
    public ThreadPoolTaskExecutor springThreadPool(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("springThreadPool -");
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setKeepAliveSeconds(200);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(5);
        threadPoolTaskExecutor.setThreadFactory(r -> {
            Thread thread = new Thread();
            //标记为守护线程
            thread.setDaemon(false);
            thread.setName("mySpringThreadPoolTask" + threadNo);
            return thread;
        });
        threadPoolTaskExecutor.setRejectedExecutionHandler((r,excutor) -> {
            System.out.println("拒绝策略报警，spring 线程池超过最大线程数");
        });
        threadPoolTaskExecutor.setThreadNamePrefix("myThread");
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(false);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        return threadPoolTaskExecutor;
    }

    /**
     *  jdk 原生方式获取线程池
     * @return
     */
    @Bean("nativeThreadPoolExcutor")
    public ThreadPoolExecutor myThreadPool(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 0,  TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),r -> new Thread(r,"ThreadPool thread" + threadNo.getAndIncrement()),
                (r,executor) ->{ System.out.println("拒绝策略报警"); });

       return threadPoolExecutor;
    }


}
