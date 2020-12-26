package com.my.mall.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.mall.dao.UserMapper;
import com.my.mall.pojo.User;
import com.my.mall.service.UserService;
@Service
public class UserServiceImlp implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
    public boolean checkUser(String param, Integer type) {
        //以空间换时间
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1, "username");
        map.put(2, "phone");
        map.put(3, "email");
        //1.type如何转化为具体的字段  10
        String column = map.get(type);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(column, param);
        int count = userMapper.selectCount(queryWrapper);
        //存在true  ,不存在false
        return count>0?true:false;
    }
    
}
