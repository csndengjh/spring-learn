package com.springboot.demo.synchorized.thread;

/**
 * @author dengjianhan
 * @className MyThread
 * @description
 * @date 2020/10/2 20:42
 */
public class MyThread implements Runnable {

    public static MyThread thread = null;

    private MyThread() {
    }


    public static MyThread getInstance(){
        if (thread == null){
            thread = new MyThread();
        }
        System.out.println(Thread.currentThread().getName() +": " + thread);
        return thread;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +": " + thread);
    }

    public static void main(String[] args) {
        Thread thread = new Thread( () ->{ MyThread.getInstance();});
        Thread thread1 = new Thread( () ->{ MyThread.getInstance();});
        thread.start();
        thread1.start();
        System.out.println("end");
    }
}
