package com.springboot.demo.lambda;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author dengjianhan
 * @className ForkJoinTask
 * @description  Fork/Join框架是Java7提供的并行执行任务框架，
 * 思想是将大任务分解成小任务，然后小任务又可以继续分解，然后每个小任务分别计算出结果再合并起来，最后将汇总的结果作为大任务结果
 * @date 2020/7/22 14:08
 */
public class ForkJoinTask extends RecursiveTask<Long> {

    private static final long MAX = 1000000000L;

    private static final long THRESHOLD = 100L;

    private long start;

    private long end;

    public ForkJoinTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        test();
        System.out.println("-----------------------");
        testForkJoin();

    }

    private static void test() {
        System.out.println("test");
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0L; i <= MAX; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    private static void testForkJoin(){
        System.out.println("testForkJoin");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new ForkJoinTask(1, MAX));
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start  + "ms");
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if ((end - start <= THRESHOLD)) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end)/2;
            ForkJoinTask task1 = new ForkJoinTask(start, mid);
            task1.fork();
            ForkJoinTask task2 = new ForkJoinTask(mid + 1, end);
            task2.fork();
            return  task1.join() + task2.join();

        }
    }
}
