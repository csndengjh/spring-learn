package com.springboot.demo.designmode;

/**
 * @author dengjianhan
 * @description 表示某个类是用来处理何种来源的订单
 * @date 2020/7/23 9:18
 */


import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义注解的方式
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PayType {
    String payType();

    interface Pay {
        void pay();
    }

    @PayType(payType = "ICBC")
    class ICBCPay implements Pay {
        @Override
        public void pay() {
            System.out.println("ICBCPay  支付");
        }
    }

    @PayType(payType = "WhChat")
    class WhChatPay implements Pay {
        @Override
        public void pay() {
            System.out.println("WhChatPay  支付");
        }
    }

    class OrderService {

        private static Map<String, Pay> orderHandleMap;
        {
            List<Pay> payTypeList = new ArrayList<>();
            payTypeList.add(new ICBCPay());
            payTypeList.add(new WhChatPay());
            orderHandleMap = payTypeList.stream().collect(Collectors.toMap(
                    payType -> AnnotationUtils.findAnnotation(payType.getClass(), PayType.class).payType(), v -> v,
                    (v1, v2) -> v1));
        }

        public static void pay(String payType) {
            Pay pay = orderHandleMap.get(payType);
            pay.pay();
        }

        @Test
        public void test(){
            pay("ICBC");
        }

    }



}



