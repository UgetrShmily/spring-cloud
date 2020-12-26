package com.my.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/")
public class WebJsonPController {
	@GetMapping("/testJsonp")
	public JSONPObject jsonp(String callback) {
		Map<String, String> map=new HashMap<>();
		map.put("id", "12");
		map.put("name", "kkkkkkk");
		return new JSONPObject(callback, map);
	}
}
