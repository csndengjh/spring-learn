package com.springboot.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dengjianhan
 * @className StudentEntity
 * @description  mapConstruct 做对象转换
 * @date 2020/11/9 15:23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    private String name;
    private int age;
    private GenderEnum gender;
    private Double height;
    private Date birthday;


    public enum GenderEnum {
        Male("1", "男"),
        Female("0", "女");

        private String code;
        private String name;

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        GenderEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudentVO {
        private String name;
        private int age;
        private String gender;
        private Double height;
        private String birthday;
    }


    @Mapper
    public interface StudentMapper {

        StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

        @Mapping(source = "gender.name", target = "gender")
        @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
        StudentVO student2StudentVO(StudentEntity student);
        List<StudentVO> students2StudentVOs(List<StudentEntity> studentList);


    }


    public static void main(String[] args) {
        StudentEntity student = StudentEntity.builder().name("小明").age(6).gender(GenderEnum.Male).height(121.1).birthday(new Date()).build();
        System.out.println(student);
        //这行代码便是实际要用的代码
        StudentVO studentVO = StudentMapper.INSTANCE.student2StudentVO(student);
        System.out.println(studentVO);

        List<StudentEntity> list = new ArrayList<>();
        list.add(student);
        List<StudentVO> result = StudentMapper.INSTANCE.students2StudentVOs(list);
        System.out.println(result);
    }
}






