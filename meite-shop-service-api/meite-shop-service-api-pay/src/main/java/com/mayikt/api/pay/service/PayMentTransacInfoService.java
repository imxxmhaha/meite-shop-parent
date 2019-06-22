package com.mayikt.api.pay.service;

import com.mayikt.common.base.BaseResponse;
import com.mayikt.pay.out.dto.PayMentTransacDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 
 */
@FeignClient(value = "APP-MAYIKT-PAY")
public interface PayMentTransacInfoService {
	@GetMapping("/tokenByPayMentTransac")
	public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
