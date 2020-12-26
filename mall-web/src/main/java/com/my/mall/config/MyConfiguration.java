package com.my.mall.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.my.mall.filter.MyFilter;
import com.my.mall.interceptor.MyInterceptor;

@Configuration
public class MyConfiguration implements WebMvcConfigurer{
//	@Bean
//	public FilterRegistrationBean<Filter> filterRegistrationBean() {
//		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setFilter(new MyFilter());
//		registrationBean.addUrlPatterns("/*");
//		return registrationBean;
//	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/cart/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(true);
	}
}
