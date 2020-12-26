package com.my.mall.service;

import com.my.mall.pojo.User;

public interface DubboUserService {
	void doRegister(User user);

	String getString();

	String doLogin(User user);
}
