package com.mayikt.api.pay.service;

import com.mayikt.common.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 */
@FeignClient(value = "APP-MAYIKT-PAY")
public interface PayContextService {
	@GetMapping("/toPayHtml")
	public BaseResponse<JSONObject> toPayHtml(String channelId, String payToken);

}
