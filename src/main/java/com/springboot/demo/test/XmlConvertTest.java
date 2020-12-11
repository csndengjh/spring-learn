package com.springboot.demo.test;

import com.springboot.demo.entity.Student;
import com.springboot.demo.util.JaxbUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OptionalTest
 * @Description:
 * @Author: dengjianhan
 * @Date: 2019/12/25 14:27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlConvertTest {

    @Value("${qcc_company_name:}")
    private Long qcc_company_name;


    @Test
    public void test1(){
        Duration duration = Duration.ofHours(10);
        System.out.println(duration);
        System.out.println(qcc_company_name);
    }

    @Test
    public void test2(){
        Student student = new Student();
        student.setName("邪恶小法师");
        student.setAge("110");
        student.setSex("男");
        List<Student.Friend> list = new ArrayList<>();
        Student.Friend f1 = new Student.Friend();
        f1.setName("德玛西亚之力");
        f1.setAge("888");
        f1.setSex("男");

        Student.Friend f2 = new Student.Friend();
        f2.setName("无双剑姬");
        f2.setAge("898");
        f2.setSex("女");

        list.add(f1);
        list.add(f2);
        student.setFriendList(list);
        // java 对象转化成 xml
        String ss = JaxbUtil.convertToXml(student);
        System.out.println(ss);
        // xml 转化成java 对象
        Student student1 = JaxbUtil.converyToJavaBean(ss, Student.class);
        System.out.println(student1);


    }


}
