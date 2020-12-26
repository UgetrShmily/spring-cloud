package com.my.mall.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysResult implements Serializable{
	private static final long serialVersionUID = -2017855896732780375L;
	private Integer status;		//200表示成功  201表示失败.
	private String msg;			//服务器回传的提示信息.
	private Object data;		//服务器返回页面数据.
	//1.重载方法,实现数据简化   只返回正确的状态码信息200.
	public static SysResult success() {

		return new SysResult(200,"调用成功!", null);
	}

	//2.返回服务器数据 
	public static SysResult success(Object data) {

		return new SysResult(200,"调用成功!", data);
	}

	//3.只返回服务器提示信息.
	public static SysResult success(String msg,Object data) {

		return new SysResult(200,msg, data);
	}

	//4.定义失败的方法
	public static SysResult fail() {

		return new SysResult(201,"业务调用失败",null);
	}


}
