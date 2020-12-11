package com.springboot.demo.test;

import org.junit.Test;

/**
 * @author dengjianhan
 * @className InterfaceTest
 * @description  接口中的default 方法
 * @date 2020/10/24 19:10
 */
public class InterfaceTest {

    interface A {
      default void a(){
          System.out.println("A");
        }
    }

    interface B extends A{
        default void a(){
            System.out.println("B");
        }

        default void b(){
            System.out.println("B");
        }
    }

    class TestClazz implements A,B{

    }

    class C {
        public void b(){
            System.out.println("Test2B");
        }
    }

    class Test2Clazz extends C implements B{

    }

    @Test
    public void test(){
        TestClazz testClazz = new TestClazz();
        testClazz.a();
    }

    @Test
    public void test2(){
        Test2Clazz test2Clazz = new Test2Clazz();
        test2Clazz.b();
    }
}
