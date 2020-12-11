package com.springboot.demo.synchorized;

/**
 * 线程的重排序
 * @author zhengbinMac
 */
public class NoVisibility {
    private static volatile boolean ready = false;
    private static volatile int number = 0;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready) {
                --number;
//                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}