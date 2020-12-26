package com.my.mall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	private static final ObjectMapper MAPPER=new ObjectMapper();
	public static String toJson(Object obj) {
		String json=null;
		try {
			json=MAPPER.writeValueAsString(obj);
		}catch(JsonProcessingException e){
			e.printStackTrace();
			throw new RuntimeException("obj->json失败");
		}
		return json;
	} 
	public static <T> T toObject(String json,Class<T> targetClass){
		T t=null;
		try {
			t=MAPPER.readValue(json, targetClass);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("json->object失败");
		}
		return t;
	}
}
