package com.springboot.demo.jdk;

/**
 * 静态内部类
 * @author dengjianhan
 * @date 2019/8/9 9:30
 */
public class Out {
    private static int a;
    private int b;
    public static class Inner{
        public void print(){
            System.out.println(a);
        }
    }

}
