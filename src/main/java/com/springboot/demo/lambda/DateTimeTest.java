package com.springboot.demo.lambda;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

/**
 * @ClassName: DateTimeTest
 * @Description:
 * @Author: dengjianhan
 * @Date: 2020/1/17 11:05
 */
public class DateTimeTest {

    @Test
    public void test(){
        // 获取这个月的最后一天
        LocalDate date = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date);

        // 获取这一年的最后一天  再减去2天
        LocalDate with = LocalDate.now().with(TemporalAdjusters.lastDayOfYear()).minusDays(2);
        System.out.println(with);

        // 产生固定日期的时间
        LocalDate birth = LocalDate.of(2014, Month.JULY,14);
        System.out.println(birth);
    }
}
