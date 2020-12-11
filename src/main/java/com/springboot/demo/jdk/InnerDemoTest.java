package com.springboot.demo.jdk;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 调用静态内部类的方式
 * @author dengjianhan
 * @date 2019/8/12 14:23
 */
public class InnerDemoTest {

    @Test
    public void desc(){
        Out.Inner inner = new Out.Inner();
        inner.print();
    }


}
