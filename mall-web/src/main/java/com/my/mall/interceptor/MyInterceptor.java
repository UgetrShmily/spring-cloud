package com.my.mall.interceptor;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.my.mall.pojo.User;
import com.my.mall.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;
@Component
public class MyInterceptor implements HandlerInterceptor {
	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//业务处理完成之后,将用户信息删除.
		request.removeAttribute("JT_USER");
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//根据cookie获取value结果
		Cookie[] cookies=request.getCookies();
		String cookieValue="";
		for(Cookie c:cookies) {
			if(c.getName()=="JT_TICKET") {
				cookieValue=c.getValue();
			}
		}
		System.out.println(cookieValue="]]]]]]]]]]]]]"+jedisCluster);
		//判断value是否有值
		if(!StringUtils.isEmpty(cookieValue)) {
			//判断redis中是否有该记录.
			if(jedisCluster.exists(cookieValue)) {
				String key = cookieValue;
				String userJSON = jedisCluster.get(key);
				User user = ObjectMapperUtil.toObject(userJSON, User.class);
				//将数据保存到request对象中
				request.setAttribute("JT_USER", user);

				//如果有特殊的需求,可以利用session临时保存数据.
				//request.getSession().setAttribute(name, value);

				//用户已经登陆.予以放行
				return true;

			}
		}
		System.out.println("==================重定向===================");
		response.sendRedirect("/user/login.html");
		return false;
	}
}