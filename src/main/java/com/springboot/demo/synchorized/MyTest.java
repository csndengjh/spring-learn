package com.springboot.demo.synchorized;


import com.springboot.demo.expection.NhException;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description A\B\C三个线程顺序执行10次
 *
 * @author sunpy
 * @date 2018年11月28日  下午2:23:45
 */
public class MyTest {

    static class MyTask {
        private static ReentrantLock rl = new ReentrantLock();
        private static Condition conditionA = rl.newCondition();
        private static Condition conditionB = rl.newCondition();
        private static Condition conditionC = rl.newCondition();

        public void execute(String flag) {
            rl.lock();

            try {
                for (int i = 1 ; i <= 10 ; i++) {
                    if ("A".equals(flag)) {
                        System.out.println(Thread.currentThread().getName() + " - " + i);
                        conditionB.signal();
                        conditionA.await();
                    }

                    if ("B".equals(flag)) {
                        System.out.println(Thread.currentThread().getName() + " - " + i);
                        conditionC.signal();
                        conditionB.await();
                    }

                    if ("C".equals(flag)) {
                        System.out.println(Thread.currentThread().getName() + " - " + i);
                        conditionA.signal();
                        conditionC.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rl.unlock();
            }
        }

    }



    @Test
    public void test1() {
        Thread a = new Thread(() -> {
            System.out.println("A");
        });

        Thread b = new Thread(() -> {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        });

        Thread c = new Thread(() -> {
            try {
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C");
        });
        a.start();
        b.start();
        c.start();
    }

    @Test
    public void test2() {
        final MyTask myTask = new MyTask();

        new Thread(() -> myTask.execute("A"), "A").start();

        new Thread(()-> myTask.execute("B"), "B").start();

        new Thread(()-> myTask.execute("C"), "C").start();
    }


    @Test
    public void test3() {
        Lock lock = new ReentrantLock();
    }


    @Test
    public void test4() throws NhException {
        try {
            Thread thread = new Thread(() -> {
                    throw new NhException();
            });
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    // 未捕获异常处理，此时再抛出异常，再外层的catch 捕获不了
                    if (e instanceof NhException) {
                        System.out.println("2222");
                        System.out.println("uncaughtException");
                        throw new NhException("222");
                    }
                }
            });
            thread.start();
        } catch (Exception e) {
            System.out.println("3333");
        }

    }

    /*
     * 第一步：定义符合线程异常处理器规范的“异常处理器”
     * 实现Thread.UncaughtExceptionHandler规范
     */
    class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
        /*
         * Thread.UncaughtExceptionHandler.uncaughtException()会在线程因未捕获的异常而临近死亡时被调用
         */
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught");
            if (e instanceof NhException) {
                System.out.println("2222");
                System.out.println("uncaughtException");
            }
        }
    }


    /*
     * 第二步：定义线程工厂
     * 线程工厂用来将任务附着给线程，并给该线程绑定一个异常处理器
     */
    class HanlderThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            System.out.println(this+" : creating new Thread");
            Thread t = new Thread(r);
            System.out.println("created "+t);
            t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());//设定线程工厂的异常处理器
            System.out.println("eh="+t.getUncaughtExceptionHandler());
            return t;
        }
    }


    /*
     * 第三步：我们的任务可能会抛出异常
     * 显示的抛出一个exception
     */
    class ExceptionThread implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            int i = 0;
            if (i == 0) {
                System.out.println("准备抛自定义异常");
                throw new NhException("自定义异常");
            }
        }
    }


    @Test
    public void test5() {


//        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool(new HanlderThreadFactory());
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
        Future submit = threadPoolExecutor.submit(new ExceptionThread());
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("51515");
            if (e instanceof ExecutionException) {
                // 异常转换
                System.out.println("4444");
            }

            if (e instanceof NhException) {
                // 异常转换
                System.out.println("5555");
            }
        }

    }



}