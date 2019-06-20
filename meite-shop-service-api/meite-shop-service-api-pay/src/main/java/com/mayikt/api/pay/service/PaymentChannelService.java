package com.mayikt.api.pay.service;

import java.util.List;

import com.mayikt.pay.out.dto.PaymentChannelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "APP-MAYIKT-PAY")
public interface PaymentChannelService {
	/**
	 * 查询所有支付渠道
	 * 
	 * @return
	 */
	@GetMapping("/selectAll")
	public List<PaymentChannelDTO> selectAll();
}
