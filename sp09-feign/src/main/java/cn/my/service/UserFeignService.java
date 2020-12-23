package cn.my.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import cn.my.sp01.pojo.User;
import cn.my.web.util.JsonResult;

@FeignClient(name = "user-service",fallback = UserFeigenServiceFB.class)
public interface UserFeignService {
	@GetMapping("/{userId}")
	JsonResult<User> getUser(@PathVariable Integer userId);

	// 拼接路径 /{userId}/score?score=新增积分
	@GetMapping("/{userId}/score") 
	JsonResult addScore(@PathVariable Integer userId, @RequestParam Integer score);
}
