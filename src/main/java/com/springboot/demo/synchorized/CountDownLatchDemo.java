package com.springboot.demo.synchorized;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author dengjianhan
 * @className CountDownLunchDemo
 * @description
 * @date 2020/7/31 21:49
 */
@Slf4j
public class CountDownLatchDemo {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask(new Callable() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                log.info(Thread.currentThread().getName() + ": call 方法执行");
                return 33;
            }
        });
        Thread thread1 = new Thread(task, "thread1");
        thread1.start();


        FutureTask<Integer> task2 = new FutureTask(new Callable() {
            @Override
            public Integer call() throws Exception {
                log.info(Thread.currentThread().getName() + ": call 方法执行");
                return 22;
            }
        });
        Thread thread2 = new Thread(task2, "thread2");
        thread2.start();

        Integer number1 = task.get();
        Integer number2 = task2.get();
        log.info("线程1返回值 + 线程2返回值 = {}", number1 + number2);
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                countDownLatch.countDown();
                log.info(Thread.currentThread().getName() + ": call1 方法执行");
                Thread.sleep(2000);
                return 22;
            }
        };

        Callable<Integer> callable2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                countDownLatch.countDown();
                log.info(Thread.currentThread().getName() + ": call2 方法执行");
                Thread.sleep(2000);
                return 33;
            }
        };

        Future<Integer> submit = executorService.submit(callable);
        Future<Integer> submit2 = executorService.submit(callable2);

        countDownLatch.await();

        log.info("线程1返回值 + 线程2返回值 = {}", submit.get() + submit2.get());

        executorService.shutdown();


    }

}
