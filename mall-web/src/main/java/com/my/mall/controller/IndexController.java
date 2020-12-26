package com.my.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping("/{model}")
	public String index(@PathVariable String model) {
		return model;
	}
	
	@RequestMapping("/user/{page}")
	public String doPage(@PathVariable String page) {
		return page;
	}
}
