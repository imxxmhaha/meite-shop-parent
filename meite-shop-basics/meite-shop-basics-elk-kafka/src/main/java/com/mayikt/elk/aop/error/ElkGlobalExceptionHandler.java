package com.mayikt.elk.aop.error;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.elk.kafka.KafkaSender;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 全局捕获异常
 */
@ControllerAdvice
@Slf4j
public class ElkGlobalExceptionHandler {
	@Autowired
	private KafkaSender<JSONObject> kafkaSender;

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JSONObject exceptionHandler(Exception e) {
		log.info("###全局捕获异常###,error:{}", e);

		// 1.封装异常日志信息
		JSONObject errorJson = new JSONObject();
		JSONObject logJson = new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		logJson.put("request_time", df.format(new Date()));
		logJson.put("error_info", e);
		errorJson.put("error", logJson);
		kafkaSender.send(errorJson);
		// 2. 返回错误信息
		JSONObject result = new JSONObject();
		result.put("code", 500);
		result.put("msg", "系统错误");

		return result;
	}
}
