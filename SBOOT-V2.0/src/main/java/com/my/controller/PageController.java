package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	@RequestMapping("/{module}/{modulePage}")
	public String doLogUI(@PathVariable String modulePage) {
		return "sys/"+modulePage;
	}
	@RequestMapping("/doPageUI")
	public String doPageUI() {
		return "common/page";
	}
}
