package cn.my.service;

import org.springframework.stereotype.Component;

import cn.my.sp01.pojo.User;
import cn.my.web.util.JsonResult;

@Component
public class UserFeigenServiceFB implements UserFeignService{

	@Override
	public JsonResult<User> getUser(Integer userId) {
		return JsonResult.err("无法获取用户信息");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		return JsonResult.err("无法增加用户积分");
	}

}
