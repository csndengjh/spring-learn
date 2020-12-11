package com.springboot.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @ClassName: Student
 * @Description:
 * @Author: dengjianhan
 * @Date: 2020/1/15 19:45
 */
@Data
//XML文件中的根标识
@XmlRootElement(name = "Student")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {"name","age","sex","friendList"})
@NoArgsConstructor
public class Student {

    private String name;
    private String age;
    private String sex;
    private List<Friend> friendList;

    @Data
    public static class Friend {
        private String name;
        private String age;
        private String sex;
    }
}
