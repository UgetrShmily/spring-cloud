package com.my.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.my.mall.dao.UserMapper;
import com.my.mall.pojo.User;
import com.my.mall.service.UserService;
import com.my.mall.service.UserServices;


@Service(timeout=3000)	//3秒超时	
public class UserServiceImpl implements UserServices {
	
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> findAll() {
		
		System.out.println("我是第一个服务的提供者");
		return userMapper.selectList(null);
	}
	
	@Override
	public void saveUser(User user) {
		
		userMapper.insert(user);
	}

}
