package com.springboot.demo.synchorized.current;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengjianhan
 * @date 2019/3/26 11:07
 */
@Slf4j
public class ConcurrentMapTest {

    private final Map<String, Long> urlCounter = new ConcurrentHashMap<>();

    //接口调用次数+1
    public long increase(String url) {

        Long oldValue = urlCounter.get(url);

        Long newValue = (oldValue == null) ? 1L : oldValue + 1;

        urlCounter.put(url, newValue);

        return newValue;

    }


    //获取调用次数

    public Long getCount(String url) {
        return urlCounter.get(url);
    }

    @Test
    public void countDownLatchTest(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,20,10L,TimeUnit.SECONDS,new ArrayBlockingQueue<>(33),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //  Executors.newFixedThreadPool()  产生固定数量的线程池，其任务队列长度最大为 Interger.MAX_VALUE  可能会造成内存泄漏
        //  Executors.newCachedThreadPool()   允许创建的最大线程数Integer.MAX_VALUE ,大量创建线程时 容易会造成内存泄漏
        final ConcurrentMapTest counterDemo = new ConcurrentMapTest();
        int callTime = 100;
        final String url = "http://localhost:8080/hello";
        CountDownLatch countDownLatch = new CountDownLatch(callTime);
        //模拟并发情况下的接口调用统计
        for (int i = 0; i < callTime; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    counterDemo.increase(url);
                    countDownLatch.countDown();
                }
            });

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPoolExecutor.shutdown();
        //等待所有线程统计完成后输出调用次数
        System.out.println("调用次数：" + counterDemo.getCount(url));

    }

    @Test
    public void threadPoolExecutorTest() throws InterruptedException, ExecutionException {
        AtomicInteger threadNo = new AtomicInteger(1);
        ThreadPoolExecutor threadPoolExecutor = new
                ThreadPoolExecutor(5,10,0L,TimeUnit.SECONDS,new LinkedBlockingDeque<>(5),
                r -> new Thread(r,"ThreadPoll Thread" +threadNo.getAndIncrement()),(r,executor) -> System.out.println("拒绝策略报警"));

        CountDownLatch countDownLatch = new CountDownLatch(16);
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Future<Integer> future = threadPoolExecutor.submit(() ->{
                try {
                    Thread.sleep(200L);
                    Random random = new Random();
                    System.out.println(Thread.currentThread().getName() + " run");
                    int i1 = random.nextInt();
                    return i1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            });
            futureList.add(future);
            countDownLatch.countDown();
            System.out.println("核心线程数" + threadPoolExecutor.getCorePoolSize());
            System.out.println("线程池数" + threadPoolExecutor.getPoolSize());
            System.out.println("队列任务数" + threadPoolExecutor.getQueue().size());
        }
        countDownLatch.await();
        for (Future<Integer> future : futureList) {
            System.out.println(future.get());
        }
        Thread.sleep(1000);
        System.out.println("task 结束后的线程数" + threadPoolExecutor.getPoolSize());

    }
}
