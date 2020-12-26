package com.my.mall.vo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import redis.clients.jedis.exceptions.JedisConnectionException;

@RestControllerAdvice
public class GlobException {
	@ExceptionHandler(RuntimeException.class)
	public SysResult sysResult(Exception exception) {
		exception.printStackTrace(); //输出/log日志保存
		return SysResult.fail();
	}
}
