package com.my.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.my.mall.service.UserService;
import com.my.mall.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/check/{param}/{type}")
	public JSONPObject checkUser(@PathVariable String param,@PathVariable Integer type,String callback) {
		//返回标识符  false:表示用户可以使用     true:数据库中以存在
		boolean flag = userService.checkUser(param,type);
		SysResult sysResult = SysResult.success(flag);
		return new JSONPObject(callback, sysResult);
	}
	@Autowired
	private JedisCluster jedisCluster;
	@GetMapping("/query/{ticket}")
    public JSONPObject findUserByTicket(@PathVariable String ticket,String callback) {
        String userJSON = jedisCluster.get(ticket);
        //判断当前redis中是否有数据
        if(StringUtils.isEmpty(userJSON)) {
            
            return new JSONPObject(callback, SysResult.fail());
        }else {
            //证明用户已经登陆
            SysResult sysResult = SysResult.success(userJSON);
            return new JSONPObject(callback,sysResult);
        }
    }
}
