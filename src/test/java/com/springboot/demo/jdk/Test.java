package com.springboot.demo.jdk;

import com.springboot.demo.entity.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dengjianhan
 * @date 2019/8/9 11:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${qcc_company_name:2323}")
    private Long qcc_company_name;

    @org.junit.Test
    public void testDemo(){
        Out.Inner inner = new Out.Inner();
        inner.print();
    }

    @org.junit.Test
    public void  testDemo1(){
        Map<String, String> multiValueMap  = new HashMap<>();
        multiValueMap.put("page","1");
        ResponseDto responseDto = testRestTemplate.getForObject("http://localhost:8087/test?page={page}",
                ResponseDto.class, multiValueMap);
        log.warn("返回的response: [{}]",responseDto);

    }

    @org.junit.Test
    public void test1(){
        Duration duration = Duration.ofHours(10);
        System.out.println(duration);
        System.out.println(qcc_company_name);
    }
}
