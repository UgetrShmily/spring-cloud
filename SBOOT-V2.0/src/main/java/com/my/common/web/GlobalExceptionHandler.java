package com.my.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.common.vo.JsonResult;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public JsonResult doHandleRuntimeException(RuntimeException e) {
		log.info(e.getMessage());
		return new JsonResult(e);
	}
}
