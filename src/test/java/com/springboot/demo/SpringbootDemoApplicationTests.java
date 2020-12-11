package com.springboot.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class SpringbootDemoApplicationTests {

	@Test
	public void contextLoads() {
		String name ="";
		getName(name);
		System.out.println(name);

	}

	public String getName(String name){
		name ="张三";
		return name;
	}

}
