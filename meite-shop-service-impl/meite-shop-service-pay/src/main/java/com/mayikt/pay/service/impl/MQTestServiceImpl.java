package com.mayikt.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.pay.mq.producer.IntegralProducer;

/**
 * 生产者测试消息
 */
@RestController
public class MQTestServiceImpl {
	@Autowired
	private IntegralProducer integralProducer;

	@RequestMapping("/send")
	public String send() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("paymentId", System.currentTimeMillis());
		jsonObject.put("userId", "123456");
		jsonObject.put("integral", 100);
		integralProducer.send(jsonObject);
		return "success";
	}

}
