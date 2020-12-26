package com.my.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
public class MpConfig {
	//需要注册分页拦截器，mp分页插件才能用
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	} 
}
