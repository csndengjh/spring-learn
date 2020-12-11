package com.springboot.demo.designmode.singleton;

/**
 * @author dengjianhan
 * @className SingletonMode  静态内部类  懒汉式写法
 * @description
 * @date 2020/10/2 21:12
 */
public class SingletonMode  {

    private static SingletonMode singletonMode = null;

    public SingletonMode() {}

    public static SingletonMode getInstance(){

        return LazyHolder.singletonMode;
    }

    public static class LazyHolder{
        private static SingletonMode singletonMode = new SingletonMode();
    }
}
