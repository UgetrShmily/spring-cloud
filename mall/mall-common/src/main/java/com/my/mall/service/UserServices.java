package com.my.mall.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.my.mall.pojo.User;



public interface UserServices {
	
	List<User> findAll();
	
	@Transactional
	void saveUser(User user);
}
