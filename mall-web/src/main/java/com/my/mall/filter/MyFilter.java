package com.my.mall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
//@WebFilter("*.html")
public class MyFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter.init()");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("MyFilter.doFilter()");
		HttpServletRequest re=(HttpServletRequest)request;
		System.out.println(re.getRequestURI());
		
		chain.doFilter(request, response);
		
	}
	@Override
	public void destroy() {
		System.out.println("MyFilter.destroy()");
	}
}