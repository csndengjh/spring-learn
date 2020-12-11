package com.springboot.demo.lambda;

import com.springboot.demo.inherit.Son;
import org.junit.Test;

import java.util.Optional;

/**
 * @ClassName: OptionalTest
 * @Description:
 * @Author: dengjianhan
 * @Date: 2020/1/9 20:16
 */
public class OptionalTest {

    @Test
    public void test(){
        Son son = new Son();
        son.setName("里斯");
        Optional.<Son>ofNullable(son).ifPresent(child -> {
            son.setCount(child.getCount());
            son.setMoney(child.getMoney());
            son.setName("张三");
        });
        System.out.println(son);
        boolean present = Optional.<Son>ofNullable(null).isPresent();
        System.out.println(present);
    }


    @Test
    public void  test1(){
        Long tradeOrderItemId = null;
        Optional.ofNullable(tradeOrderItemId).orElseThrow(() -> new RuntimeException("tradeOrderItemId 不能为空"));
    }

    @Test
    public void test2(){
        Son user = null;
        Son user2 = new Son(2, 2,"张三aa");
        Son result = Optional.ofNullable(user).orElse(user2);
        System.out.println(result);

        // ifPresent 存在值执行里面的方法操作
        Optional<Integer> money = Optional.ofNullable(user).map(Son::getMoney);
        money.ifPresent(u -> System.out.println("money :"+ u));

        // isPresent 判断这个是否存在
        Optional<Integer> count = Optional.ofNullable(user).map(Son::getMoney);
        System.out.println(count.isPresent());

        Optional.ofNullable(user2).ifPresent(son -> {
                    son.setName("进入aa");
                    son.setMoney(562);
                    System.out.println(son);
                }
        );

        Optional<String> username = Optional
                .ofNullable(user2)
                .flatMap(u -> Optional.of(u.getName()))
                .flatMap(name -> Optional.of(name.toLowerCase()));

        System.out.println("Username is: " + username.orElse("Unknown"));

        String user22 = Optional
                .ofNullable(user2)
                .filter(u -> u.getCount() > 1)
                .map(Son::getName)
                .map(String::toUpperCase)
                .orElseGet(() -> "aaaa");

        System.out.println("Username is: " + user22);
    }


}
