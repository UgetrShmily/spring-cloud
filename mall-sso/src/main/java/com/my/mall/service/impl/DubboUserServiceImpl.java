package com.my.mall.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.mall.dao.UserMapper;
import com.my.mall.pojo.User;
import com.my.mall.service.DubboUserService;
import com.my.mall.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;
@Service
public class DubboUserServiceImpl implements DubboUserService{
	@Autowired
	private UserMapper userMapper;
	public void doRegister(User user){
		byte[] bytes=user.getPassword().getBytes();
		String md5Digest = DigestUtils.md5DigestAsHex(bytes);
		user.setPassword(md5Digest)
			.setEmail("12345678911")
			.setCreated(new Date())
			.setUpdated(user.getCreated());
		userMapper.insert(user);
	}
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return "aaaaaaaaaaaaaaaaaaaaaaaaaa";
	}
	@Autowired
	private JedisCluster jedis;
	@Override
	public String doLogin(User user) {
		String pass=DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", user.getUsername())
					.eq("password", pass);
		User userDB=userMapper.selectOne(queryWrapper);
		if(userDB==null) {
			return null;
		}
		String key=UUID.randomUUID().toString();
		userDB.setPassword("这就是密码");
		String userJson=ObjectMapperUtil.toJson(userDB);
		jedis.setex(key, 7*24*3600, userJson);
		return key;
	}
}
