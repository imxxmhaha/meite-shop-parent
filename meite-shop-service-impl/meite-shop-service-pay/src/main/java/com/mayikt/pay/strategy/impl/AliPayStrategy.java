package com.mayikt.pay.strategy.impl;

import com.mayikt.pay.entity.ChannelEntity;
import com.mayikt.pay.out.dto.PayMentTransacDTO;
import com.mayikt.pay.strategy.PayStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 支付宝支付渠道
 */
@Slf4j
public class AliPayStrategy implements PayStrategy {

	@Override
	public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {
		log.info(">>>>>支付宝参数封装开始<<<<<<<<");
		return "支付宝支付from表单提交";
	}

}