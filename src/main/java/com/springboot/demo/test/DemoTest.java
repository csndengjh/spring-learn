package com.springboot.demo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author dengjianhan
 * @className DemoTest
 * @description
 * @date 2020/10/4 11:20
 */
@RunWith(SpringRunner.class)
public class DemoTest {

    @Before
    public void before(){
        System.out.println("before");
    }

    @Test
    public void test(){
        HashMap<String, String> hashMap = new HashMap();
        Object o = hashMap.put("轷龚", null);
        System.out.println(o);
        o = hashMap.put("轻鼞", "轻鼞");
        System.out.println(o);
        o = hashMap.put("辁鹤", null);
        System.out.println(o);

    }

    @Test
    public void test2(){
        int a = 8;
        int i = a >>>2;
        System.out.println(i);

        int b = a << 3;
        System.out.println(b);
    }


    @Test
    public void  test3(){
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("test" + i);
            list1.add("test" + i*2);
        }
        System.out.println(checkDiffrent1(list, list1));
        System.out.println(list);
        System.out.println(list1);
    }

    /**
     * 方法2：利用List的 retainAll的方法进行判断
     */
    private static boolean checkDiffrent1(List<String> list, List<String> list1) {
        long st = System.currentTimeMillis();
        System.out.println("消耗时间为：" + (System.currentTimeMillis() - st));
        return !list.retainAll(list1);
    }

}
