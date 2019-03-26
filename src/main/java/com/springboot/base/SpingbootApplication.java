package com.springboot.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@EnableScheduling
@SpringBootApplication
/*若要自动生成表需先将@ComponentScan注释以及application.properties中的执行初始化数据部分*/
@ComponentScan(basePackages = {"com.springboot.base.module","com.springboot.base.common","com.springboot.base.filters","com.springboot.base.handlers"})
public class SpingbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpingbootApplication.class, args);
	}

}

