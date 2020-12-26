package com.my.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
//@ServletComponentScan(basePackages = "com.my.mall.filter")
@EnableDubbo
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ApplicationRun {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}
}
