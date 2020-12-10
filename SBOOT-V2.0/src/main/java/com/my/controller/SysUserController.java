package com.my.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.common.vo.JsonResult;
import com.my.pojo.SysUser;
import com.my.service.SysUserService;

@RestController
@RequestMapping("/user")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		return new JsonResult(sysUserService.findPageObjects(username,pageCurrent));
	}
	@RequestMapping("/doValidById")
	public JsonResult doValidById(Integer id,Integer valid){
		sysUserService.validById(id,valid, "admin");//"admin"用户将来是登陆用户
		String message=valid==0?"启用成功":"禁用成功";
		return new JsonResult(message);
	}
	@RequestMapping("/doSaveObject")
	public JsonResult doSaveObject(SysUser entity,Integer[] roleIds){
		sysUserService.saveObject(entity,roleIds);
		return new JsonResult("用户保存成功");
	}
	@RequestMapping("/doFindObjectById")
	public JsonResult doFindObjectById(Integer id){
		Map<String,Object> map=sysUserService.findObjectById(id);
		return new JsonResult(map);
	}
	@RequestMapping("/doUpdateObject")
	public JsonResult doUpdateObject(
			SysUser entity,Integer[] roleIds){
		sysUserService.updateObject(entity,roleIds);
		return new JsonResult("更新用户数据成功");
	}
	@RequestMapping("/doUpdatePassword")
	public JsonResult doUpdatePassword(String pwd, String newPwd,String cfgPwd) {
		sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		return new JsonResult("密码修改成功，将在下次登录后使用");
	}

}
