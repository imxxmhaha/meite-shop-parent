package com.mayikt.pay.strategy.impl;

import com.mayikt.pay.entity.ChannelEntity;
import com.mayikt.pay.out.dto.PayMentTransacDTO;
import com.mayikt.pay.strategy.PayStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 银联支付渠道实现
 */
@Slf4j
public class UnionPayStrategy implements PayStrategy {

	@Override
	public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {
		log.info(">>>>>银联参数封装开始<<<<<<<<");
		// Plugin
		return "银联支付from表单提交";
	}

}
